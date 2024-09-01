

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.TimestampType
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.StructField
import java.sql.Timestamp

object SchamaDefining extends App {
  
  case class Orders(orderid:Int, orderdate:Timestamp,custid:Int, orderstatus:String)
    
  val sparkConf = new SparkConf
  sparkConf.set("spark-app.name", "my first appication")
  sparkConf.set("spark.master", "local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  //Explicitly Defining Schema when we are working with Dataframe
  //StructType (spark type)
  /*
  val ordersSchema = StructType(List(
  StructField("orderid",IntegerType),
  StructField("orderdate",TimestampType),
  StructField("customerid",IntegerType),
  StructField("status",StringType)
  ))
  */
  
  //DDL string  (scala Type)
  val orderSchemaDDL = "orderid Int, orderdate String, custid Int, orderstatus String"
  
  
  val ordersDf = spark.read
  .format("csv")
  .option("header", true)
  .schema(orderSchemaDDL)
  .option("path","C:/Users/Vijay/Desktop/shared/week11/orders.csv")
  .load()
  
  import spark.implicits._
  
  //datatfram to dataset ...by case class
  val orederDS = ordersDf.as[Orders] 
   
  ordersDf.printSchema
  
  ordersDf.show(false)
  
  scala.io.StdIn.readLine()
  spark.stop()
}