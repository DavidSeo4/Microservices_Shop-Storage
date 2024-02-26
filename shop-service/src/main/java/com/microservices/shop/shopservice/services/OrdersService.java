package com.microservices.shop.shopservice.services;

import com.microservices.shop.shopservice.models.OrderEntity;
import com.microservices.shop.shopservice.models.ProductEntity;

import java.security.Principal;
import java.util.List;

public interface OrdersService {

    public List<OrderEntity> getAllOrders();

    public List<OrderEntity> getOrdersByEmployee(String employee);

    public OrderEntity createOrder(OrderEntity orderEntity);


}
