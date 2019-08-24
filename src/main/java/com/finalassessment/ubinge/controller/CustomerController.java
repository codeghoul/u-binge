package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.model.Customer;
import com.finalassessment.ubinge.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/customers")
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping(value = "/customers/{customer_id}")
    public Customer getCustomer(@PathVariable Long customer_id) {
        return customerService.findById(customer_id);
    }

    @PostMapping(value = "/customers")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return  customerService.save(customer);
    }

    @PutMapping(value = "/customers/{customer_id}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long customer_id) {
        return customerService.update(customer, customer_id);
    }

    @DeleteMapping(value = "/customers")
    public void deleteCustomer(@RequestBody Customer customer) {
        customerService.delete(customer);
    }

    @DeleteMapping(value = "/customers/{customer_id}")
    public void deleteCustomerById(@PathVariable Long customer_id) {
        customerService.deleteById(customer_id);
    }
}
