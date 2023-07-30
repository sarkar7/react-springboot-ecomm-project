package com.ecomm.product.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
@Data
public class Product {

    @Id
    private String id;

    private String type;
    private String title;
    private String author;
    private List<Sku> skus;
    private List<String> categories;
    private String lastUpdate;

}
