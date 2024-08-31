// wordcount problem
import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger

object Week_Spark1 extends App {

  //only show the error
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  //ctrl+shift+o ... fora auto import library
  val sc = new SparkContext("local[*]","wordcount")
  
  val input = sc.textFile("D:/dataset/search_data.txt")
  
  val words = input.flatMap(x => x.split(" "))
  // val words = input.flatMap(_.split(" "))  we can write like this also - less typing
  
  val wordsLower = words.map(x => x.toLowerCase())
  
  val wordMap = wordsLower.map(x => (x,1))
  
  val finalCount = wordMap.reduceByKey((a,b) => (a+b))
  
  //val reversedTuple = finalCount.map(x => (x._2, x._1))
  //val sortedResult = reversedTuple.sortByKey(false).map(x => (x._2, x._1))
  
  val sortedResult = finalCount.sortBy(x => x._2)
  
  val results = sortedResult.collect
  
  for (result <- results) {
    val word = result._1
    val count = result._2
    println(s"$word : $count")
  }
  
  
  // Hold screen for Spark UI
  // scala.io.StdIn.readLine()
  
  
  
}