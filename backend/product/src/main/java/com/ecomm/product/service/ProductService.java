package com.ecomm.product.service;

import com.ecomm.product.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> fetchAllProducts();
    long getCount();
}
