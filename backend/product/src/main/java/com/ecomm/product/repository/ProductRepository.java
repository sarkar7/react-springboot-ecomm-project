package com.ecomm.product.repository;

import com.ecomm.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Override
    List<Product> findAll();

    long count();

}
