import org.apache.spark.SparkContext
import org.apache.log4j.Level
import org.apache.log4j.Logger


object Week10_TotalSpend extends App {
  
  //Logger.getLogger("org").setLevel(Level.ERROR)
  
  val sc = new SparkContext("local[*]","wordcount")  
  
  val input = sc.textFile("C:/Users/Vijay/Desktop/shared/week9/customerorders.csv")
  
  val mappedInput = input.map(x => (x.split(",")(0),x.split(",")(2).toFloat))
  
  val totalByCustomer = mappedInput.reduceByKey((x,y) => x+y)

  val premiumCustomer = totalByCustomer.filter(x => x._2 > 5000)
  
  val doubleAmount = premiumCustomer.map(x => (x._1,x._2 * 2)).cache()
  
  doubleAmount.collect().foreach(println)
  
  println(doubleAmount.count)
  
  readLine()
  //val sortedTotal = totalByCustomer.sortBy(x => x._2,false)

  //var results = sortedTotal.collect
  //println(sortedTotal.collect)
  
  //sortedTotal.saveAsTextFile("file:///C:/Users/Vijay/Desktop/shared/week9/SparkOutput")
  
  //results.foreach(println)
  //results(0)_1
  /*
  println("Highest Spends Top 10 Customers")
  for(i <- 0 until 10){
    //println(results(i)._1)
    val cust = results(i)._1
    val amt = results(i)._2
    println(s"$cust : $amt")
  }
  */
}