object week8_session6 {
  
  //cpnstructor
	class Person(val name:String, age:Int)
	val p = new Person("vijay",30)            //> p  : week8_session6.Person = week8_session6$Person@1cf4f579
	// in the above class both name and age are class parameters.
	// these are not the class fields
	println(p.name)                           //> vijay
	
	
	class Person1(name:String, age:Int)
	{
	val x=20
	
	def ageDoubler() = age*x
	
	def salaryDoubler(salary:Int) = salary*2
	}
	
	val q = new Person1("vijay",30)           //> q  : week8_session6.Person1 = week8_session6$Person1$1@3cda1055
	
	//fields inside class
	println(q.x)                              //> 20
	
	q.ageDoubler()                            //> res0: Int = 600
	q.salaryDoubler(10000)                    //> res1: Int = 20000
	
	// it will have same coly for all persons
	object Person2 {
	/// class level fucntionality | same like "static" in java
	
		val n_eyes = 2
		def canfly: Boolean = false
	}
	
	println(Person2.n_eyes)                   //> 2
	
	
	val marry = Person2                       //> marry  : week8_session6.Person2.type = week8_session6$Person2$2$@7a5d012c
	val john = Person2                        //> john  : week8_session6.Person2.type = week8_session6$Person2$2$@7a5d012c
	
	println(marry == john)                    //> true
	
	
	/// it will create saparate copy for every person
	class Person2(val name:String, val age:Int){
	// instance level functionality
	
	def salaryageDoubler(salary:Int) = salary * 2
	}
	
	val person = new Person2("vijay",30)      //> person  : week8_session6.Person2 = week8_session6$Person2$3@3fb6a447
	
	//companions design pattern : object and class having same name
	println(person.age)                       //> 30
	println(Person2.n_eyes)                   //> 2
	
	
	val vijay = new Person2("vijay",30)       //> vijay  : week8_session6.Person2 = week8_session6$Person2$3@79b4d0f
	val sandy = new Person2("sandy",30)       //> sandy  : week8_session6.Person2 = week8_session6$Person2$3@6b2fad11
	
	
	println(vijay == sandy)                   //> false
	
	
	
	
}