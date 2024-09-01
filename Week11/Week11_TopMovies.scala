import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger


object Week11_TopMovies extends App{
 
  //Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sc = new SparkContext("local[*]","wordcount")  
  
  val ratingsRdd = sc.textFile("C:/Users/Vijay/Desktop/shared/week11/ratings.dat")
  
 val mappedRdd = ratingsRdd.map(x => {
   val fields= x.split("::")
   (fields(1),fields(2))
 })
 //(1193,5)
 //(1193,3)
 //(1193,4)
 
 //output
 //(1193,(5.0,1.0))
 //(1193,(3.0,1.0))
 //(1193,(4.0,1.0))
 val newMapped = mappedRdd.mapValues(x => (x.toFloat,1.0))
 
 val reducedRdd = newMapped.reduceByKey((x,y) => (x._1 + y._1, x._2 + y._2))
 
 val filterdRdd = reducedRdd.filter(x => x._2._2 > 10)
 
 val ratingProccessed = filterdRdd.mapValues( x => x._1 / x._2).filter(x => x._2 > 4)
 
 //ratingProccessed.collect().foreach(println)
 
 val moviesRdd = sc.textFile("C:/Users/Vijay/Desktop/shared/week11/movies.dat")
 
 val moviesMapped = moviesRdd.map(x => {
   val fields = x.split("::")
   (fields(0),fields(1))
 })
 
 val joinedRdd = moviesMapped.join(ratingProccessed)
 
 val topMpvies = joinedRdd.map(x => x._2._1)
 
 topMpvies.collect().foreach(println)
 
 scala.io.StdIn.readLine()
}