package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.model.DeliveryGuy;
import com.finalassessment.ubinge.service.DeliveryGuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeliveryGuyController {
    private DeliveryGuyService deliveryGuyService;

    @Autowired
    public DeliveryGuyController(DeliveryGuyService deliveryGuyService) {
        this.deliveryGuyService = deliveryGuyService;
    }

    @GetMapping(value = "/deliveryguys")
    public List<DeliveryGuy> getAllDeliveryGuys() {
        return deliveryGuyService.findAll();
    }

    @GetMapping(value = "/deliveryguys/{deliveryGuyId}")
    public DeliveryGuy getDeliveryGuy(@PathVariable Long deliveryGuyId) {
        return deliveryGuyService.findById(deliveryGuyId);
    }

    @PostMapping(value = "/deliveryguys")
    public DeliveryGuy saveDeliveryGuy(@RequestBody DeliveryGuy deliveryGuyId) {
        return  deliveryGuyService.save(deliveryGuyId);
    }

    @PutMapping(value = "/deliveryguys/{deliveryGuyId}")
    public DeliveryGuy updateDeliveryGuy(@RequestBody DeliveryGuy deliveryGuy, @PathVariable Long deliveryGuyId) {
        return deliveryGuyService.update(deliveryGuy, deliveryGuyId);
    }

    @DeleteMapping(value = "/deliveryguys")
    public void deleteDeliveryGuy(@RequestBody DeliveryGuy deliveryGuy) {
        deliveryGuyService.delete(deliveryGuy);
    }

    @DeleteMapping(value = "/deliveryguys/{deliveryGuyId}")
    public void deleteDeliveryGuyById(@PathVariable Long deliveryGuyId) {
        deliveryGuyService.deleteById(deliveryGuyId);
    }
}
