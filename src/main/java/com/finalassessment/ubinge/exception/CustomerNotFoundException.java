package com.finalassessment.ubinge.exception;

public class CustomerNotFoundException extends IllegalArgumentException {
    public CustomerNotFoundException(Long customerId) {
        super("No Customer with Id: " + customerId);
    }
}
