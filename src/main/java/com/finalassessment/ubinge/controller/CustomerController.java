package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.model.Customer;
import com.finalassessment.ubinge.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
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

    @GetMapping(value = "/customers/{customer_id}")
    public Customer getCustomer(@PathVariable Long customer_id) {
        log.debug("Getting Customers By Id.");
        return customerService.findById(customer_id);
    }

    @PostMapping(value = "/customers")
    public Customer saveCustomer(@RequestBody Customer customer) {
        log.debug("Saving Customer.");
        return  customerService.save(customer);
    }

    @PutMapping(value = "/customers/{customer_id}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long customer_id) {
        log.debug("Updating Customer.");
        return customerService.update(customer, customer_id);
    }

    @DeleteMapping(value = "/customers")
    public void deleteCustomer(@RequestBody Customer customer) {
        log.debug("Deleting Customer.");
        customerService.delete(customer);
    }

    @DeleteMapping(value = "/customers/{customer_id}")
    public void deleteCustomerById(@PathVariable Long customer_id) {
        log.info("Deleting Customer By Id.");
        customerService.deleteById(customer_id);
    }
}
