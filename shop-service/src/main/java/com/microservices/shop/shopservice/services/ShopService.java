package com.microservices.shop.shopservice.services;

import com.microservices.shop.shopservice.models.OrderEntity;
import com.microservices.shop.shopservice.models.ProductEntity;

import java.util.List;

public interface ShopService {


    public List<ProductEntity> getAllProducts();

    public List<ProductEntity> getProductsByType(String type);

    public ProductEntity saveProduct(ProductEntity product);

    List<ProductEntity> getProductsByOrder(OrderEntity order);
}
