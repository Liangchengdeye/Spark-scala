package day05

/**
  * 匹配数组，元组，集合
  */
object matchList {
  def main(args: Array[String]): Unit = {
//匹配数组
    val arr = Array(3,2,5,7)
    arr match {
      case Array(4,a,b,c) =>println(s"case1:$a,$b,$c")
      case Array(_,x,y,z)=>println(s"case1:$x,$y,$z")
      case _=>println("Not Matched") //相当于default
    }
  }
//  匹配元组
  val tup =(9,5,6)
  tup match {
    case (2,a,b)=>println(s"case2:$a,$b")
    case (_,x,y)=>println(s"case2:$x,$y")
    case _=>println("Not Matched")
  }
//  匹配集合
  val list1= List(5,1,7,8)
  list1 match {
      //Nil=list空的集合
      //::从右向左开始合并生成新的list，Nil代表空的集合
    case 0:: Nil=>println("case3: 0") //可改为List(0,a,b,c) =0::a::b::c::Nil
    case a::b::c::d::Nil=>println(s"case3 $a,$b,$c,$d")
    case 0::a=>println(s"case3 $a")
    case _ =>println("Not Macched")
  }
}
