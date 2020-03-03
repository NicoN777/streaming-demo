import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._

object KafkaOrdersAggregator extends App {
  val spark = SparkSession
    .builder
    .appName("KafaOrdersAggregator")
    .master("local[*]")
    .getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")


  import spark.implicits._

  val reviewSchema = new StructType(Array(
    new StructField("id", IntegerType, false),
    new StructField("title", StringType, false),
    new StructField("body", StringType, false),
    new StructField("date", TimestampType, false),
    new StructField("likes", IntegerType, false),
    new StructField("useful", IntegerType, false))
  )

  val productSchema = new StructType(Array(
    new StructField("id", IntegerType, false),
    new StructField("brand", StringType, false),
    new StructField("name", StringType, false),
    new StructField("description", StringType, false),
    new StructField("price", FloatType, false),
    new StructField("reviews", ArrayType(reviewSchema)))
  )

  val orderSchema = new StructType(Array(
    new StructField("id", IntegerType, false),
    new StructField("customerName", StringType, false),
    new StructField("shippingAddress", StringType, false),
    new StructField("products", ArrayType(productSchema)),
    new StructField("total", FloatType, false),
    new StructField("date", TimestampType, false)
  ))


  val rawOrdersData = spark
    .readStream
    .format("kafka")
//    .option("multiLine", "true")
    .options(Map(
      "kafka.bootstrap.servers" -> "localhost:9092",
      "subscribe" -> "orders"
//      "startingOffsets" -> "earliest"
    ))
    .load()

  rawOrdersData.printSchema()
  val ordersData = rawOrdersData
    .filter($"key".isNotNull)
    .withColumn("key", $"key".cast(StringType))
    .withColumn("value", $"value".cast(StringType))
    .withColumn("topic", $"topic".cast(IntegerType))
    .withColumn("offset", $"offset".cast(LongType))
    .withColumn("timestamp", $"timestamp".cast(TimestampType))
    .select("key", "value", "timestamp")


    val ordersDataDF = ordersData
      .select(from_json(col("value"), orderSchema).as("data"), $"timestamp")
      .select("data.*", "timestamp")
//    .select(from_json($"value", schema).alias("value"))

  val productsBoughtLastHourDF = ordersDataDF
    .select(explode($"products")  as "products",  $"timestamp")
    .select("products.*", "timestamp")
    .drop($"reviews")
    .withWatermark("timestamp", "5 hours")
    .groupBy($"brand", $"name", window($"timestamp", "1 hours"))
      .count()

  //    .groupBy('brand)
//      .count()
//    .agg(window($"timestamp", "6 hours"))

  productsBoughtLastHourDF
    .writeStream
    .outputMode("append")
    .format("console")
    .option("truncate", false)
    .start()
    .awaitTermination()
}
