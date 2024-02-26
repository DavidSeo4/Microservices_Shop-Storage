package com.microservices.storage.storageservice.services.impl;

import com.microservices.storage.storageservice.models.ProductEntity;
import com.microservices.storage.storageservice.repositories.StorageDao;
import com.microservices.storage.storageservice.services.StorageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    private final StorageDao storageDao;
    public StorageServiceImpl(StorageDao storageDao) {
        this.storageDao = storageDao;
    }


    @Override
    public ProductEntity getProductByType(String type) {
        return storageDao.findAllByType(type).stream().findFirst().orElse(null);
    }

    @Override
    public ProductEntity saveProduct(ProductEntity product) {
        return storageDao.save(product);
    }

    @Override
    public List<ProductEntity> getProductsByType(String type) {
        return storageDao.findAllByType(type);
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return storageDao.findAll();
    }

    @Override
    public void deleteProduct(ProductEntity product) {
        storageDao.delete(getProductByType(product.getType()));
    }
}
