package BoundDemo
/**
  * 隐式转换练习
 */
object ImplicitContext{
  implicit object OderingGirl extends Ordering[Girl]//ordering排序
  {
    override def compare(x:Girl,y:Girl):Int = if (x.faceValue > y.faceValue) 1 else  -1
  }
}
//比较的方法
class Girl(val name:String,var faceValue:Int){
  override def toString:String = s"name:$name,faceValue:$faceValue"
}
//用到了泛型
/**
  * [B <： A] UpperBound 上届：B类型的上界是A类型，即B类型的父类是A类型
  * [B >: A] LowerBound 下届：B类型的下界是A类型，即B类型的子类是A类型
  * [B <% A] ViewBound 表示B类型要转换成A类型，需要一个隐式转换函数
  * [B : A] ContextBound 需要一个隐式转换的值
  * [-A, +B] :
  *   [-A] 逆变，作为参数类型，如果A是T的子类，那么C[T] 是C[A]的子类
  *   [+B] 协变，作为返回类型，如果B是T的子类，那么C[B] 是C[T]的子类
  * @param v1
  * @param v2
  * @param ev$1
  * @tparam T
  */
class Goddess[T:Ordering](val v1:T,val v2:T){
  def choose()(implicit ord:Ordering[T])= if (ord.gt(v1,v2)) v1 else v2
}
object Goddess {
  def main(args: Array[String]): Unit = {
    import ImplicitContext.OderingGirl
    val g1 = new Girl("LiLi",98)
    val g2 = new Girl("shasha",90)
    val goddess = new Goddess(g1,g2)
    val res = goddess.choose()
    println(res)
  }
}
