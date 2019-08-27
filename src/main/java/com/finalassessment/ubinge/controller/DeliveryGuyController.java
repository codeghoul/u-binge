package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.dto.DeliveryGuyDTO;
import com.finalassessment.ubinge.dto.OrderDTO;
import com.finalassessment.ubinge.dto.OrderModificationDTO;
import com.finalassessment.ubinge.service.DeliveryGuyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class DeliveryGuyController {
    private DeliveryGuyService deliveryGuyService;

    @Autowired
    public DeliveryGuyController(DeliveryGuyService deliveryGuyService) {
        this.deliveryGuyService = deliveryGuyService;
    }

    @GetMapping(value = "/deliveryguys")
    public ResponseEntity<List<DeliveryGuyDTO>> getAllDeliveryGuys() {
        log.debug("Getting all Delivery Guys.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryGuyService.findAll());
    }

    @GetMapping(value = "/deliveryguys/{deliveryGuyId}")
    public ResponseEntity<DeliveryGuyDTO> getDeliveryGuy(@PathVariable Long deliveryGuyId) {
        log.debug("Getting Delivery Guy by id.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryGuyService.findById(deliveryGuyId));
    }

    @GetMapping(value = "/deliveryguys/{deliveryGuyId}/orders")
    public ResponseEntity<List<OrderDTO>> getDeliveryGuyOrders(@PathVariable Long deliveryGuyId) {
        log.debug("Getting Orders assigned to Delivery Guy.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryGuyService.getDeliveryGuyOrders(deliveryGuyId));
    }

    @GetMapping(value = "/deliveryguys/{deliveryGuyId}/orders/{orderId}")
    public ResponseEntity<OrderDTO> getDeliveryGuyOrderById(@PathVariable Long deliveryGuyId, @PathVariable Long orderId) {
        log.debug("Getting Order assigned to Delivery Guy using given OrderId.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryGuyService.getDeliveryGuyOrderById(deliveryGuyId, orderId));
    }

    @PostMapping(value = "/deliveryguys")
    public ResponseEntity<DeliveryGuyDTO> saveDeliveryGuy(@Valid @RequestBody DeliveryGuyDTO deliveryGuyDTO) {
        log.debug("Saving Delivery Guy.");
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryGuyService.save(deliveryGuyDTO));
    }

    @PutMapping(value = "/deliveryguys/{deliveryGuyId}")
    public ResponseEntity<DeliveryGuyDTO> updateDeliveryGuy(@Valid @RequestBody DeliveryGuyDTO deliveryGuyDTO, @PathVariable Long deliveryGuyId) {
        log.debug("Updating Delivery Guy by id.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryGuyService.update(deliveryGuyDTO, deliveryGuyId));
    }

    @PutMapping(value = "/deliveryguys/{deliveryGuyId}/orders/{orderId}")
    public ResponseEntity<OrderDTO> modifyOrder(@PathVariable Long deliveryGuyId, @PathVariable Long orderId, @Valid @RequestBody OrderModificationDTO modification) {
        log.debug("Modifying Delivery Guy Order.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryGuyService.modifyOrder(deliveryGuyId, orderId, modification));
    }

    @DeleteMapping(value = "/deliveryguys/{deliveryGuyId}")
    public ResponseEntity<?> deleteDeliveryGuyById(@PathVariable Long deliveryGuyId) {
        log.debug("Deleting Delivery Guy by id.");
        deliveryGuyService.deleteById(deliveryGuyId);
        return ResponseEntity.noContent().build();
    }
}
