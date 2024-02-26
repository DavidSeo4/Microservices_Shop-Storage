package com.microservices.shop.shopservice.proxys;

import com.microservices.shop.shopservice.models.ProductEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.List;

@FeignClient(name = "storage-service", url = "http://localhost:8080/storage")
public interface StorageServiceProxy {

    @PostMapping("/extractProducts")
    public List<ProductEntity> extractProducts(@RequestBody List<ProductEntity> products, @RequestHeader("Authorization") String token);

}
