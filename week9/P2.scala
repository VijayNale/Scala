

object P2 extends App {
  
  val b = for(i <- 1 to 10) yield {
    i*i
  }
  
  println(b)
 
  val a = for (i <- 1 to 10) yield {
    if(i%2 == 0)
    i*i
  }
  println(a)
  
  //if guard
  val c = for(i <- 1 to 10; if i%2 == 0) yield {
     i*i 
  }
  println(c)  //getting filter vales from for loop
  
  //below are alternative syntax of above "c" 
  val d = for{
    i <- 1 to 10
    if i%2 == 0
  } yield i*i 
  
  println(d)
  
  
  def checkSign(x:Int):String = {
    x match {
      case a if a > 0 => s"$a is a positive number"
      case b if b < 0 => s"$b is a negative number"
      case c => s"$c is a neither postive or negative number"
    }
  }
  
  println(checkSign(10)) //if statement inside your case statement it will called as pattern guard.
  println(checkSign(0))
  
  def f(x: Option[Int]) = x match {
    case Some(i) if i%2 == 0 => println(i)
    case None => println("none")
    case _ => println("soemthing else")
  }
  
  
  
  //for comprehension - below statment are same
  
  //general for loop in scala
  for(i <- 1 to 10) println(i)
  
  // scala will do above for loop into below code internally  
  (1 to 10).foreach(i => println(i))
  
  
  for(i <- 1 to 10; j <- 'a' to 'c') yield println(i*2 +" "+j)
  
  
  
}