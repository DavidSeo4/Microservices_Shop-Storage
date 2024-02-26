package com.microservices.shop.shopservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "products_shop")
public class ProductEntity {

    public ProductEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String type;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "order_id")
    private OrderEntity order;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
