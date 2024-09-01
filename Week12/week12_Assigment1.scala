//Find the count of employees against each department
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.sql.functions._

object week12_Assigment1 extends App {
  
  //Setting the Log Level
  Logger.getLogger("org").setLevel(Level.ERROR)
  
  //Setting the spark conf
  val sparkConf = new SparkConf()
  sparkConf.set("spark.app.name","Assignment1_Week12")
  sparkConf.set("spark.master","local[2]")
  
  //Creating Spark Session
  val spark = SparkSession.builder()
  .config(sparkConf)
  .getOrCreate()

   //Load the department data into a Dataframe using dataframe reader API
    val deptDf = spark.read
    .format("json")
    .option("path","C:/Users/Vijay/Desktop/shared/wek12/dept.json")
    .load()
    
    //deptDf.show()
    //deptDf.printSchema()
    
    //Load the employee data into a Dataframe using dataframe reader API
    val employeeDf = spark.read
    .format("json")
    .option("path","C:/Users/Vijay/Desktop/shared/wek12/employee.json")
    .load()
    
    //employeeDf.show()
    //employeeDf.printSchema()
    
    
    //Joining of two dataframes using left outer join, with department dataframe on left side
    val joinCondition = deptDf.col("deptid") === employeeDf.col("deptid")//joincondition
    val joinType = "left" //joinType
    
    val joinedDf = deptDf.join(employeeDf, joinCondition, joinType) //Joining of two dataframes
    
    //drop the ambiguous column deptid of employee dataframe,from the joined Dataframe
    val joinedDfNew = joinedDf.drop(employeeDf.col("deptid"))
    
    //Use first function so as to get other columns also along with aggregated columns
    joinedDfNew.groupBy("deptid").agg(count("empname").as("empcount"),first("deptName").as ("deptName")).dropDuplicates("deptName").show()
    
    spark.stop()
}



