package com.finalassessment.ubinge.exception;

public class CustomerNotFoundException extends NotFoundException {
    public CustomerNotFoundException(Long customerId) {
        super("No Customer with Id: " + customerId);
    }
}
