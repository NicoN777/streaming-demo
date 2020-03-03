package com.github.nicon777.kafkaexampleconsumer.configuration;


import com.github.nicon777.kafkaexampleconsumer.model.Order;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Bean
//    public ConsumerFactory<String, String> consumerFactory(){
//        Map<String, Object> consumerConfigurations = new HashMap<>();
//        consumerConfigurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        consumerConfigurations.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
//        consumerConfigurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        consumerConfigurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(consumerConfigurations);
//    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
//        ConcurrentKafkaListenerContainerFactory<String, String> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//
    @Bean
    public ConsumerFactory<String, Order> consumerFactory() {
        JsonDeserializer<Order> orderJsonDeserializer = new JsonDeserializer<>(Order.class);
        orderJsonDeserializer.setRemoveTypeHeaders(false);
        orderJsonDeserializer.addTrustedPackages("*");
        orderJsonDeserializer.setUseTypeMapperForKey(true);

        Map<String, Object> consumerConfigurations = new HashMap<>();
        consumerConfigurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerConfigurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfigurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, orderJsonDeserializer);
        consumerConfigurations.put(ConsumerConfig.GROUP_ID_CONFIG, "stream-orders");
        consumerConfigurations.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(consumerConfigurations,
                new StringDeserializer(),
                orderJsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Order> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Order> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
        concurrentKafkaListenerContainerFactory.setErrorHandler((exception, consumerRecord) -> {
            logger.info("Listener error handler got exception: " + exception);

            logger.info("[Topic] " +consumerRecord.topic()
                    + " Key: " + consumerRecord.key()
                    + " ,Value: " + consumerRecord.value() + " was not sent");
        });
        concurrentKafkaListenerContainerFactory.setRecordFilterStrategy((consumerRecord ->
            consumerRecord.value().getCustomerName().equals("Nicolas Nunez")));
        return concurrentKafkaListenerContainerFactory;
    }

}
