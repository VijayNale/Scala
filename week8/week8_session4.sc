object week8_session4 {
  
  val a = Array(1,2,3,4,5)                        //> a  : Array[Int] = Array(1, 2, 3, 4, 5)
  
  //separated by comma .. defualt it will show object of array
  println(a.mkString(","))                        //> 1,2,3,4,5
  
  for(i <- a) println(i)                          //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
  a(2) = 7
  
  println(a(2))                                   //> 7
  println(a.mkString(","))                        //> 1,2,7,4,5
  
  
  val b = List(1,2,3,4,5)                         //> b  : List[Int] = List(1, 2, 3, 4, 5)
  
  println(b)                                      //> List(1, 2, 3, 4, 5)
  
  println(b.head)                                 //> 1
  
  println(b.tail)                                 //> List(2, 3, 4, 5)
  
  
  for (i <- b) {
  println(i)                                      //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
 	
 	
	}
  
 	println(b.reverse, b.sum)                 //> (List(5, 4, 3, 2, 1),15)
  
  //add 10 in each list element
  10 :: b                                         //> res0: List[Int] = List(10, 1, 2, 3, 4, 5)
  
  println(b(0))                                   //> 1
  
  val x = ("vijay",100000,29, true)               //> x  : (String, Int, Int, Boolean) = (vijay,100000,29,true)
  
  println(x)                                      //> (vijay,100000,29,true)
  
  
  // tuple start with 1.. but array start with 0
  println(x._1)                                   //> vijay
  println(x._2)                                   //> 100000
  
  
  // suppose we have tuple of 2 element... it is key value.
  val y = (107, "vijay")                          //> y  : (Int, String) = (107,vijay)
  
  val z = 107 -> "vijay"                          //> z  : (Int, String) = (107,vijay)
  
  //above both statement are same.. 2 stype for define tuple for key value pair
  
  
  val rng = 1 to 10                               //> rng  : scala.collection.immutable.Range.Inclusive = Range 1 to 10
  
  //this will exclude 10
  val rng1 = 1 until 10                           //> rng1  : scala.collection.immutable.Range = Range 1 until 10
  
  for (i <- rng) println(i)                       //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
                                                  //| 9
                                                  //| 10
  for (i <- rng1) println(i)                      //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
                                                  //| 9
  val zx = Set(1,1,1,1,2,4,5,5,5)                 //> zx  : scala.collection.immutable.Set[Int] = Set(1, 2, 4, 5)
  
  println(zx)                                     //> Set(1, 2, 4, 5)
  
  println(zx.min)                                 //> 1
  
  println(zx.max)                                 //> 5
  
  println(zx.sum)                                 //> 12
  
  
  val xy = Map(1 -> "Vijay",2-> "sushant", 1 -> "Sandy")
                                                  //> xy  : scala.collection.immutable.Map[Int,String] = Map(1 -> Sandy, 2 -> sus
                                                  //| hant)
  
  xy.get(1)                                       //> res1: Option[String] = Some(Sandy)
  
  println(xy)                                     //> Map(1 -> Sandy, 2 -> sushant)
  
  
  
  val s = "hello how are you"                     //> s  : String = hello how are you
  
  println(s.reverse)                              //> uoy era woh olleh
  
  
 //val a = "hello"; val a = "bye"
  
  
  def doubler(i:Int):Int = {
  	i*2
  }                                               //> doubler: (i: Int)Int
  
  doubler(2)                                      //> res2: Int = 4
  
  val c = doubler(_)                              //> c  : Int => Int = week8_session4$$$Lambda$27/542060780@e25b2fe
  
  c(2)                                            //> res3: Int = 4
  
  
  def tripler(i:Int): Int = {i*3}                 //> tripler: (i: Int)Int
  
  def func(i:Int , f:Int => Int) = {
  	f(i)
  }                                               //> func: (i: Int, f: Int => Int)Int
  
  // pass function as parameter
  func(5,tripler)                                 //> res4: Int = 15
  
  // function can also return function with return value (lambda)
  def func1 = {
  x:Int => x*x
  }                                               //> func1: => Int => Int
  
  func1(4)                                        //> res5: Int = 16
  
  
  var d = 1 to 10                                 //> d  : scala.collection.immutable.Range.Inclusive = Range 1 to 10
  
  def doubler1(i:Int):Int = {i*2}                 //> doubler1: (i: Int)Int
  
  d.map(doubler1)                                 //> res6: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 4, 6, 8, 10, 1
                                                  //| 2, 14, 16, 18, 20)
  
  //lambda functon: anonymous function
  d.map(x => x*2)                                 //> res7: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 4, 6, 8, 10, 1
                                                  //| 2, 14, 16, 18, 20)
  //loop
  def fact(input:Int):Int = {
  	var result :Int = 1
  	
  	for(i <- 1 to input)
  	{
  		result = result * i
  	}
  	result
  }                                               //> fact: (input: Int)Int
  
  fact(5)                                         //> res8: Int = 120
  
  
  //recursion
  
  def fact_recr(input:Int):Int={
  	if(input == 1) 1
  	else input * fact_recr(input-1)
  }                                               //> fact_recr: (input: Int)Int
  
  fact_recr(5)                                    //> res9: Int = 120
  
  
  
 def fact1(input:Int, result:Int):Int = {
 	if(input == 1) result
 	else fact1(input-1, result*input)
 }                                                //> fact1: (input: Int, result: Int)Int
  
 fact1(4,1)                                       //> res10: Int = 24
  
  
 val e = 5                                        //> e  : Int = 5
 
 val f = if(e==x) 2 else 7                        //> f  : Int = 7
  
 
 def areaCircle = {val pi = 3.14; x:Int => pi *x*x}
                                                  //> areaCircle: => Int => Double
  
 val pi = 3.5                                     //> pi  : Double = 3.5
 
 //clouser
 areaCircle(10)                                   //> res11: Double = 314.0
  
  
  
  
}