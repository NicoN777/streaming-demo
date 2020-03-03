package com.github.nicon777.restkafkaproductviewproducer.service;

import com.github.nicon777.restkafkaproductviewproducer.model.Product;

import java.util.List;

public interface ProductViewService {
    int addProductView(Product product);
    int addProductViews(List<Product> products);
}
