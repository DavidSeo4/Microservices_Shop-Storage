package com.microservices.shop.shopservice.controllers;

import com.microservices.shop.shopservice.models.OrderEntity;
import com.microservices.shop.shopservice.models.ProductEntity;
import com.microservices.shop.shopservice.proxys.StorageServiceProxy;
import com.microservices.shop.shopservice.services.impl.OrdersServiceImpl;
import com.microservices.shop.shopservice.services.impl.ShopServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private OrdersServiceImpl ordersService;
    private ShopServiceImpl shopService;
    private final StorageServiceProxy storageServiceProxy;


    public OrdersController(OrdersServiceImpl ordersService, ShopServiceImpl shopService,StorageServiceProxy storageServiceProxy) {
        this.ordersService = ordersService;
        this.shopService = shopService;
        this.storageServiceProxy = storageServiceProxy;
    }

    @GetMapping("/getAll")
    public List<OrderEntity> getAll(){

        List<OrderEntity> listOrders = ordersService.getAllOrders();
        for (OrderEntity order : listOrders){
            order.setProducts(shopService.getProductsByOrder(order));
        }
        return listOrders;
    }

    @PostMapping("/create")
    public OrderEntity createOrder(@RequestBody List<ProductEntity> products, HttpServletRequest request){

        final String token = request.getHeader("Authorization");
        String tokenNoDobleBearer = token.split(" ")[1];

        List<ProductEntity> productsExtractedFromStorage = storageServiceProxy.extractProducts(products, tokenNoDobleBearer);
        productsExtractedFromStorage.forEach(m-> System.out.println("Product extracted from storage: "+ m.getType()));

        OrderEntity order = ordersService.createOrder(new OrderEntity(productsExtractedFromStorage));
        RestTemplate restTemplate = new RestTemplate();
        order.setRequestedBy(restTemplate.getForObject("http://localhost:8080/auth/getAuthUsername/{token}", String.class, Map.of("token", token)));
        for (ProductEntity product: productsExtractedFromStorage){
            product.setOrder(order);
            shopService.saveProduct(product);
        }
        return ordersService.createOrder(order);
    }

}
