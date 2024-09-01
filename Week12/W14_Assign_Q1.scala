import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.FloatType
import org.apache.spark.storage.StorageLevel
import org.apache.spark.sql.functions.broadcast
import org.apache.spark.sql.functions._

object W14_Assign_Q1 extends App{
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sparkConf = new SparkConf()
  sparkConf.set("spark.app.name", "Week_14_Assignment1")
  sparkConf.set("spark.master", "local[2]")
  
  val spark = SparkSession.builder()  
  .config(sparkConf)
  .getOrCreate()
  
  val orderSchema = StructType(List(
      StructField("order_id",IntegerType,true),
      StructField("order_date",StringType),
      StructField("order_customer_id",IntegerType),
      StructField("order_status",StringType)))
  
  val orderDF= spark.read
    .format("csv")
    .option("header", true)
    .schema(orderSchema)
    .option("path", "C:/Users/Vijay/Desktop/shared/week14/orders.txt")
    .load()
    
  //orderDF.show()
  //orderDF.printSchema()
  
  val customerSchema = StructType(List(
      StructField("order_item_id",IntegerType,true),
      StructField("order_item_order_id",IntegerType),
      StructField("order_item_product_id",IntegerType),
      StructField("order_item_quantity",IntegerType),
      StructField("order_item_subtotal",FloatType),
      StructField("order_item_product_price",FloatType)))
  
  val customerDF= spark.read
    .format("csv")
    .option("header", true)
    .schema(customerSchema)
    .option("path", "C:/Users/Vijay/Desktop/shared/week14/orders_item.txt")
    .load()
  
  //customerDF.show()
  //customerDF.printSchema()
    
    spark.sql("set spark.sql.autoBroadcastJoinThreshold = -1")
    
    val joinnedCondition = orderDF.col("order_id") === customerDF.col("order_item_order_id")
    
    val jointype = "inner"
    
    val joinedOrderDataDF = customerDF.join(broadcast(orderDF),joinnedCondition,jointype)
    .persist(StorageLevel.MEMORY_AND_DISK_SER)//.show()
    
    
    val dataFrameResult = joinedOrderDataDF
    .groupBy(to_date(col("order_date")).alias("order_formatted_date"), col("order_status"))
    .agg(
        round(sum("order_item_subtotal"), 2).alias("total_amount"),
        countDistinct("order_id").alias("total_orders"))
        .orderBy(col("order_formatted_date").desc,
            col("order_status"),col("total_amount").desc,col("total_orders")
            )
    dataFrameResult.show();
    
    
    //Now we have a joined result, lets implement the functionality using spark sql
  
    joinedOrderDataDF.createOrReplaceTempView("order_joined")
    
    val sqlResult= spark.sql("""
      select cast(to_date(order_date)as String) as order_formatted_date,
      order_status, cast(sum(order_item_subtotal) as DECIMAL (10,2)) as total_amount,
      count(distinct(order_id)) as total_orders 
      from order_joined
      group by to_date(order_date),order_status 
      order by order_formatted_date desc,order_status,total_amount desc, total_orders""")
      //.explain()  //<--it is EXPLAIN~PLAN FOR THE QUERY //Using "HASH AGGREGATION"
      
      
    sqlResult.show()
    
    scala.io.StdIn.readLine()
    
    spark.stop()  
}