package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.dto.CustomerDTO;
import com.finalassessment.ubinge.dto.OrderDTO;
import com.finalassessment.ubinge.dto.OrderModificationDTO;
import com.finalassessment.ubinge.service.CustomerService;
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
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        log.debug("Getting All Customers.");
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @GetMapping(value = "/customers/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long customerId) {
        log.debug("Getting Customers By Id.");
        CustomerDTO customerDTO = customerService.findById(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @GetMapping(value = "/customers/{customerId}/orders")
    public ResponseEntity<List<OrderDTO>> getCustomerOrders(@PathVariable Long customerId) {
        log.debug("Getting Customer Orders.");
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerOrders(customerId));
    }


    @GetMapping(value = "/customer/{customerId}/orders/{orderId}")
    public ResponseEntity<OrderDTO> getCustomerOrderById(@PathVariable Long customerId, @PathVariable Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerOrderById(customerId, orderId));
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        log.debug("Saving Customer.");
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customerDTO));
    }

    @PutMapping(value = "/customers/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long customerId) {
        log.debug("Updating Customer.");
        return ResponseEntity.status(HttpStatus.OK).body(customerService.update(customerDTO, customerId));
    }

    @DeleteMapping(value = "/customers/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long customerId) {
        log.debug("Deleting Customer By Id.");
        customerService.deleteById(customerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<OrderDTO> modifyOrder(@PathVariable Long customerId, @PathVariable Long orderId, @RequestBody OrderModificationDTO modification) {
        log.debug("Modifying Customer Order.");
        return ResponseEntity.status(HttpStatus.OK).body(customerService.modifyOrder(customerId, orderId, modification));
    }

}
