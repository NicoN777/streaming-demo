package com.github.nicon777.kafkaexampleconsumer.listener;

import com.github.nicon777.kafkaexampleconsumer.model.Order;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service

public class ConsoleStreamServiceImpl {
//    @KafkaListener(topics = "temp",
//            groupId = "test-group",
//            containerFactory = "kafkaListenerContainerFactory")
//    public void consume(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition){
//        System.out.println(message + " [From partition: " + partition + "]");
//    }

    @KafkaListener(topics = "orders",
            groupId = "stream-orders")
    public void consume(Order order){
        System.out.println(order);
    }

}
