object week8_session5 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def sumFunc(x:Int, y:Int) = {x+y}               //> sumFunc: (x: Int, y: Int)Int
  
  val increment = sumFunc(1, _:Int)               //> increment  : Int => Int = week8_session5$$$Lambda$8/440434003@79698539
  
  increment(12)                                   //> res0: Int = 13

	def genericSum(x:Int, y:Int,f:Int => Int) = {
		f(x) + f(y)
	}                                         //> genericSum: (x: Int, y: Int, f: Int => Int)Int

	genericSum(2, 3, x=>x)                    //> res1: Int = 5
	genericSum(2, 3, x=>x*x)                  //> res2: Int = 13

	//partially applied funtion
	val sumOfSquar = genericSum(_:Int, _:Int, x=>x*x)
                                                  //> sumOfSquar  : (Int, Int) => Int = week8_session5$$$Lambda$11/1595953398@3b81
                                                  //| a1bc
	
	sumOfSquar(3,4)                           //> res3: Int = 25
	
	
	//function curring
	// fixing one parameter another parameter are place holder.. it will pass
	def genericSum1(f:Int=> Int)(x:Int,y:Int) = {
		f(x) + f(y)
	}                                         //> genericSum1: (f: Int => Int)(x: Int, y: Int)Int

	genericSum1(x=>x*x)(2,3)                  //> res4: Int = 13

	val sumOFsquares = genericSum1(x=> x*x)_  //> sumOFsquares  : (Int, Int) => Int = week8_session5$$$Lambda$15/391447681@735
                                                  //| f7ae5
	
	sumOFsquares(2,3)                         //> res5: Int = 13
	
	




}