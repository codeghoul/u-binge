package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.model.Customer;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.service.CustomerService;
import com.finalassessment.ubinge.vo.GeneralDetailVO;
import com.finalassessment.ubinge.vo.OrderModificationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Customer>> getAllCustomers() {
        log.debug("Getting All Customers.");
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @GetMapping(value = "/customers/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId) {
        log.debug("Getting Customers By Id.");
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findById(customerId));
    }

    @GetMapping(value = "/customers/{customerId}/orders")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable Long customerId) {
        log.debug("Getting Customer Orders.");
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerOrders(customerId));
    }

    @GetMapping(value = "/customer/{customerId}/orders/{orderId}")
    public ResponseEntity<Order> getCustomerOrderById(@PathVariable Long customerId, @PathVariable Long orderId){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerOrderById(customerId, orderId));
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        log.debug("Saving Customer.");
        customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer));
    }

    @PutMapping(value = "/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody GeneralDetailVO generalDetailVO, @PathVariable Long customerId) {
        log.debug("Updating Customer.");
        return ResponseEntity.status(HttpStatus.OK).body(customerService.update(generalDetailVO, customerId));
    }

    @PutMapping(value = "/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<Order> modifyOrder(@PathVariable Long customerId, @PathVariable Long orderId, @RequestBody OrderModificationVO modification) {
        log.debug("Modifying Customer Order.");
        return ResponseEntity.status(HttpStatus.OK).body(customerService.modifyOrder(customerId, orderId, modification));
    }

    @DeleteMapping(value = "/customers")
    public ResponseEntity<?> deleteCustomer(@RequestBody Customer customer) {
        log.debug("Deleting Customer.");
        customerService.delete(customer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/customers/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long customerId) {
        log.debug("Deleting Customer By Id.");
        customerService.deleteById(customerId);
        return ResponseEntity.noContent().build();
    }
}
