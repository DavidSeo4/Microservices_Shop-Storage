package com.microservices.storage.storageservice.repositories;

import com.microservices.storage.storageservice.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StorageDao extends JpaRepository<ProductEntity, Long > {

    List<ProductEntity> findAllByType(String type);

}
