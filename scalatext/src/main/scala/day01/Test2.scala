package day01

class Test2 {

}
object Test2{ //伴生对象
  def inin1():Unit={
    println("call init()")
  }
  def main(args: Array[String]): Unit = {
    val property =inin1() //没有lazy修饰
    println("after init")
    println(property)
  }
}

/**
  * scala 中用lazy的定义的变量叫惰性变量，会延迟加载
  * 惰性变量只能是不可变变量，且只有在调用惰性变量时，才会去实例化这个变量。
  */
object Test3{ //伴生对象
  def inin2():Unit={
    println("call init()")
  }
  def main(args: Array[String]): Unit = {
    lazy val property =inin2() //有lazy修饰
    println("after init")
    println(property)
  }
}
