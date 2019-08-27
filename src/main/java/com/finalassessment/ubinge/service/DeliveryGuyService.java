package com.finalassessment.ubinge.service;

import com.finalassessment.ubinge.dto.DeliveryGuyDTO;
import com.finalassessment.ubinge.dto.OrderDTO;
import com.finalassessment.ubinge.dto.OrderModificationDTO;

import java.util.List;

public interface DeliveryGuyService extends CrudService<DeliveryGuyDTO, Long> {
    List<OrderDTO> getDeliveryGuyOrders(Long deliveryGuyId);

    OrderDTO getDeliveryGuyOrderById(Long deliveryGuyId, Long orderId);

    DeliveryGuyDTO update(DeliveryGuyDTO deliveryGuyDTO, Long deliveryGuyId);

    OrderDTO modifyOrder(Long deliveryGuyId, Long orderId, OrderModificationDTO modification);
}
