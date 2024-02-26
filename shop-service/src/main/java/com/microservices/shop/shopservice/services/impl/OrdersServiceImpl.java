package com.microservices.shop.shopservice.services.impl;

import com.microservices.shop.shopservice.models.OrderEntity;
import com.microservices.shop.shopservice.repositories.OrdersDAO;
import com.microservices.shop.shopservice.services.OrdersService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    private OrdersDAO ordersDAO;
    public OrdersServiceImpl(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return ordersDAO.findAll();
    }

    @Override
    public List<OrderEntity> getOrdersByEmployee(String employee) {
        return ordersDAO.findAllByRequestedBy(employee);
    }

    @Override
    public OrderEntity createOrder(OrderEntity orderEntity) {
        return ordersDAO.save(orderEntity);
    }
}
