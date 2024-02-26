package com.microservices.shop.shopservice.services.impl;

import com.microservices.shop.shopservice.models.OrderEntity;
import com.microservices.shop.shopservice.models.ProductEntity;
import com.microservices.shop.shopservice.repositories.ShopDAO;
import com.microservices.shop.shopservice.services.ShopService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ShopServiceImpl implements ShopService {

    private final ShopDAO shopDAO;

    public ShopServiceImpl(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return shopDAO.findAll();
    }

    @Override
    public List<ProductEntity> getProductsByType(String type) {
        return shopDAO.findAllByType(type);
    }

    @Override
    public ProductEntity saveProduct(ProductEntity product) {
        return shopDAO.save(product);
    }

    @Override
    public List<ProductEntity> getProductsByOrder(OrderEntity order) {
        return shopDAO.findAllByOrder(order);
    }

}
