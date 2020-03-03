package com.github.nicon777.kafkaexample.resource;


import com.github.nicon777.kafkaexample.model.Order;
import com.github.nicon777.kafkaexample.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderResource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final OrderService orderService;

    @Autowired
    public OrderResource(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity addOrder(@RequestBody Order order){
        try{
            orderService.addOrder(order);
        }catch (Exception e){
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
