package com.finalassessment.ubinge.service;

import com.finalassessment.ubinge.dto.DeliveryGuyDTO;
import com.finalassessment.ubinge.dto.OrderDTO;

import java.util.List;

public interface DeliveryGuyService extends CrudService<DeliveryGuyDTO, Long> {
    List<OrderDTO> getDeliveryGuyOrders(Long deliveryGuyId);

    OrderDTO getDeliveryGuyOrderById(Long deliveryGuyId, Long orderId);

//    OrderDTO modifyOrder(Long deliveryGuyId, Long orderId, OrderModificationVO modification);

    DeliveryGuyDTO update(DeliveryGuyDTO deliveryGuyDTO, Long deliveryGuyId);
}
