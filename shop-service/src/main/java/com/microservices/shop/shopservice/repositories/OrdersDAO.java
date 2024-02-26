package com.microservices.shop.shopservice.repositories;

import com.microservices.shop.shopservice.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDAO extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByRequestedBy(String employee);
}
