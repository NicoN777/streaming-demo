# Author: Nicolas Nunez
# This is only for demo purposes, change partitions and replication factor according
# to throughput and/or your cluster/ broker specifications
kafka-topics --bootstrap-server localhost:9092 --create --topic orders --partitions 6 --replication-factor 1
kafka-topics --bootstrap-server localhost:9092 --create --topic users --partitions 6 --replication-factor 1
kafka-topics --bootstrap-server localhost:9092 --create --topic product-views --partitions 6 --replication-factor 1
kafka-topics --bootstrap-server localhost:9092 --create --topic hot-products-by-hour --partitions 6 --replication-factor 1
