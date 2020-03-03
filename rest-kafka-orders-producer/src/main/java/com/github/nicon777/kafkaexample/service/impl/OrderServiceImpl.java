package com.github.nicon777.kafkaexample.service.impl;

import com.github.nicon777.kafkaexample.model.Order;
import com.github.nicon777.kafkaexample.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class OrderServiceImpl implements OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    public OrderServiceImpl(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public int addOrder(Order order) {
        ListenableFuture result = kafkaTemplate.send("orders", Integer.toString(order.getId()), order);
        logger.info(order.toString());
        result.addCallback(
                o1 -> {
                    logger.info("Order sent to Kafka");
                    },
                throwable -> {
                    logger.info("Something went wrong! " + throwable.getMessage());
                });
        return 0;
    }
}
