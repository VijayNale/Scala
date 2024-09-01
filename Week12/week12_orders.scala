import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.sql.SaveMode



object week12_orders extends App {
  
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
  
  
  spark.sql("create database if not exists retail")
  
  ordersDf.write
  .format("csv")   
  .mode(SaveMode.Overwrite)
  .bucketBy(4, "order_customer_id")
  .sortBy("order_customer_id")
  .saveAsTable("retail.orders1")
    
  spark.catalog.listTables("retail").show()
  
  
  //ordersDf.createOrReplaceTempView("orders")
  
  //val resultDf = spark.sql("select order_status,count(*) as status_count from orders group by order_status order by status_count desc")
  
  //val resultDf = spark.sql("select order_customer_id,count(*) as order_count from orders "+
    //  "where order_status='CLOSED' group by order_customer_id order by order_count desc")
  
  
  //resultDf.show()
  
  //println("Order DF has "+ordersDf.rdd.getNumPartitions)
  
  //val orderRep = ordersDf.repartition(2)  //no of files are created
  
  //println("OrderRep DF has "+orderRep.rdd.getNumPartitions)
  
  /*
  ordersDf.write
  .format("avro")   //parqute by default file format
  .partitionBy("order_status")   //same as partion in hive.
  .mode(SaveMode.Overwrite)
  .option("maxRecordsPerFile", 2000)    //max no of line for each file
  .option("path", "C:/Users/Vijay/Desktop/shared/wek12/newfolder")
  .save()
  */
  
  scala.io.StdIn.readLine()
  spark.stop()
  
}