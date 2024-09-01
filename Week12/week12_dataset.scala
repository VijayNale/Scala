import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.functions._
import org.apache.spark.sql._


case class Person(name:String,age:Int,city:String)

object week12_dataset extends App {
  
  Logger.getLogger("org").setLevel(Level.ERROR)
 
  
  def ageCheck(age:Int) = {
    if(age > 18 ) "Y" else "N"
  }
    
  val sparkConf = new SparkConf
  sparkConf.set("spark-app.name", "first appication")
  sparkConf.set("spark.master", "local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  //processing
  val df = spark.read
  .format("csv")
  .option("inferSchema", true)
  .option("path", "C:/Users/Vijay/Desktop/shared/wek12/dataset.dataset1")
  .load()
  
  
  
  //df.printSchema()
  val df1: Dataset[Row] = df.toDF("name","age","city")
  //df1.printSchema()
 
  //val paserAgeFunction = udf(ageCheck(_:Int):String)
  
  //val df2 = df1.withColumn("adult",paserAgeFunction(column("age")))
  
  //df2.show()
  
  //spark.catalog.listFunctions().filter(x => x.name == "paserAgeFunction").show()
  
    
  spark.udf.register("paserAgeFunction", (x:Int) => {if (x>18) "Y" else "N"} )
  val df2 = df1.withColumn("Adult", expr("paserAgeFunction(age)"))
  df2.show()
  spark.catalog.listFunctions().filter(x => x.name == "paserAgeFunction").show()
  
  df1.createOrReplaceTempView("peopletable")
  
  spark.sql("select name,age,city, paserAgeFunction(age) as Adult from peopletable").show()
  
  //val paserAgeFunction = udf(ageCheck(_:Int):String)
  //val df2 = df1.withColumn("adult",paserAgeFunction(column("age")))
  //df2.show()
  
  
  
  
  //import spark.implicits._
  //val ds1 = df1.as[Person]
  //ds1.groupByKey(x => x.name)
  //ds1.filter(x => x.age > 20)
  
  //val df2 = ds1.toDF().as[Person].toDF()
  
  //scala.io.StdIn.readLine()
  spark.stop()
  
}