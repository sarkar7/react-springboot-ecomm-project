package com.ecomm.product.controller;

import com.ecomm.product.config.ProductConfig;
import com.ecomm.product.model.Product;
import com.ecomm.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(ProductConfig.PRODUCT_API)
public class SearchController {

    @Autowired
    private ProductService productService;

    @GetMapping(ProductConfig.ALL)
    public ResponseEntity<List<Product>> fetchAll() {
        try {
            List<Product> products = new ArrayList<>(productService.fetchAllProducts());
            if (products.size() != 0)
                return new ResponseEntity<>(products, HttpStatus.OK);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Error occurred while fetching product info. \n", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(ProductConfig.COUNT)
    public long getTotalCount() {
        return productService.getCount();
    }

}
