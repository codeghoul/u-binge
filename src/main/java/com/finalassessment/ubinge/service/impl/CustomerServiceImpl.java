package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.CustomerNotFoundException;
import com.finalassessment.ubinge.exception.OrderNotFoundException;
import com.finalassessment.ubinge.model.Customer;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.model.OrderStatus;
import com.finalassessment.ubinge.repository.CustomerRepository;
import com.finalassessment.ubinge.repository.OrderRepository;
import com.finalassessment.ubinge.service.CustomerService;
import com.finalassessment.ubinge.vo.OrderModificationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
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

    @Override
    public List<Order> getCustomerOrders(Long customerId) {
        Customer customer = findById(customerId);
        return customer.getOrders().stream().collect(Collectors.toList());
    }

    @Override
    public Order getCustomerOrderById(Long customerId, Long orderId) {
        Customer customer = findById(customerId);
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));

        if(!order.getCustomer().equals(customer)) {
            throw new IllegalArgumentException("Order does not belong to Customer.");
        }

        return order;
    }

    @Override
    public Order modifyOrder(Long customerId, Long orderId, OrderModificationVO modification) {
        Order order = getCustomerOrderById(customerId, orderId);

        if(!order.getOrderStatus().getDescription().equals("delivered") && modification.getOrderStatus().getDescription().equals("cancelled")) {
            order.setOrderStatus(OrderStatus.CANCELLED_BY_USER);
        }
        return orderRepository.saveAndFlush(order);
    }
}
