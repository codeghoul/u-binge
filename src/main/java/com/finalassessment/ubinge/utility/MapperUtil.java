package com.finalassessment.ubinge.utility;

import com.finalassessment.ubinge.dto.*;
import com.finalassessment.ubinge.model.*;

import java.util.Set;
import java.util.stream.Collectors;

public class MapperUtil {
    public static Customer toCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setPhoneNo(customerDTO.getPhoneNo());
        customer.setEmail(customerDTO.getEmail());
        return customer;
    }

    public static CustomerDTO toCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNo(customer.getPhoneNo());
        customerDTO.setOrderIds(getSetOfId(customer.getOrders()));
        return customerDTO;
    }

    public static FoodItem toFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItem = new FoodItem();
        foodItem.setName(foodItemDTO.getName());
        foodItem.setPrice(foodItemDTO.getPrice());
        return foodItem;
    }

    public static FoodItemDTO toFoodItemDTO(FoodItem foodItem) {
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        foodItemDTO.setName(foodItem.getName());
        foodItemDTO.setPrice(foodItem.getPrice());
        foodItemDTO.setRestaurantId(foodItem.getRestaurant().getId());
        return foodItemDTO;
    }

    public static OrderFoodItem toOrderFoodItem(OrderFoodItemDTO orderFoodItemDTO, FoodItem foodItem, Order order) {
        OrderFoodItem orderFoodItem = new OrderFoodItem();
        orderFoodItem.setFoodItem(foodItem);
        orderFoodItem.setOrder(order);
        orderFoodItem.setTotalPrice(orderFoodItemDTO.getTotalPrice());
        orderFoodItem.setQuantity(orderFoodItemDTO.getQuantity());
        return orderFoodItem;
    }

    public static OrderFoodItemDTO toOrderFoodItemDTO(OrderFoodItem orderFoodItem) {
        OrderFoodItemDTO orderFoodItemDTO = new OrderFoodItemDTO();
        orderFoodItemDTO.setId(orderFoodItem.getId());
        orderFoodItemDTO.setQuantity(orderFoodItem.getQuantity());
        orderFoodItemDTO.setTotalPrice(orderFoodItem.getTotalPrice());
        orderFoodItemDTO.setOrderId(orderFoodItem.getOrder().getId());
        orderFoodItemDTO.setFoodItemId(orderFoodItem.getFoodItem().getId());
        return orderFoodItemDTO;
    }

    public static OrderDTO toOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerId(order.getCustomer().getId());
        orderDTO.setRestaurantId(order.getRestaurant().getId());
        orderDTO.setDeliveryGuyId(order.getDeliveryGuy().getId());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setPaymentMode(order.getPaymentMode());
        orderDTO.setTimestamp(order.getTimestamp());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setOrderFoodItems(getSetOfId(order.getOrderFoodItems()));
        orderDTO.setId(order.getId());
        return orderDTO;
    }

    public static Order toOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setTimestamp(orderDTO.getTimestamp());
        return order;
    }

    private static Set<Long> getSetOfId(Set<? extends BaseEntity> objects) {
        return objects.stream().map(object -> ((BaseEntity) object).getId()).collect(Collectors.toSet());
    }

    public static DeliveryGuyDTO toDeliveryGuyDTO(DeliveryGuy deliveryGuy) {
        DeliveryGuyDTO deliveryGuyDTO = new DeliveryGuyDTO();
        deliveryGuyDTO.setId(deliveryGuy.getId());
        deliveryGuyDTO.setEmail(deliveryGuy.getEmail());
        deliveryGuyDTO.setName(deliveryGuy.getName());
        deliveryGuyDTO.setPhoneNo(deliveryGuy.getPhoneNo());
        return deliveryGuyDTO;
    }

    public static DeliveryGuy toDeliveryGuy(DeliveryGuyDTO deliveryGuyDTO) {
        DeliveryGuy deliveryGuy = new DeliveryGuy();
        deliveryGuy.setName(deliveryGuyDTO.getName());
        deliveryGuy.setEmail(deliveryGuyDTO.getEmail());
        deliveryGuy.setPhoneNo(deliveryGuyDTO.getName());
        return deliveryGuy;
    }
}
