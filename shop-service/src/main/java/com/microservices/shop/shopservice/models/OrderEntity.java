package com.microservices.shop.shopservice.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    public OrderEntity() {
    }

    public OrderEntity(List<ProductEntity> products) {
        this.products = products;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "requested_by")
    private String requestedBy;

    @OneToMany(mappedBy = "order")
    @Column(name = "products")
    private List<ProductEntity> products;

    @Column(name = "created_at")
    private Date createdAt;

    @PrePersist
    public void prePersist(){
        createdAt = new Date();
    }


    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", requestedBy='" + requestedBy + '\'' +
                ", products=" + products +
                ", createdAt=" + createdAt +
                '}';
    }
}
