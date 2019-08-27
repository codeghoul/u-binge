package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.constants.OrderStatus;
import com.finalassessment.ubinge.constants.PaymentMode;
import com.finalassessment.ubinge.dto.DeliveryGuyDTO;
import com.finalassessment.ubinge.dto.OrderDTO;
import com.finalassessment.ubinge.dto.OrderModificationDTO;
import com.finalassessment.ubinge.exception.DeliveryGuyNotFoundException;
import com.finalassessment.ubinge.exception.OrderNotFoundException;
import com.finalassessment.ubinge.exception.OrderStatusException;
import com.finalassessment.ubinge.exception.PaymentModeException;
import com.finalassessment.ubinge.model.DeliveryGuy;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.repository.DeliveryGuyRepository;
import com.finalassessment.ubinge.repository.OrderRepository;
import com.finalassessment.ubinge.service.DeliveryGuyService;
import com.finalassessment.ubinge.utility.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DeliveryGuyServiceImpl implements DeliveryGuyService {
    private DeliveryGuyRepository deliveryGuyRepository;
    private OrderRepository orderRepository;

    @Autowired
    public DeliveryGuyServiceImpl(DeliveryGuyRepository deliveryGuyRepository, OrderRepository orderRepository) {
        this.deliveryGuyRepository = deliveryGuyRepository;
        this.orderRepository = orderRepository;
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
        return MapperUtil.toDeliveryGuyDTO(deliveryGuy);
    }

    @Override
    public DeliveryGuyDTO update(DeliveryGuyDTO deliveryGuyDTO, Long deliveryGuyId) {
        log.debug("Updating Delivery Guy.");
        DeliveryGuy deliveryGuy = getDeliveryGuy(deliveryGuyId);
        deliveryGuy.setName(deliveryGuyDTO.getName());
        deliveryGuy.setPhoneNo(deliveryGuyDTO.getPhoneNo());
        deliveryGuy.setEmail(deliveryGuyDTO.getEmail());
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

        PaymentMode paymentMode = modification.getPaymentMode();
        OrderStatus orderStatus = modification.getOrderStatus();

        if (paymentMode != null) {
            throw new PaymentModeException("Delivery Guy cannot change Payment Mode.");
        }

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
}