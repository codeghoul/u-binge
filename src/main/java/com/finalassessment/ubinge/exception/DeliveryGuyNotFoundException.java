package com.finalassessment.ubinge.exception;

public class DeliveryGuyNotFoundException extends IllegalArgumentException {
    public DeliveryGuyNotFoundException(Long deliveryGuyId) {
        super("No Delivery Guy with Id: " + deliveryGuyId);
    }
}
