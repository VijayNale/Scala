object week8_session9 {
  
  case class Person(name:String, age:Int)
  
  //1. class parameters are automatically promoted to fields
  val person1 = new Person("vijay",30)            //> person1  : week8_session9.Person = Person(vijay,30)
  
  println(person1.name)                           //> vijay
  
  //2. case class have sensible toString
  println(person1.toString)                       //> Person(vijay,30)
	println(person1)                          //> Person(vijay,30)
	
	//3 equals and hashcode method implemented already
	val person2 = new Person("vijay",30)      //> person2  : week8_session9.Person = Person(vijay,30)
	println(person1 == person2)               //> true
	
	//4 have companion object already, we dont have to create it. = we dont write "new" keyword
	val person3= Person.apply("vijay", 30)    //> person3  : week8_session9.Person = Person(vijay,30)
	println(person3)                          //> Person(vijay,30)
	
	val person4= Person("vijay", 30)          //> person4  : week8_session9.Person = Person(vijay,30)
	println(person4)                          //> Person(vijay,30)
	
	
	//5 case classes have a handy copy method
	val person5 = person2.copy(age=45)        //> person5  : week8_session9.Person = Person(vijay,45)
	
	println(person5.age)                      //> 45
	
	//6 case class are serialiable : transfer over network
	
	
	

}