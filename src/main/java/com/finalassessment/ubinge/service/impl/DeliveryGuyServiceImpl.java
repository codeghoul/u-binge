package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.constants.OrderStatus;
import com.finalassessment.ubinge.constants.PaymentMode;
import com.finalassessment.ubinge.exception.DeliveryGuyNotFoundException;
import com.finalassessment.ubinge.exception.OrderNotFoundException;
import com.finalassessment.ubinge.exception.OrderStatusException;
import com.finalassessment.ubinge.exception.PaymentModeException;
import com.finalassessment.ubinge.model.DeliveryGuy;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.repository.DeliveryGuyRepository;
import com.finalassessment.ubinge.repository.OrderRepository;
import com.finalassessment.ubinge.service.DeliveryGuyService;
import com.finalassessment.ubinge.vo.GeneralDetailVO;
import com.finalassessment.ubinge.vo.OrderModificationVO;
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
    public List<DeliveryGuy> findAll() {
        log.debug("Returning Delivery Guy(s) from Service");
        return deliveryGuyRepository.findAll();
    }

    @Override
    public DeliveryGuy findById(Long deliveryGuyId) {
        log.debug("Returning Delivery Guy by Id from Service");
        return deliveryGuyRepository.findById(deliveryGuyId).orElseThrow(() -> new DeliveryGuyNotFoundException(deliveryGuyId));
    }

    @Override
    public DeliveryGuy save(DeliveryGuy newDeliveryGuy) {
        log.debug("Saving Delivery Guy from Service");
        return deliveryGuyRepository.save(newDeliveryGuy);
    }

    @Override
    public DeliveryGuy update(GeneralDetailVO generalDetailVO, Long deliveryGuyId) {
        log.debug("Updating Delivery Guy from Service");
        DeliveryGuy deliveryGuy = findById(deliveryGuyId);
        deliveryGuy.setName(generalDetailVO.getName());
        deliveryGuy.setEmail(generalDetailVO.getEmail());
        deliveryGuy.setPhoneNo(generalDetailVO.getPhoneNo());
        return deliveryGuyRepository.saveAndFlush(deliveryGuy);
    }

    @Override
    public void delete(DeliveryGuy deliveryGuy) {
        log.debug("Deleting Delivery Guy from Service");
        deliveryGuyRepository.delete(deliveryGuy);
    }

    @Override
    public void deleteById(Long deliveryGuyId) {
        log.debug("Updating Delivery Guy from Service");
        deliveryGuyRepository.deleteById(deliveryGuyId);
    }

    @Override
    public List<Order> getDeliveryGuyOrders(Long deliveryGuyId) {
        log.debug("Getting all Delivery Guy Order(s)");
        DeliveryGuy deliveryGuy = deliveryGuyRepository.findById(deliveryGuyId).orElseThrow(() -> new DeliveryGuyNotFoundException(deliveryGuyId));
        return deliveryGuy.getOrders().stream().collect(Collectors.toList());
    }

    @Override
    public Order getDeliveryGuyOrderById(Long deliveryGuyId, Long orderId) {
        log.debug("Getting Orders by Delivery Guy Id.");
        DeliveryGuy deliveryGuy = findById(deliveryGuyId);
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));

        if (!order.getDeliveryGuy().equals(deliveryGuy)) {
            throw new OrderNotFoundException(orderId);
        }

        return order;
    }

    @Override
    public Order modifyOrder(Long deliveryGuyId, Long orderId, OrderModificationVO modification) {
        Order order = getDeliveryGuyOrderById(deliveryGuyId, orderId);

        PaymentMode paymentMode = modification.getPaymentMode();
        OrderStatus orderStatus = modification.getOrderStatus();

        if (paymentMode == null) {
            throw new PaymentModeException("Delivery Guy cannot change Payment Mode.");
        }

        if (order.getOrderStatus().getDescription().equals("picked up") && orderStatus.getDescription().equals("delivered")) {
            order.setOrderStatus(OrderStatus.DELIVERED);
        } else {
            throw new OrderStatusException("Delivery guy cannot change status from " + order.getOrderStatus() + " to" + modification.getOrderStatus());
        }
        return orderRepository.saveAndFlush(order);
    }
}