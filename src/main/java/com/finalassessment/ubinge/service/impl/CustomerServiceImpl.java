package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.CustomerNotFoundException;
import com.finalassessment.ubinge.model.Customer;
import com.finalassessment.ubinge.repository.CustomerRepository;
import com.finalassessment.ubinge.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @Override
    public Customer save(Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer update(Customer customer, Long customerId) {
        return null;
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void deleteById(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
