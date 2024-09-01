import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.functions._
import org.apache.spark.sql._
import org.apache.spark.sql.types.DateType

object week12_order_data extends App {

  Logger.getLogger("org").setLevel(Level.ERROR)
 
  val sparkConf = new SparkConf
  sparkConf.set("spark-app.name", "first appication")
  sparkConf.set("spark.master", "local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
 
   val invoiceDf = spark.read
  .format("csv")
  .option("header", "true")
  .option("inferSchema", "true")
  .option("path", "C:/Users/Vijay/Desktop/shared/wek12/order_data.csv")
  .load()
  
  //----- simple aggregation
  /*
  // column object expression 
  invoiceDf.select(
      count("*").as("RowCount"), 
      sum("Quantity").as("TotalQantity"),
      avg("UnitPrice").as("AvePrice"),
      countDistinct("InvoiceNo").as("CountDistinct")
      ).show
  
  
  //string expression 
  invoiceDf.selectExpr(
      "count(*) as RowCount",
      "sum(Quantity) as TotalQuantity",
      "avg(UnitPrice) as AvgPrice",
      "count(Distinct(InvoiceNo)) as CountDistinct"
      ).show()
  
  //spark sql  
   invoiceDf.createOrReplaceTempView("sales")
   spark.sql("select count(*),sum(Quantity),avg(UnitPrice),count(Distinct(InvoiceNo)) from sales").show()
      
   */
  
  //---- Grouping aggregation 
  /*
  //column expression
  val summaryDf = invoiceDf.groupBy("Country", "InvoiceNo")
  .agg(sum("Quantity").as("TotalQuantity"),
      sum(expr("Quantity * UnitPrice")).as("InvoiceValue")
      )
  summaryDf.show()
  
  //string exrpression
   val summaryDf1 = invoiceDf.groupBy("Country", "InvoiceNo")
  .agg(expr("sum(Quantity) as TotalQuantity"),
   expr("sum(Quantity * UnitPrice) as InvoiceValue"))
  
   summaryDf1.show()
   
   //spark sql
   invoiceDf.createOrReplaceTempView("sales")
   val summaryDf2 = spark.sql("""select Country, InvoiceNo,count(*),sum(Quantity * UnitPrice) as InvoiceValue 
     from sales group by Country, InvoiceNo""")
   
   summaryDf2.show()
   */

  //invoiceDf.show()
  
  spark.stop()
  
}