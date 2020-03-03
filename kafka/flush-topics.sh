kafka-configs --zookeeper localhost:2181 --entity-type topics --entity-name orders --add-config retention.ms=1000 --alter

kafka-configs --zookeeper localhost:2181 --entity-type topics --entity-name orders --delete-config retention.ms --alter
