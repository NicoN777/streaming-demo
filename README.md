## Streaming Demo
This repository contains the following main components:

* kafka
  - [x] Provide scripts for topic creation with partition-count and replication-factors
  - [ ] Provide scripts for topic deletion
  - [ ] Command cheat sheet
  - [ ] Provide a master starter-script that **installs and configures** Zookeeper and Kafka servers to run three Kafka servers locally
* rest-kafka-orders-producer
  * Spring Boot Web component that **simulates** an online **order** of a customer.
  * To build the code you need maven installed. Go to project root `mvn clean package`
  * To run `java -jar target/rest-kafka-product-order-0.0.1-SNAPSHOT.jar`
* rest-kafka-product-view-producer
  * Spring Boot Web component that **simulates** a customer product **view**. (Whenever a customer clicks on a product)
  * To build the code you need maven installed. Go to project root `mvn clean package`
  * To run `java -jar target/rest-kafka-product-view-producer-0.0.1-SNAPSHOT.jar`
* kafka-orders-aggregator
  * Spark Structured Streaming with Scala application that **simulates** _hottest_ products by the hour, and other **live** analytics.
  * To build you need Simple Build Tool to build Scala applications. Go to the project root `sbt package`
* kafka-orders-consumer
  * A Spring Boot Kafka consumer project that **consumes** from the orders topic and **outputs** to console.
