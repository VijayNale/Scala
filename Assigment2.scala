import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.math.min

object Assigment2 {
  
  def parseLine(line:String) = {
    val fields = line.split(",")
    val stationID = fields(0)
    val entryType = fields(2)
    val temperature = fields(3)
    println(stationID,entryType,temperature)
    (stationID,entryType,temperature)
  }
  
  def main(args: Array[String]){
    
    
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    val sc = new SparkContext("local[*]","MinTemperature")
    
    val lines = sc.textFile("C:/Users/Vijay/Desktop/shared/week9/tempdata.csv")
    
    val parsedLines = lines.map(parseLine)    
    
    // Filter out all but TMIN entries    
    val minTemps = parsedLines.filter(x => x._2 == "TMIN") 
    
    val stationTemps = minTemps.map(x => (x._1, x._3.toFloat))    
    
    val minTempsByStation = stationTemps.reduceByKey( (x,y) => min(x,y))    

    val results = minTempsByStation.collect()
    
   for(result <- results) {
      val station = result._1       
      val temp = result._2    
      println("ssdfsdfd..............")
      val formattedTemp = f"$temp%.2f F"    
      println(s"$station minimum temperature: $formattedTemp")     
    }
  }
}