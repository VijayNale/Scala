object P1 extends App {
  
  println("Hello World")
  
  //default argument
  def add(num:Int, incrementBy:Int = 1) = {
    num + incrementBy
  }
  
  println(add(5))
  
  //named argument
   def add1(num:Int = 8 , incrementBy:Int = 1) = {
    num + incrementBy
  }
    
  println(add1(incrementBy = 7))
  
    // variable length argument
    def prntFn(name:String*) = {
      for(i <- name){
        println(i)
      }
    }
    prntFn("hello","how","are","you")
    
    //NUll
    def tryIt(thing: Null) : Unit = {println("that worked")}
    
    tryIt(null)  //only thing of we have pass here null.
    
    //Nil - empty list
    val c = Nil
    println(c)
    
    //Nothing - trait in scala
    def fun = { throw new Exception}
    
    //fun   //getting error while calling "fun" function... because it throw exception that time it has return type was nothing. 
    
    
    //
    def getAStringMayBe(num: Int):Option[String] = {
      if (num >= 0) Some("A positive number")
      else None
    }
    
    // option preffered for avoid null pointer exceptions, to avoid null in our code 
    def printResult(num:Int) = {
      getAStringMayBe(num) match {
        case Some(str) => println(str)
        case None => println("No String")
      }
    }
    
    printResult(10)
    
    
    def funcNew = {
      println("hellow world")
    }
    
    funcNew //return type of this function is "Unit" ... println doesnt have side effect.
    
    
    case class Address (city:String, state: String, zip: String)
    
    /*
    class User(email:String, password: String) {
       var firstname: String = _  //null define
       var lastname: String = _
       var address: String = _
      }
    
     val usr = new User("vijay123@gmail.com","abcd@123")
     
     println(usr.firstname.length) //geting null pointer exception
			*/
    
    class User(email:String, password: String) {
      // fields present or not..  it none thats so better way to deals with null is to use option
       var firstname: Option[String] = None 
       var lastname: Option[String] = None
       var address: Option[Address] = None
      }
    
     val usr = new User("vijay123@gmail.com","abcd@123")
     
     println(usr.firstname.getOrElse("<not assigned>"))
     
     usr.firstname = Some("Vijay")
     usr.lastname = Some("Nale")
     usr.address = Some(Address("Pune","MH","416012"))
     
     println(usr.firstname.getOrElse("<not assigned>"))
     
     
     
}
    
    
    
    
    
    