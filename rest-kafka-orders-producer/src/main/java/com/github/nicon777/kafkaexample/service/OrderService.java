package com.github.nicon777.kafkaexample.service;

import com.github.nicon777.kafkaexample.model.Order;

public interface OrderService {
    int addOrder(Order order);
}
