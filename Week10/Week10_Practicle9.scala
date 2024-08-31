import scala.io.Source
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkContext


object Week10_Practicle9 extends App {
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sc = new SparkContext("local[*]","wordcount")  
  
  val myrdd = sc.textFile("C:/Users/Vijay/Desktop/shared/week10/samplefile.txt")
  
  val myaccum = sc.longAccumulator("blank line accumulator")

  myrdd.foreach(x => if(x=="") myaccum.add(1))
  
  println(myaccum)

}