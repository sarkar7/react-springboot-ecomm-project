package com.ecomm.product.model;

import lombok.Data;

@Data
public class Sku {

    private String sku;
    private String isbn;
    private String format;
    private String pages;
    private String published;
    private String price;

}
