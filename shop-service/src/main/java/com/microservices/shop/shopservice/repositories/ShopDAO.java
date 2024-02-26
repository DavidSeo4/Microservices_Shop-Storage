package com.microservices.shop.shopservice.repositories;

import com.microservices.shop.shopservice.models.OrderEntity;
import com.microservices.shop.shopservice.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShopDAO extends JpaRepository<ProductEntity, Long > {
    List<ProductEntity> findAllByType(String type);

    List<ProductEntity> findAllByOrder(OrderEntity order);
}
