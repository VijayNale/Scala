import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window


object week12_window extends App {
  
  Logger.getLogger("org").setLevel(Level.ERROR)
 
  val sparkConf = new SparkConf
  sparkConf.set("spark-app.name", "first appication")
  sparkConf.set("spark.master", "local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
 
   val readdf = spark.read
  .format("csv")
  .option("inferSchema", "true")
  .option("path", "C:/Users/Vijay/Desktop/shared/wek12/windowdata.csv")
  .load()
 
  readdf.printSchema()
  
  val invoiceDf = readdf.toDF("country","weeknum","numinvoices","totalqunatity","invoicevalue")
  
  val myWindow = Window.partitionBy("country")
  .orderBy("weeknum")
  //.rowsBetween(Window.unboundedPreceding, Window.currentRow)
  .rowsBetween(-2, Window.currentRow)
  
  val mydf = invoiceDf.withColumn("RunnningTotal", 
            sum("invoicevalue").over(myWindow))
  
  mydf.show()          
            
  spark.stop()
}