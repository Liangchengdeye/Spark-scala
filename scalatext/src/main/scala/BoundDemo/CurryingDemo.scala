package BoundDemo

/**
  * 隐式转换和隐式参数
  * 作用：能丰富现有类库的功能，对类的方法进行增强
  * 隐式转换函数：以implicit关键字声明并带有单个参数的函数
  * 必须放到import的前面，否则无法起作用
  */
object Context{
  implicit val a = "java"
  implicit val b = "python"
}
/**
  * 柯里化
  */
object CurryingDemo {
  def m1(str:String)(implicit name:String ="scala")={
    println(str+name)
  }
  def main(args: Array[String]): Unit = {
    import Context.b
    println(m1("Hi~"))
  }
}
