package com.finalassessment.ubinge.utility;

import com.finalassessment.ubinge.dto.*;
import com.finalassessment.ubinge.model.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class MapperUtil {
    public static Customer toCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setPhoneNo(customerDTO.getPhoneNo());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
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

    public static FoodItem toFoodItem(FoodItemDTO foodItemDTO, Restaurant restaurant) {
        FoodItem foodItem = new FoodItem();
        foodItem.setName(foodItemDTO.getName());
        foodItem.setPrice(foodItemDTO.getPrice());
        foodItem.setRestaurant(restaurant);
        return foodItem;
    }

    public static FoodItemDTO toFoodItemDTO(FoodItem foodItem) {
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        foodItemDTO.setId(foodItem.getId());
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
        Set<OrderFoodItemDTO> orderFoodItemDTOs = order.getOrderFoodItems().stream()
                .map(MapperUtil :: toOrderFoodItemDTO).collect(Collectors.toSet());
        orderDTO.setOrderFoodItemDTOs(orderFoodItemDTOs);
        orderDTO.setId(order.getId());
        return orderDTO;
    }

    public static Order toOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setPaymentMode(orderDTO.getPaymentMode());
        order.setTimestamp(LocalDateTime.now());
        return order;
    }

    private static Set<Long> getSetOfId(Set<? extends BaseEntity> objects) {
        return objects.stream().map(object -> object.getId()).collect(Collectors.toSet());
    }

    public static DeliveryGuy toDeliveryGuy(DeliveryGuyDTO deliveryGuyDTO) {
        DeliveryGuy deliveryGuy = new DeliveryGuy();
        deliveryGuy.setName(deliveryGuyDTO.getName());
        deliveryGuy.setEmail(deliveryGuyDTO.getEmail());
        deliveryGuy.setPhoneNo(deliveryGuyDTO.getPhoneNo());
        deliveryGuy.setPassword(deliveryGuyDTO.getPassword());
        return deliveryGuy;
    }

    public static DeliveryGuyDTO toDeliveryGuyDTO(DeliveryGuy deliveryGuy) {
        //TODO: use code reuse. this is duplicate code.
        DeliveryGuyDTO deliveryGuyDTO = new DeliveryGuyDTO();
        deliveryGuyDTO.setId(deliveryGuy.getId());
        deliveryGuyDTO.setEmail(deliveryGuy.getEmail());
        deliveryGuyDTO.setName(deliveryGuy.getName());
        deliveryGuyDTO.setPhoneNo(deliveryGuy.getPhoneNo());
        deliveryGuyDTO.setOrderIds(getSetOfId(deliveryGuy.getOrders()));
        return deliveryGuyDTO;
    }

    public static Restaurant toRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDTO.getName());
        restaurant.setPhoneNo(restaurantDTO.getPhoneNo());
        restaurant.setEmail(restaurantDTO.getEmail());
        restaurant.setPassword(restaurantDTO.getPassword());
        return restaurant;
    }

    public static RestaurantDTO toRestaurantDTO(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setPhoneNo(restaurant.getPhoneNo());
        restaurantDTO.setEmail(restaurant.getEmail());
        restaurantDTO.setOrderIds(getSetOfId(restaurant.getOrders()));
        restaurantDTO.setFoodItemIds(getSetOfId(restaurant.getFoodItems()));
        return restaurantDTO;
    }

    public static RestaurantOwnerDTO toRestaurantOwnerDTO(RestaurantOwner restaurantOwner) {
        RestaurantOwnerDTO restaurantOwnerDTO = new RestaurantOwnerDTO();
        restaurantOwnerDTO.setId(restaurantOwner.getId());
        restaurantOwnerDTO.setName(restaurantOwner.getName());
        restaurantOwnerDTO.setEmail(restaurantOwner.getEmail());
        restaurantOwnerDTO.setPhoneNo(restaurantOwner.getPhoneNo());
        restaurantOwnerDTO.setRestaurantIds(getSetOfId(restaurantOwner.getRestaurants()));
        return restaurantOwnerDTO;
    }

    public static RestaurantOwner toRestaurantOwner(RestaurantOwnerDTO restaurantOwnerDTO) {
        RestaurantOwner restaurantOwner = new RestaurantOwner();
        restaurantOwner.setName(restaurantOwnerDTO.getName());
        restaurantOwner.setEmail(restaurantOwnerDTO.getEmail());
        restaurantOwner.setPhoneNo(restaurantOwnerDTO.getPhoneNo());
        restaurantOwner.setPassword(restaurantOwnerDTO.getPassword());
        return restaurantOwner;
    }
}
