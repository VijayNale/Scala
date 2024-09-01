import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object Week12_practicle2 extends App {
  
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  val myregex = """^(\S+) (\S+)\t(\S+)\,(\S+)""".r
  
  case class Orders(order_id:Int,date:String,customer_id:Int, order_status:String)
  
  def parser(line:String) = {
    line match {
      case myregex(order_id,date,customer_id,order_status) =>
        Orders(order_id.toInt,date,customer_id.toInt,order_status)
    }
  }
  
  val sparkConf = new SparkConf
  sparkConf.set("spark-app.name", "App")
  sparkConf.set("spark.master", "local[2]")
  
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()
  
  val lines = spark.sparkContext.textFile("C:/Users/Vijay/Desktop/shared/wek12/orders_new.csv")
  
  import spark.implicits._
  
  val ordersDS = lines.map(parser).toDS().cache()
  
  ordersDS.printSchema()
  
  ordersDS.select("order_id","date").show()
  
  ordersDS.groupBy("order_status").count().show()
  
  //ordersDS.filter(x => x.customer_id > 20) // dataset give us compile time safty
  
  //scala.io.StdIn.readLine()
  spark.stop()
}