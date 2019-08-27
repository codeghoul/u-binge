package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.dto.CustomerDTO;
import com.finalassessment.ubinge.dto.OrderDTO;
import com.finalassessment.ubinge.exception.CustomerNotFoundException;
import com.finalassessment.ubinge.exception.OrderNotFoundException;
import com.finalassessment.ubinge.model.Customer;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.repository.CustomerRepository;
import com.finalassessment.ubinge.repository.OrderRepository;
import com.finalassessment.ubinge.service.CustomerService;
import com.finalassessment.ubinge.utility.MapperUtil;
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
    public List<CustomerDTO> findAll() {
        log.debug("Returning Customer(s) from Service");
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(MapperUtil :: toCustomerDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findById(Long customerId) {
        log.debug("Returning Customer(s) by id from Service");
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        return MapperUtil.toCustomerDTO(customer);
    }

    @Override
    public CustomerDTO save(CustomerDTO newCustomer) {
        log.debug("Saving Customer(s) from Service");
        Customer customer = customerRepository.save(MapperUtil.toCustomer(newCustomer));
        return MapperUtil.toCustomerDTO(customer);
    }


    @Override
    public void delete(CustomerDTO customerDTO) {
        log.debug("Deleting Customer from Service.");
        Customer customer = MapperUtil.toCustomer(customerDTO);
        customerRepository.delete(customer);
    }

    @Override
    public void deleteById(Long customerId) {
        log.debug("Deleting Customer by Id from Service.");
        customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO, Long customerId) {
        log.debug("Updating Customer Details.");
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNo(customerDTO.getPhoneNo());
        customerRepository.save(customer);
        return MapperUtil.toCustomerDTO(customer);
    }

    @Override
    public List<OrderDTO> getCustomerOrders(Long customerId) {
        log.debug("Get all Customer Orders.");
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        return customer.getOrders().stream().map(order -> MapperUtil.toOrderDTO(order)).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getCustomerOrderById(Long customerId, Long orderId) {
        log.debug("Getting Customer order by id.");
        Customer customer = getCustomer(customerId);
        Order order = getOrder(orderId);
        if (!order.getCustomer().equals(customer)) {
            throw new OrderNotFoundException(orderId);
        }
        return MapperUtil.toOrderDTO(order);
    }

//    @Override
//    public Order modifyOrder(Long customerId, Long orderId, OrderModificationVO modification) {
//        log.debug("Modifying Order.");
//        Order order = getCustomerOrderById(customerId, orderId);
//
//        PaymentMode paymentMode = modification.getPaymentMode();
//        OrderStatus orderStatus = modification.getOrderStatus();
//
//        if (paymentMode != null) {
//            throw new PaymentModeException("Payment mode cannot be changed now.");
//        }
//
//        if (!order.getOrderStatus().getDescription().equals("delivered") && orderStatus.getDescription().equals("cancelled")) {
//            log.debug("Successfully changed order status.");
//            order.setOrderStatus(OrderStatus.CANCELLED_BY_USER);
//        } else {
//            throw new OrderStatusException("The order you are trying to cancel is already: " + order.getOrderStatus());
//        }
//
//        return orderRepository.save(order);
//    }

    private Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    private Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}
