import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger


object Assingment1 extends App{
   

Logger.getLogger("org").setLevel(Level.ERROR)
  
val sc = new SparkContext("local[*]","wordcount")  
  
val rdd1 = sc.textFile("C:/Users/Vijay/Desktop/shared/week9/dataset.dataset1")

//rdd1.collect().foreach(println)

val rdd2 = rdd1.map(line => {
  var fields= line.split(",")
  if(fields(1).toInt > 18)
    (fields(0),fields(1),fields(2),"Y")
  else
    (fields(0),fields(1),fields(2),"N")
})

rdd2.collect().foreach(println)


}