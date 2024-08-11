

object P3 extends App {
  
  val r = 10
  val area = {
    println("calculating area of circle")
    3.14*r*r
  }
  
  println(area)
  
  lazy val area1 = {
    println("area of circle")
    3.14*r*r
  }
  
  println(area1)
  
  
  trait traintA {
    
    def name = println("this is the grand parent traint")
  }
  trait traitB extends traintA {
    
   override def name = {
     println("B is a child of A.")
     super.name
   } 
  }
  
  trait traitC extends traintA {
    
   override def name = {
     println("C is a child of A.")
     super.name
   } 
  }
  
  
  object grandChild extends traitB with traitC
  
  grandChild.name
  
  
  //Scala Interview Preparation Series 4
  
  //val a:Int = "Vijay" // compile time error
  
  var a = "vijay"  //bound at compile time - statically type
  
  //a = 56   // varible at bound to compile type.... not allow int value to string type earlier.
  
  
  //Exception
  
  try {
    val b = 5/0
  }
  catch{
    case e: Exception => println("plase give a denomnator other that 0")
  }
  finally {
    println("I will always execute")
  }
  
  
  val list1 = List(1,2,3,4)
  val list2 = List(5,6,7,8)
  
  val x = list1.flatMap{x => list2.map{
    y => x+y
  }
  }
  
  println(x)
  
  
  
  
}