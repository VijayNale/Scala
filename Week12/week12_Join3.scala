import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window


object week12_Join3 extends App{
  
  Logger.getLogger("org").setLevel(Level.ERROR)
 
  val sparkConf = new SparkConf
  sparkConf.set("spark-app.name", "first appication")
  sparkConf.set("spark.master", "local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
 
   val orderDf = spark.read
  .format("csv")
  .option("header", "true")
  .option("inferSchema", "true")
  .option("path", "C:/Users/Vijay/Desktop/shared/wek12/orders_join.csv")
  .load()
 
  //val ordersNew = orderDf.withColumnRenamed("customer_id", "cust_id")
  
  val customerDf = spark.read
  .format("csv")
  .option("header", "true")
  .option("inferSchema", "true")
  .option("path", "C:/Users/Vijay/Desktop/shared/wek12/customers.csv")
  .load()
 
  //orderDf.printSchema()
  //orderDf.show()
  //customerDf.printSchema()
  //customerDf.show()
  
  //join condition
  val joinCondition = orderDf.col("customer_id") === customerDf.col("customer_id")
  
  //type of join
  val jointype = "outer" //left,right,outer
  
  //joinin the 2 dataframes
  val joinedDf = orderDf.join(customerDf,joinCondition,jointype)
  .drop(orderDf.col("customer_id"))
  .select("order_id", "customer_id","customer_fname")
  .sort("order_id")
  .withColumn("order_id", expr("coalesce(order_id,-1)"))
 
  //ambiguity come when we are join same col from 2 dataset here we have use dropto handle this sistualtion
  
  joinedDf.show(1000)
  
  spark.stop()
}