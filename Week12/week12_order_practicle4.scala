import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.functions._
import org.apache.spark.sql._
import org.apache.spark.sql.types.DateType


object week12_order_practicle4 extends App {
  
  Logger.getLogger("org").setLevel(Level.ERROR)
 
  val sparkConf = new SparkConf
  sparkConf.set("spark-app.name", "first appication")
  sparkConf.set("spark.master", "local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
 
  val myList = List((1,"2013-07-25",11599,"CLOSED"),
      (2,"2014-07-25",256,"PENDING_PAYMENT"),
      (3,"2013-07-25",11599,"COMPLETE"),
      (4,"2019-07-25",8827,"CLOSED"))
  
  import spark.implicits._
  
  val ordersDf = spark.createDataFrame(myList)
  .toDF("order_id","orderdate","customerid","status")
  
  val newDf = ordersDf
  .withColumn("orderdate", unix_timestamp(col("orderdate")
  .cast(DateType)))
  .withColumn("newid", monotonically_increasing_id)
  .dropDuplicates("orderdate","customerid")
  .drop("order_id")
  .sort("orderdate")
    
  
  newDf.printSchema()
  
  newDf.show(false)
      
  spark.stop()
}