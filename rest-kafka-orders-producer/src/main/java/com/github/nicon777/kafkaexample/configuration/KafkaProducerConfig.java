//package com.github.nicon777.kafkaexample.configuration;
//
//import com.github.nicon777.kafkaexample.model.Order;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaProducerConfig {
//    @Bean
//    public ProducerFactory producerFactory() {
//        Map<String, Object> producerProperties = new HashMap<>();
//        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(producerProperties);
//    }
//    @Bean
//    public KafkaTemplate<String, Order> kafkaTemplate(){
//        KafkaTemplate kafkaTemplate = new KafkaTemplate<>(producerFactory());
//        kafkaTemplate.setDefaultTopic("orders");
//        return kafkaTemplate;
//    }
//}
