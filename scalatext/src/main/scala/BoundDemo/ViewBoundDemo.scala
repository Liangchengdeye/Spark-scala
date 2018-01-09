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
object Mypredef2{ //隐式转换函数
  implicit val selectGirl = (g:Girl2) => new Ordered[Girl2]{
    override def compare(that: Girl2): Int = {
      if (g.faceValue == that.faceValue){
        that.age -g.age
      }else{
        g.faceValue -that.faceValue
      }
    }
  }
}
//  * [B <% A] ViewBound 表示B类型要转换成A类型，需要一个隐式转换函数
class ViewBoundDemo[T <% Ordered[T]] { //Ordered排序接口
  def select(first:T,second :T):T={
    if (first >second) first else second
  }
}
object ViewBoundDemo{
  def main(args: Array[String]): Unit = {
    import Mypredef2.selectGirl
    val view = new ViewBoundDemo[Girl2]
    val g1 = new Girl2("tom",23,82)
    val g2 = new Girl2("kevin",24,92)
    val res = view.select(g1,g2)
    println(res.name)
  }
}