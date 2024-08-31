import scala.io.Source
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkContext

object Week10_Practicle13 extends App{
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sc = new SparkContext("local[*]","wordcount")  
 
  val baseRdd = sc.textFile("C:/Users/Vijay/Desktop/shared/week10/bigLog.txt")
  
  val mappedRdd = baseRdd.map(x => {
    val fields = x.split(":")
    //(fields(0),fields(1))
    (fields(0),1)
  })
  
  //mappedRdd.groupByKey.collect().foreach(x=> println(x._1,x._2.size))
  mappedRdd.reduceByKey(_+_).collect.foreach(println)
  readLine()
}