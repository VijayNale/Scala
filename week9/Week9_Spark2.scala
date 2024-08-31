import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger


object Week9_Spark2 extends App {
 
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sc = new SparkContext("local[*]","wordcount")
  
  val input = sc.textFile("D:/dataset/customerorders.csv")
 
  val mappedInput = input.map(x => (x.split(",")(0),x.split(",")(2).toFloat))
  //second element is amount convert into float... if it will string by default
  
  val totalByCustomer = mappedInput.reduceByKey((x,y) => x+y) 
  
  val sortedTotal = totalByCustomer.sortBy(x => x._2, ascending = false)
  
  val result = sortedTotal.collect()
  
  result.foreach(println)
}