package com.finalassessment.ubinge.service;

import com.finalassessment.ubinge.model.DeliveryGuy;
import com.finalassessment.ubinge.model.Order;

import java.util.List;

public interface DeliveryGuyService extends CrudService<DeliveryGuy, Long> {
    List<Order> getDeliveryGuyOrders(Long deliveryGuyId);
}
