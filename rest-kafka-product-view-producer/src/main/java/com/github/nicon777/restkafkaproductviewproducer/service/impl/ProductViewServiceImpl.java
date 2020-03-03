package com.github.nicon777.restkafkaproductviewproducer.service.impl;

import com.github.nicon777.restkafkaproductviewproducer.model.Product;
import com.github.nicon777.restkafkaproductviewproducer.service.ProductViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ProductViewServiceImpl implements ProductViewService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final KafkaTemplate kafkaTemplate;

    @Autowired
    public ProductViewServiceImpl(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public int addProductView(Product product) {
        ListenableFuture future = kafkaTemplate.send("product-views", Integer.toString(product.getId()), product);
        future.addCallback(
                (o1)-> logger.info("Product-view sent to Kafka"),
                (throwable -> logger.info("No! Something went wrong... " + throwable.getMessage())));
        return 0;
    }

    @Override
    public int addProductViews(List<Product> products){
        products.forEach(product -> {
            ListenableFuture future = kafkaTemplate.send("product-views", Integer.toString(product.getId()), product);
                    future.addCallback(
                            (o1)-> logger.info("Product-view sent to Kafka"),
                            (throwable -> logger.info("No! Something went wrong... " + throwable.getMessage())));
            }
        );
        return 0;
    }
}
