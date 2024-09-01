import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.sql.Row
import org.apache.spark.sql.Dataset
import java.sql.Timestamp
import java.text.Format

case class OrderData(order_id:Int, order_date:Timestamp, order_customer_id:Int, order_Status:String)

object DataframeExample extends App {

  //Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sparkConf = new SparkConf
  sparkConf.set("spark-app.name", "my first appication")
  sparkConf.set("spark.master", "local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  //Dataframe reader (dataset of row type)
  /*
  val ordersDf = spark.read
  .format("csv")
  .option("header", true)
  .option("inferSchema", true)
  .option("path","C:/Users/Vijay/Desktop/shared/week11/orders.csv")
  .load()
  */
  
  /*
  val ordersDf = spark.read
  .format("json")
  .option("path","C:/Users/Vijay/Desktop/shared/week11/players.json")
  .option("mode", "FAILFAST")
  .load()
  */
  
  val ordersDf = spark.read
  .option("path","C:/Users/Vijay/Desktop/shared/week11/users.parquet")
  .load()
  
  ordersDf.printSchema
  
  ordersDf.show(false)
   
    
  //always import after spark session
  //import spark.implicits._   //converting dataset <-> dataframe  ..vicevarsa
  //val ordersDs = ordersDf.as[OrderData]
  
  //ordersDs.filter(x => x.order_id < 10)  
  //ordersDf.filter("order_ids < 10").show
  
  
  scala.io.StdIn.readLine()
  spark.stop()
}