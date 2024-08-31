import scala.io.Source
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkContext


object Week10_Spark1 extends App {
  
   def loadBoringwords(): Set[String] = {
    
    var boringWords:Set[String] = Set()
    
    val lines = Source.fromFile("C:/Users/Vijay/Desktop/shared/week10/boringwords.txt").getLines()
    
    for(line <- lines) {
      boringWords += line
    }
    boringWords
  }
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sc = new SparkContext("local[*]","wordcount")  
  
  var nameSet = sc.broadcast(loadBoringwords)
  
  val initail_rdd = sc.textFile("C:/Users/Vijay/Desktop/shared/week10/bigdatacampaigndata.csv")
  //println(initail_rdd.collect)
  
  val mappedInput = initail_rdd.map(x => (x.split(",")(10).toFloat,x.split(",")(0)))
  
  val words = mappedInput.flatMapValues(x => x.split(" "))
  
  val finalmapped = words.map(x => (x._2.toLowerCase(),x._1))

  val filteredRdd = finalmapped.filter(x => !nameSet.value(x._1))

  val total = filteredRdd.reduceByKey(_+_)
  
  val sorted = total.sortBy(x => x._2, false)
  
  sorted.take(20).foreach(println)

}