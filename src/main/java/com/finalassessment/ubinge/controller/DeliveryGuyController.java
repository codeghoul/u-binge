package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.model.DeliveryGuy;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.service.DeliveryGuyService;
import com.finalassessment.ubinge.vo.OrderModificationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<DeliveryGuy>> getAllDeliveryGuys() {
        log.debug("Getting all Delivery Guys.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryGuyService.findAll());
    }

    @GetMapping(value = "/deliveryguys/{deliveryGuyId}")
    public ResponseEntity<DeliveryGuy> getDeliveryGuy(@PathVariable Long deliveryGuyId) {
        log.debug("Getting Delivery Guy by id.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryGuyService.findById(deliveryGuyId));
    }

    @GetMapping(value = "/deliveryguys/{deliveryGuyId}/orders")
    public ResponseEntity<List<Order>> getDeliveryGuyOrders(@PathVariable Long deliveryGuyId) {
        log.debug("Getting Orders assigned to Delivery Guy.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryGuyService.getDeliveryGuyOrders(deliveryGuyId));
    }

    @GetMapping(value = "/deliveryguys/{deliveryGuyId}/orders/{orderId}")
    public ResponseEntity<Order> getDeliveryGuyOrderById(@PathVariable Long deliveryGuyId, @PathVariable Long orderId) {
        log.debug("Getting Order assigned to Delivery Guy using given OrderId.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryGuyService.getDeliveryGuyOrderById(deliveryGuyId, orderId));
    }

    @PostMapping(value = "/deliveryguys")
    public ResponseEntity<DeliveryGuy> saveDeliveryGuy(@RequestBody DeliveryGuy deliveryGuy) {
        log.debug("Saving Delivery Guy.");
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryGuyService.save(deliveryGuy));
    }

    @PutMapping(value = "/deliveryguys/{deliveryGuyId}")
    public ResponseEntity<DeliveryGuy> updateDeliveryGuy(@RequestBody DeliveryGuy deliveryGuy, @PathVariable Long deliveryGuyId) {
        log.debug("Updating Delivery Guy by id.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryGuyService.update(deliveryGuy, deliveryGuyId));
    }

    @PutMapping(value = "/deliveryguys/{deliveryGuyId}/orders/{orderId}")
    public ResponseEntity<Order> modifyOrder(@PathVariable Long deliveryGuyId, @PathVariable Long orderId, @RequestBody OrderModificationVO modification) {
        log.debug("Modifying Delivery Guy Order.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryGuyService.modifyOrder(deliveryGuyId, orderId, modification));
    }

    @DeleteMapping(value = "/deliveryguys")
    public ResponseEntity<?> deleteDeliveryGuy(@RequestBody DeliveryGuy deliveryGuy) {
        log.debug("Deleting Delivery Guy.");
        deliveryGuyService.delete(deliveryGuy);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/deliveryguys/{deliveryGuyId}")
    public ResponseEntity<?> deleteDeliveryGuyById(@PathVariable Long deliveryGuyId) {
        log.debug("Deleting Delivery Guy by id.");
        deliveryGuyService.deleteById(deliveryGuyId);
        return ResponseEntity.noContent().build();
    }
}
