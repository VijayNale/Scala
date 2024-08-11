

object P4 extends App{
  
  trait Computer{
    
    def ram:String
    def hdd:String
    def cpu:String
  }
  
  case class PC(ram:String, hdd:String, cpu:String) extends Computer
  
  case class LAPTOP(ram:String, hdd:String, cpu:String) extends Computer
  
  object ComputerFactory {
    
    def apply(compType: String, ram: String, hdd:String, cpu: String) = compType match {
      
      case "PC" => PC (ram, hdd, cpu)
      
      case "LAPTOP" => LAPTOP (ram, hdd, cpu)
    }
    
    ComputerFactory("PC", "16gb","1tb","2.3gz")
    ComputerFactory("LAPTOP", "16gb","1tb","2.3gz")
    
  }
  
}