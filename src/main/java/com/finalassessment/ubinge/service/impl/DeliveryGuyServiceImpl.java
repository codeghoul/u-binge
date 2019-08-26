package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.DeliveryGuyNotFoundException;
import com.finalassessment.ubinge.exception.OrderNotFoundException;
import com.finalassessment.ubinge.model.DeliveryGuy;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.model.OrderStatus;
import com.finalassessment.ubinge.repository.DeliveryGuyRepository;
import com.finalassessment.ubinge.repository.OrderRepository;
import com.finalassessment.ubinge.service.DeliveryGuyService;
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
    public DeliveryGuy update(DeliveryGuy deliveryGuy, Long deliveryGuyId) {
        log.debug("Updating Delivery Guy from Service");
        return null;
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
        DeliveryGuy deliveryGuy = deliveryGuyRepository.findById(deliveryGuyId).orElseThrow(() -> new DeliveryGuyNotFoundException(deliveryGuyId));
        return deliveryGuy.getOrders().stream().collect(Collectors.toList());
    }

    @Override
    public Order getDeliveryGuyOrderById(Long deliveryGuyId, Long orderId) {
        DeliveryGuy deliveryGuy = findById(deliveryGuyId);
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));

        if (!order.getDeliveryGuy().equals(deliveryGuy)) {
            throw new IllegalArgumentException("Order does not belong this Deliver Guy.");
        }

        return order;
    }

    @Override
    public Order modifyOrder(Long deliveryGuyId, Long orderId, OrderModificationVO modification) {
        Order order = getDeliveryGuyOrderById(deliveryGuyId, orderId);

        if(order.getOrderStatus().getDescription().equals("picked up") && modification.getOrderStatus().getDescription().equals("delivered")) {
            order.setOrderStatus(OrderStatus.DELIVERED);
        } else {
            throw new IllegalArgumentException("Delivery guy cannot change status from " + order.getOrderStatus() + " to" + modification.getOrderStatus());
        }
        return orderRepository.saveAndFlush(order);
    }
}