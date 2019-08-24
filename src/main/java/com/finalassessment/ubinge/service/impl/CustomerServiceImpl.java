package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.CustomerNotFoundException;
import com.finalassessment.ubinge.model.Customer;
import com.finalassessment.ubinge.repository.CustomerRepository;
import com.finalassessment.ubinge.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        log.debug("Returning Customer(s) from Service");
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long customerId) {
        log.debug("Returning Customer(s) by id from Service");
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @Override
    public Customer save(Customer newCustomer) {
        log.debug("Saving Customer(s) from Service");
        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer update(Customer customer, Long customerId) {
        log.debug("Updating Customer(s) from Service");
        return null;
    }

    @Override
    public void delete(Customer customer) {
        log.debug("Deleting Customer from Service");
        customerRepository.delete(customer);
    }

    @Override
    public void deleteById(Long customerId) {
        log.debug("Deleting Customer by Id from Service");
        customerRepository.deleteById(customerId);
    }
}
