package com.microservices.shop.shopservice.controllers;
import com.microservices.shop.shopservice.models.ProductEntity;
import com.microservices.shop.shopservice.services.impl.ShopServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private ShopServiceImpl shopService;

    public ShopController(ShopServiceImpl shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/getProducts")
    public List<ProductEntity> getAllProducts(){
        return shopService.getAllProducts();
    }

    @GetMapping("/getProductsByType")
    public List<ProductEntity> getProductsByType(@RequestBody String type){
        return shopService.getProductsByType(type);
    }

}
