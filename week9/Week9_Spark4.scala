import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger

// find out average number of connection for each age

object Week9_Spark4 extends App {
  
   def parse(line:String) = {
      val fields = line.split("::")
      val age = fields(2).toInt
      val numFriends = fields(3).toInt
      (age,numFriends)
  }
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sc = new SparkContext("local[*]","wordcount")
  
  val input = sc.textFile("D:/dataset/friendsdata.csv")
  
  val mappedInput = input.map(parse)
  
  //val mappedFinal = mappedInput.map(x => (x._1,(x._2,1)))
  
  val mappedFinal = mappedInput.mapValues(x => (x,1))
    
  val totalByAge = mappedFinal.reduceByKey((x,y) => (x._1 + y._1, x._2 + y._2))
  
  //val avaragesByAge = totalByAge.map(x => (x._1,x._2._1/x._2._2)).sortBy(x => x._2,false)
  
  val avaragesByAge = totalByAge.mapValues(x => x._1/x._2).sortBy(x => x._2,false)
  
  avaragesByAge.collect.foreach(println)
  
  
}