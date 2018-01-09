package BoundDemo

/**
  *  [B <： A] UpperBound 上届：B类型的上界是A类型，即B类型的父类是A类型
  * [B >: A] LowerBound 下届：B类型的下界是A类型，即B类型的子类是A类型
  * [B <% A] ViewBound 表示B类型要转换成A类型，需要一个隐式转换函数
  * [B : A] ContextBound 需要一个隐式转换的值
  * [-A, +B] :
  *   [-A] 逆变，作为参数类型，如果A是T的子类，那么C[T] 是C[A]的子类
  *   [+B] 协变，作为返回类型，如果B是T的子类，那么C[B] 是C[T]的子类
  */
/**
  * 上界 UpperBound
  */
class UpperBoundDemo[T <: Comparable[T]] {
  def select(first:T,second:T):T={
    if (first.compareTo(second) >0) first else second
  }
}
object UpperBoundDemo{
  def main(args: Array[String]): Unit = {
     val u = new UpperBoundDemo[GoddessGirl]
     val m1 = new GoddessGirl("baozi",90)
     val m2 = new GoddessGirl("cui",95)
     val girl = u.select(m1,m2)
    println(girl.Name)
  }
}
class GoddessGirl (val Name:String,val faceValue:Int) extends Comparable[GoddessGirl]{
  override def compareTo(o: GoddessGirl): Int = {
    this.faceValue - o.faceValue
  }
}