package day05

import scala.util.Random

object MatchType {
  def main(args: Array[String]): Unit = {
    val arr=Array("abcde",100,3.14,true,MatchType)
    val element =arr(Random.nextInt(arr.length))
    println(element)
    element match {
      case str:String =>println(s"match String:$str")
      case int:Int=>println(s"match int:$int")
      case bool:Boolean =>println(s"match bool:$bool")
      case mathtest:MatchTest =>println(s"match String:$mathtest")
        //若没有匹配到，则匹配所有的基类any
      case _:Any=>println("Not Matched")
    }
  }
}
class MatchTest{

}
