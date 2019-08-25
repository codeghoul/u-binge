package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.model.Customer;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/customers")
    public List<Customer> getAllCustomers() {
        log.debug("Getting All Customers.");
        return customerService.findAll();
    }

    @GetMapping(value = "/customers/{customerId}")
    public Customer getCustomer(@PathVariable Long customerId) {
        log.debug("Getting Customers By Id.");
        return customerService.findById(customerId);
    }

    @PostMapping(value = "/customers")
    public Customer saveCustomer(@RequestBody Customer customer) {
        log.debug("Saving Customer.");
        return  customerService.save(customer);
    }

    @PutMapping(value = "/customers/{customerId}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId) {
        log.debug("Updating Customer.");
        return customerService.update(customer, customerId);
    }

    @DeleteMapping(value = "/customers")
    public void deleteCustomer(@RequestBody Customer customer) {
        log.debug("Deleting Customer.");
        customerService.delete(customer);
    }

    @DeleteMapping(value = "/customers/{customerId}")
    public void deleteCustomerById(@PathVariable Long customerId) {
        log.debug("Deleting Customer By Id.");
        customerService.deleteById(customerId);
    }

    @GetMapping(value = "/customers/{customerId}/orders")
    public List<Order> getCustomerOrders(@PathVariable Long customerId) {
        return customerService.getCustomerOrders(customerId);
    }
}
