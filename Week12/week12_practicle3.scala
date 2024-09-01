import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.functions._

object week12_practicle3 extends App{
    
  Logger.getLogger("org").setLevel(Level.ERROR)
  
    
  val sparkConf = new SparkConf
  sparkConf.set("spark-app.name", "first appication")
  sparkConf.set("spark.master", "local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  //processing
  val ordersDf = spark.read
  .format("csv")
  .option("header", true)
  .option("inferSchema", true)
  .option("path", "C:/Users/Vijay/Desktop/shared/wek12/orders.csv")
  .load()
  
  //ordersDf.select("order_id", "order_status").show()
  
  import spark.implicits._
     
  //ordersDf.select(column("order_id"),col("order_date"),$"order_customer_id", 'order_status).show
   
  //ordersDf.select(col("order_id"),column("order_date"),expr("concat(order_status,'_STATUS')")).show(false)
  
  
  ordersDf.selectExpr("order_id","order_date","concat(order_status,'_STATUS')").show(false)
  
  
  
  scala.io.StdIn.readLine()
  spark.stop()
  
}