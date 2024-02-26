package com.microservices.storage.storageservice.controllers;

import com.microservices.storage.storageservice.models.ProductEntity;
import com.microservices.storage.storageservice.services.impl.StorageServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/storage")
public class StorageController {

    private StorageServiceImpl storageService;

    public StorageController(StorageServiceImpl storageService) {
        this.storageService = storageService;
    }

    public ProductEntity getProduct(String type){
        ProductEntity product = storageService.getProductByType(type);
        return product;
    }

    @GetMapping("/getProducts")
    public List<ProductEntity> getAllProducts(){
        return storageService.getAllProducts();
    }

    @PostMapping("/extractProducts")
    public List<ProductEntity> extractProducts(@RequestBody List<ProductEntity> products){

        List<ProductEntity> productsExtracted = new ArrayList<>();

        for (ProductEntity product: products){
            ProductEntity extractedProductFromStorage = getProduct(product.getType());
            if (extractedProductFromStorage!=null){
                productsExtracted.add(extractedProductFromStorage);
                storageService.deleteProduct(product);
            }
        }
        return productsExtracted;
    }

    @PostMapping("/addProducts")
    public List<ProductEntity> addProducts(@RequestBody List<ProductEntity> products){
        for (ProductEntity product: products){
            storageService.saveProduct(product);
        }
        return storageService.getAllProducts();
    }



}
