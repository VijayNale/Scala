object week8_session2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  var name:String = "Sumit"                       //> name  : String = Sumit
  
  println(s"hello $name how are you ")            //> hello Sumit how are you 
  
  
  val pi:Double = 3.1415f                         //> pi  : Double = 3.1414999961853027
  
  println(f"value pi is $pi%.3f")                 //> value pi is 3.141
  
  println("hello how \n are you")                 //> hello how 
                                                  //|  are you
  
  println(raw"hello how \n are you")              //> hello how \n are you
  
  val abc = 1>2                                   //> abc  : Boolean = false
  
  println(abc)                                    //> false
  
  val x:String = "Sumit"                          //> x  : String = Sumit
  val y:String = "Sumit"                          //> y  : String = Sumit
  
  val z = x == y                                  //> z  : Boolean = true
  
  
  if(1 > 3){
  println("hello")
  }
  else {
  println("there")
  }                                               //> there
    
  if(1 > 3) println("hello") else println("there")//> there
  
  
  val num = 5                                     //> num  : Int = 5
  
  num match {
  
  case 1 => println("one")
  
  case 2 => println("two")
  
  case 3 => println("three")
  
  case _ => println("something else")
  }                                               //> something else
  
  
  for (x <- 1 to 10) {
  
  val squared = x*x
  
  println(squared)
  
  }                                               //> 1
                                                  //| 4
                                                  //| 9
                                                  //| 16
                                                  //| 25
                                                  //| 36
                                                  //| 49
                                                  //| 64
                                                  //| 81
                                                  //| 100
  var i = 0                                       //> i  : Int = 0
  
  while (i <= 10){
  println(i)
  i = i+1
  }                                               //> 0
                                                  //| 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
                                                  //| 9
                                                  //| 10
  i = 0
  
  do{
  println(i)
  i = i+1
  } while(i <= 10)                                //> 0
                                                  //| 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
                                                  //| 9
                                                  //| 10
  
  
  {val x = 10; x+20; 7}                           //> res0: Int = 7
  
  
  // last statement was return from block of statement
  val var1 = {val x = 10
  x+20
  7}                                              //> var1  : Int = 7
  
  println(var1)                                   //> 7
  
  
  println({val x = 10; x + 20})                   //> 30
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}