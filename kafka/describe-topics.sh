topics=$(kafka-topics --bootstrap-server localhost:9092 --list)
for i in "${topics[@]}"
do
    kafka-topics --bootstrap-server localhost:9092 --topic ${i} --describe > topic-${i}-description
done
