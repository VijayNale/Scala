object week8_session3 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
  def squareIt(x: Int): Int = {
  	x*x
  }                                               //> squareIt: (x: Int)Int
  
  def squareIt1(x: Int) = x*x                     //> squareIt1: (x: Int)Int
  
  println(squareIt(4))                            //> 16

	def cubeIt(x: Int) = x*x*x                //> cubeIt: (x: Int)Int

	println(cubeIt(2))                        //> 8

	def transformInt(x:Int, f:Int => Int): Int = {
		f(x)
	}                                         //> transformInt: (x: Int, f: Int => Int)Int


	transformInt(2, squareIt)                 //> res0: Int = 4
	
	transformInt(2, cubeIt)                   //> res1: Int = 8

	
	// function without name - anonymous function
	transformInt(2, x=> x*x*x)                //> res2: Int = 8

	transformInt(4, x=> x+x)                  //> res3: Int = 8


	def divideByTwo(x:Int) ={
	x/2
	}                                         //> divideByTwo: (x: Int)Int

	divideByTwo(4)                            //> res4: Int = 2

	transformInt(4, x=> x/2)                  //> res5: Int = 2

	transformInt(2, x=> {val y = x*2; y*y})   //> res6: Int = 16

	
}