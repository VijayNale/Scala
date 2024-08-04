object week8_session7 {
  
	class Animal{
	//private member of this class cant access to extended class
	def eat = println("Animal eat a lot")
	
	// this will access body of extended class
	protected def eat1 = println("Animal eat a lot")
	}
	
	class Cat extends Animal{
	def prefferedMeal = println("milk")
	eat1
	}
	
	val cat = new Cat                         //> Animal eat a lot
                                                  //| cat  : week8_session7.Cat = week8_session7$Cat@380fb434
	
	cat.eat                                   //> Animal eat a lot
	cat.prefferedMeal                         //> milk
	
	
}