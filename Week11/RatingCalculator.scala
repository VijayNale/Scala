import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger


object RatingCalculator extends App{
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sc = new SparkContext("local[*]","wordcount")  
  
  val input = sc.textFile("C:/Users/Vijay/Desktop/shared/week9/moviedata.data")
  
  val mappedInput = input.map(x => x.split("\t")(2))
  
  val results = mappedInput.countByValue
  results.foreach(println)
  
  
  //val ratings = mappedInput.map(x => (x,1))
  
  //val reduceRatings = ratings.reduceByKey((x,y) => x+y)
  
  //val results = reduceRatings.collect
  
  //results.foreach(println)
  
}