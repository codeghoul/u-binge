package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.DeliveryGuyNotFoundException;
import com.finalassessment.ubinge.model.DeliveryGuy;
import com.finalassessment.ubinge.repository.DeliveryGuyRepository;
import com.finalassessment.ubinge.service.DeliveryGuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryGuyServiceImpl implements DeliveryGuyService {
    private DeliveryGuyRepository deliveryGuyRepository;

    @Autowired
    public DeliveryGuyServiceImpl(DeliveryGuyRepository deliveryGuyRepository) {
        this.deliveryGuyRepository = deliveryGuyRepository;
    }

    @Override
    public List<DeliveryGuy> findAll() {
        return deliveryGuyRepository.findAll();
    }

    @Override
    public DeliveryGuy findById(Long deliveryGuyId) {
        return deliveryGuyRepository.findById(deliveryGuyId).orElseThrow(() -> new DeliveryGuyNotFoundException(deliveryGuyId));
    }

    @Override
    public DeliveryGuy save(DeliveryGuy newDeliveryGuy) {
        return deliveryGuyRepository.save(newDeliveryGuy);
    }

    @Override
    public DeliveryGuy update(DeliveryGuy deliveryGuy, Long deliveryGuyId) {
        return null;
    }

    @Override
    public void delete(DeliveryGuy deliveryGuy) {
        deliveryGuyRepository.delete(deliveryGuy);
    }

    @Override
    public void deleteById(Long deliveryGuyId) {
        deliveryGuyRepository.deleteById(deliveryGuyId);
    }
}