object week8_session8 {
	
	abstract class Animal {
		val creatureType:String
		def eat
		def sleep = println("animal sleep a lot")
	}
	
	class Dog extends Animal {
		val createType: String = "canine"
		
		def eat:Unit = println("I eat fruits")
	}
	
	val d = new Dog
	d.sleep
	d.eat
	
	
	trait Carnivore {
		
	def preferredMeal
	
	}
	
	trait ColdBlooded
	
	// scala can support multiple inheritance using traits
	class Crocodile extends Animal with Carnivore with ColdBlooded
	{
	val creatureType : String = "canine"
	
	def eat = println("I eat Flesh")
	
	def prefferedMeal = println("I like food")
	}
	
	val croc = new Crocodile
	croc.eat
	croc.preferredMeal
	
	
	
}