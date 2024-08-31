import scala.io.Source
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkContext


object Week10_Practicle10 extends App{
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sc = new SparkContext("local[*]","wordcount")  
  
  val mylist = List("WARN: Tuesday 4 September 0405",
                    "ERROR: Tuesday 4 September 0408",
                    "ERROR: Tuesday 4 September 0408",
                    "ERROR: Tuesday 4 September 0408",
                    "ERROR: Tuesday 4 September 0408",
                    "ERROR: Tuesday 4 September 0408")
    
   val originalLogsRdd = sc.parallelize(mylist)
   
   val newPairRdd = originalLogsRdd.map(x => {
     val columns = x.split(":")
     val loglevel = columns(0)
     (loglevel,1)
   })
   
   val resultantRdd = newPairRdd.reduceByKey((x,y) => x+y)
   
   resultantRdd.collect().foreach(println)
   
   /* chaining of function (above activity into 2 lines :)
   sc.parallelize(mylist).map(x => (x.split(":")(0),1)).
   reduceByKey(_+_).collect().foreach(println)
   */
}
