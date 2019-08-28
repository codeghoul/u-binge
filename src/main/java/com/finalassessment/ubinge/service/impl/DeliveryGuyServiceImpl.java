package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.constants.OrderStatus;
import com.finalassessment.ubinge.dto.DeliveryGuyDTO;
import com.finalassessment.ubinge.dto.OrderDTO;
import com.finalassessment.ubinge.dto.OrderModificationDTO;
import com.finalassessment.ubinge.exception.DeliveryGuyNotFoundException;
import com.finalassessment.ubinge.exception.OrderNotFoundException;
import com.finalassessment.ubinge.exception.OrderStatusException;
import com.finalassessment.ubinge.model.DeliveryGuy;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.model.User;
import com.finalassessment.ubinge.repository.DeliveryGuyRepository;
import com.finalassessment.ubinge.repository.OrderRepository;
import com.finalassessment.ubinge.repository.RoleRepository;
import com.finalassessment.ubinge.repository.UserRepository;
import com.finalassessment.ubinge.service.DeliveryGuyService;
import com.finalassessment.ubinge.utility.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DeliveryGuyServiceImpl implements DeliveryGuyService {
    private DeliveryGuyRepository deliveryGuyRepository;
    private OrderRepository orderRepository;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DeliveryGuyServiceImpl(DeliveryGuyRepository deliveryGuyRepository, OrderRepository orderRepository,
                                  RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.deliveryGuyRepository = deliveryGuyRepository;
        this.orderRepository = orderRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<DeliveryGuyDTO> findAll() {
        log.debug("Returning Delivery Guy(s) from Service");
        return deliveryGuyRepository.findAll().stream().map(MapperUtil :: toDeliveryGuyDTO).collect(Collectors.toList());
    }

    @Override
    public DeliveryGuyDTO findById(Long deliveryGuyId) {
        log.debug("Returning Delivery Guy by Id from Service");
        DeliveryGuy deliveryGuy = getDeliveryGuy(deliveryGuyId);
        return MapperUtil.toDeliveryGuyDTO(deliveryGuy);
    }

    @Override
    public DeliveryGuyDTO save(DeliveryGuyDTO deliveryGuyDTO) {
        log.debug("Saving Delivery Guy from Service");
        DeliveryGuy deliveryGuy = deliveryGuyRepository.save(MapperUtil.toDeliveryGuy(deliveryGuyDTO));
        createUser(deliveryGuy);
        return MapperUtil.toDeliveryGuyDTO(deliveryGuy);
    }

    @Override
    public DeliveryGuyDTO update(DeliveryGuyDTO deliveryGuyDTO, Long deliveryGuyId) {
        log.debug("Updating Delivery Guy.");
        DeliveryGuy deliveryGuy = getDeliveryGuy(deliveryGuyId);
        User user = userRepository.findByEmail(deliveryGuy.getEmail());
        user.setEmail(deliveryGuyDTO.getEmail());
        deliveryGuy.setName(deliveryGuyDTO.getName());
        deliveryGuy.setPhoneNo(deliveryGuyDTO.getPhoneNo());
        deliveryGuy.setEmail(deliveryGuyDTO.getEmail());
        deliveryGuy.setPassword(bCryptPasswordEncoder.encode(deliveryGuyDTO.getPassword()));

        userRepository.saveAndFlush(user);
        deliveryGuyRepository.saveAndFlush(deliveryGuy);
        return MapperUtil.toDeliveryGuyDTO(deliveryGuy);
    }

    @Override
    public void deleteById(Long deliveryGuyId) {
        deliveryGuyRepository.deleteById(deliveryGuyId);
    }

    @Override
    public List<OrderDTO> getDeliveryGuyOrders(Long deliveryGuyId) {
        log.debug("Getting all orders from Delivery Guy");
        DeliveryGuy deliveryGuy = getDeliveryGuy(deliveryGuyId);
        return deliveryGuy.getOrders().stream().map(MapperUtil :: toOrderDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getDeliveryGuyOrderById(Long deliveryGuyId, Long orderId) {
        DeliveryGuy deliveryGuy = getDeliveryGuy(deliveryGuyId);
        Order order = getOrder(orderId);
        if (!deliveryGuy.getOrders().contains(order)) {
            throw new OrderNotFoundException(orderId);
        }
        return MapperUtil.toOrderDTO(order);
    }

    @Override
    public OrderDTO modifyOrder(Long deliveryGuyId, Long orderId, OrderModificationDTO modification) {
        DeliveryGuy deliveryGuy = getDeliveryGuy(deliveryGuyId);
        Order order = getOrder(orderId);

        //TODO: use different error.
        if (!deliveryGuy.getOrders().contains(order)) {
            throw new OrderNotFoundException(orderId);
        }

//        PaymentMode paymentMode = PaymentMode.fromDescription(modification.getPaymentMode());
        OrderStatus orderStatus = OrderStatus.fromDescription(modification.getOrderStatus());

//        if (paymentMode != null) {
//            throw new PaymentModeException("Delivery Guy cannot change Payment Mode.");
//        }

        if (order.getOrderStatus().getDescription().equals("picked up") && orderStatus.getDescription().equals("delivered")) {
            order.setOrderStatus(OrderStatus.DELIVERED);
        } else {
            throw new OrderStatusException("Delivery guy cannot change status from " + order.getOrderStatus() + " to " + modification.getOrderStatus());
        }
        return MapperUtil.toOrderDTO(orderRepository.save(order));
    }

    private DeliveryGuy getDeliveryGuy(Long deliveryGuyId) {
        return deliveryGuyRepository.findById(deliveryGuyId).orElseThrow(() -> new DeliveryGuyNotFoundException(deliveryGuyId));
    }

    private Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    private void createUser(DeliveryGuy deliveryGuy) {
        User user = new User();
        user.setEmail(deliveryGuy.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(deliveryGuy.getPassword()));
        user.setRole(roleRepository.findByRole("DELIVERY"));
        userRepository.save(user);
    }
}