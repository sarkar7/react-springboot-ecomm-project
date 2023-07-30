package com.ecomm.product.service.impl;

import com.ecomm.product.model.Product;
import com.ecomm.product.repository.ProductRepository;
import com.ecomm.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> fetchAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public long getCount() {
        return productRepository.count();
    }
}
