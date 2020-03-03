import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.types.{FloatType, IntegerType, StringType, StructType, TimestampType}

object KafkaProductViewsAggregator {

  def main(args:Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .master("local[*]")
      .appName("KafaOrdersAggregator")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    import spark.implicits._

    val productSchema = new StructType()
      .add("id", IntegerType)
      .add("brand", StringType)
      .add("name", StringType)
      .add("description", StringType)
      .add("price", FloatType)


    //Source
    val rawProductViews = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "product-views")
//      .option("startingOffsets", "earliest")
      .load()

    //Transformation (Business Logic)
    val productViews = rawProductViews
      .select($"key".cast(StringType),
        from_json($"value" cast StringType, productSchema) as "products",
        $"partition" cast IntegerType,
        'timestamp cast TimestampType
      )

    val hotProductsByHour = productViews
      .select($"products.*", $"timestamp")
      .withWatermark("timestamp", "2 hours")
      .groupBy($"id", $"brand", $"name", window($"timestamp", "1 hours"))
      .count()
      .orderBy($"count".desc)

    val hotProductsByHourJSON = hotProductsByHour
      .select('window.cast(StringType) as "key", to_json(struct('id, 'brand, 'name, 'count)) as "value")

    //Sink
    //Multiple Sinks? Why not.

    val A: Array[Int] = Array(1,2,3,4,5)
    A.map(a => a*a).sorted

    val jsonWriter = hotProductsByHourJSON.writeStream

    val consoleJsonStreamingQuery = hotProductsByHour
      .writeStream
      .format("console")
      .option("truncate", false)
      .outputMode("complete")
      .start()

    val consoleStreamingQuery = jsonWriter
      .format("console")
      .option("truncate", false)
      .outputMode("complete")
      .start()

    val kafkaStreamingQuery = jsonWriter
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("topic", "hottest-products-by-hour")
      .option("checkpointLocation", "/Users/nicolasnunez/data")
      .start()

    spark.streams.awaitAnyTermination()
  }
}
