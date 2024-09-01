import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.log4j.Level
import org.apache.log4j.Logger


object DataFrame extends App {

  //Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sparkConf = new SparkConf
  sparkConf.set("spark-app.name", "my first appication")
  sparkConf.set("spark.master", "local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  //processing
  val ordersDf = spark.read
  .option("header", true)
  .option("inferSchema", true)
  .csv("C:/Users/Vijay/Desktop/shared/week11/orders.csv")
  
  
  val groupOrderDf = ordersDf
  .repartition(4)
  .where("order_customer_id > 1000")
  .select("order_id", "order_customer_id")
  .groupBy("order_customer_id")
  .count()
  
  groupOrderDf.foreach(x => {
    println(x)
  })
  
  groupOrderDf.show()
  
  Logger.getLogger(getClass.getName).info("my application is completed succesfully")
  
  //ordersDf.show()  //by default give you 20 rows
  //ordersDf.printSchema()
  
  scala.io.StdIn.readLine()
  spark.stop()
}