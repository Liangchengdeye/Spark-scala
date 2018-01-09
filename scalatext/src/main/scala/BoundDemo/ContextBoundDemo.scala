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
object Mypredef3{ //隐式转换函数
  implicit object  OrderingGirl extends Ordering[Girl2]{
    override def compare(x: Girl2, y: Girl2): Int = {
      if (x.faceValue== y.faceValue){
        y.age - x.age
      }else{
        x.faceValue - y.faceValue
      }
    }
  }
}
class ContextBoundDemo[T:Ordering] {
  def select(first:T,second :T):T={
    val ord :Ordering[T] = implicitly[Ordering[T]]
    if (ord.gt(first ,second)) first else second
  }
}
object ContextBoundDemo{
  def main(args: Array[String]): Unit = {
    import Mypredef3.OrderingGirl
    val contextBound = new ContextBoundDemo[Girl2]
    val g1 =new Girl2("zhangsan",34,87)
    val g2 =new Girl2("lisi",32,87)
    val res = contextBound.select(g1,g2)
    println(res.name)
  }
}