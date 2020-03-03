package com.github.nicon777.kafkaexampleconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class KafkaOrdersConsumer {

    public static void main(String[] args) {
        SpringApplication.run(KafkaOrdersConsumer.class, args);
    }

}
