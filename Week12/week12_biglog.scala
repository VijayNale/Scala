import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.functions._
import org.apache.spark.SparkContext

object week12_biglog {
  
  case class Logging(level:String,datetime:String)

  def mapper(line:String):Logging = {
    val fields = line.split(',')
    
    val logging:Logging = Logging(fields(0),fields(1))
    return logging
  }
  
  def main(args: Array[String]){
  
  Logger.getLogger("org").setLevel(Level.ERROR) 
    
  val spark = SparkSession
  .builder
  .appName("SparkSQL")
  .master("local[*]")
  .getOrCreate()
  
  import spark.implicits._
  /*
  val mylist = List("DEBUG,2015-2-6 16:24:07",
          "WARN,2016-7-26 18:54:43",
          "INFO,2012-10-18 14:35:19",
          "DEBUG,2012-4-26 14:26:50",
          "DEBUG,2012-4-26 14:26:50",
          "DEBUG,2013-9-28 20:27:13",
          "INFO,2017-8-20 13:17:27")
  
  val rdd1 = spark.sparkContext.parallelize(mylist)        
          
  val rdd2 = rdd1.map(mapper)
  
  val df1 = rdd2.toDF()
  
  //df1.show()
  
  df1.createOrReplaceTempView("logging_table")
  
  //spark.sql("select * from logging_table").show()
  /*spark.sql("""select level,count(datetime) 
    from logging_table group by level order by level""")
  .show(false)
  */
  
  val df2 = spark.sql("""select level,date_format(datetime,'MMMM') as Month 
    from logging_table""")
  
  
  df2.createOrReplaceTempView("new_logging_table")
  
  spark.sql("""select level,Month,count(1) 
    from new_logging_table 
    group by level,Month""").show()
    
    */
      
   val df3 = spark.read
  .option("header", true)
  .csv("C:/Users/Vijay/Desktop/shared/wek12/biglog.txt")

   
  df3.createOrReplaceTempView("logtable")
  
  /* val results = spark.sql("""select level,date_format(datetime,'MMMM') as month,
            cast(first(date_format(datetime,'M')) as int) as monthnum,count(1) as Total
            from logtable group by level,month
            order by monthnum,level""")
   
  val result1 = results.drop("monthnum")
  result1.show(100)
  */
  
  val monthList = List("January","February","March","April","May","June","July","August","September","October","November","December")         
    
  val results = spark.sql("""select level,date_format(datetime,'MMMM') as month
            from logtable""")
            .groupBy("level").pivot("month",monthList).count().show(100)
            
  /*
  val df2 = spark.sql("""select level,date_format(datetime,'MMMM') as Month,count(*) 
    from logging_table group by level,date_format(datetime,'MMMM') 
    order by level,date_format(datetime,'MMMM')""")
  */
  } 
}