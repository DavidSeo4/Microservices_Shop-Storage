package com.microservices.storage.storageservice.services;

import com.microservices.storage.storageservice.models.ProductEntity;

import java.util.List;

public interface StorageService {

    public ProductEntity getProductByType(String type);
    public ProductEntity saveProduct(ProductEntity product);
    public List<ProductEntity> getProductsByType(String type);
    public List<ProductEntity> getAllProducts();
    public void deleteProduct(ProductEntity product);
}
