import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.sql.SaveMode

object week12_json extends App {
  
  val sparkConf = new SparkConf
  sparkConf.set("spark-app.name", "first appication")
  sparkConf.set("spark.master", "local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .enableHiveSupport()    //hive metastore to store table metadata (beause it will store in memory by default)
  .getOrCreate()
  
  //processing
  val ordersDf = spark.read
  .format("csv")
  .option("header", true)
  .option("inferSchema", true)
  .option("path", "C:/Users/Vijay/Desktop/shared/wek12/orders.csv")
  .load()
  
   val custDf = spark.read
  .format("csv")
  .option("header", true)
  .option("inferSchema", true)
  .option("path", "C:/Users/Vijay/Desktop/shared/wek12/customers.csv")
  .load()  
  
  val oDf = ordersDf.repartition(2)
  val cDf = custDf.repartition(2)
  
   oDf.write
  .format("json")   //parqute by default file format
  .mode(SaveMode.Overwrite)
  .option("path", "C:/Users/Vijay/Desktop/shared/wek12/order")
  .save()
  
  cDf.write
  .format("json")   
  .mode(SaveMode.Overwrite)
  .option("path", "C:/Users/Vijay/Desktop/shared/wek12/customer")
  .save()
    
  //scala.io.StdIn.readLine()
  spark.stop()
}