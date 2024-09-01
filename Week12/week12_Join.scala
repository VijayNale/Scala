import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window


object week12_Join extends App{
  
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
  .option("path", "C:/Users/Vijay/Desktop/shared/wek12/orders.csv")
  .load()
 
  val customerDf = spark.read
  .format("csv")
  .option("header", "true")
  .option("inferSchema", "true")
  .option("path", "C:/Users/Vijay/Desktop/shared/wek12/customers.csv")
  .load()
 
  orderDf.printSchema()
  //orderDf.show()
  customerDf.printSchema()
  //customerDf.show()
  
  val joinCondition = orderDf.col("order_customer_id") === customerDf.col("customer_id")
  val jointype = "left" //left,right,outer
  val joinedDf = customerDf.join(orderDf)
  //.select("customer_id", "customer_fname","customer_id","order_customer_id")
  //.sort("order_customer_id")
  //.sort("customer_id")
  //.where("customer_id is null")
  
  joinedDf.show()
  
    
  spark.stop()
}