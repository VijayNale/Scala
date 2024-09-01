import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.functions._

object week12_join4 extends App {
  
  val sparkConf = new SparkConf
  sparkConf.set("spark-app.name", "first appication")
  sparkConf.set("spark.master", "local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .enableHiveSupport()    //hive metastore to store table metadata (beause it will store in memory by default)
  .getOrCreate()
  
  //processing
  val ordersDf = spark.read
  .format("json")
  .option("path", "C:/Users/Vijay/Desktop/shared/wek12/order")
  .load()
  
   val custDf = spark.read
  .format("json")
  .option("path", "C:/Users/Vijay/Desktop/shared/wek12/customer")
  .load()  
  
  spark.sql("set spark.sql.autoBroadcastJoinThreshold = -1")
  
  //join condition
  val joinCondition = ordersDf.col("order_customer_id") === custDf.col("customer_id")
  
  //type of join
  val jointype = "inner" //left,right,outer
  
  //joinin the 2 dataframes
  val joinedDf = ordersDf.join(broadcast(custDf),joinCondition,jointype)
 
  //ambiguity come when we are join same col from 2 dataset here we have use dropto handle this sistualtion
  
  joinedDf.show()
  
  scala.io.StdIn.readLine()
  spark.stop()  
  
}