package day05.CaseclassDemo

object PartialFunctionDemo {
  //偏函数 [参数类型，返回值类型] 经常用来做模式匹配
  def m1:PartialFunction[String, Int]={
    case "one" =>{
      println("case 1")
      1 //最后一句代码作为返回
    }
    case "two" =>{
      println("case 2")
      2
    }
  }
//  等同于  偏函数是下面这种用法的进化版本
  def m2(num:String):Int =num match {
    case "one"=>1
    case "two"=>2
    case  _=> 0
  }
  def main(args: Array[String]): Unit = {
    println(m1("one"))
    println(m2("two"))
  }
}
