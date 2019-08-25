package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.model.DeliveryGuy;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.service.DeliveryGuyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<DeliveryGuy> getAllDeliveryGuys() {
        log.debug("Getting all Delivery Guys.");
        return deliveryGuyService.findAll();
    }

    @GetMapping(value = "/deliveryguys/{deliveryGuyId}")
    public DeliveryGuy getDeliveryGuy(@PathVariable Long deliveryGuyId) {
        log.debug("Getting Delivery Guy by id.");
        return deliveryGuyService.findById(deliveryGuyId);
    }

    @PostMapping(value = "/deliveryguys")
    public DeliveryGuy saveDeliveryGuy(@RequestBody DeliveryGuy deliveryGuy) {
        log.debug("Saving Delivery Guy.");
        return  deliveryGuyService.save(deliveryGuy);
    }

    @PutMapping(value = "/deliveryguys/{deliveryGuyId}")
    public DeliveryGuy updateDeliveryGuy(@RequestBody DeliveryGuy deliveryGuy, @PathVariable Long deliveryGuyId) {
        log.debug("Updating Delivery Guy by id.");
        return deliveryGuyService.update(deliveryGuy, deliveryGuyId);
    }

    @DeleteMapping(value = "/deliveryguys")
    public void deleteDeliveryGuy(@RequestBody DeliveryGuy deliveryGuy) {
        log.debug("Deleting Delivery Guy.");
        deliveryGuyService.delete(deliveryGuy);
    }

    @DeleteMapping(value = "/deliveryguys/{deliveryGuyId}")
    public void deleteDeliveryGuyById(@PathVariable Long deliveryGuyId) {
        log.debug("Deleting Delivery Guy by id.");
        deliveryGuyService.deleteById(deliveryGuyId);
    }

    @GetMapping("/deliveryguys/{deliveryGuyId}/orders")
    public List<Order> getDeliveryGuyOrders(@PathVariable Long deliveryGuyId) {
        return deliveryGuyService.getDeliveryGuyOrders(deliveryGuyId);
    }
}
