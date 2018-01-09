package day04

//默认就是public的类
class Person {
  //用val修饰的变量相当于只有get方法
  val id:Int = 95001; //用val修饰的只可读，不可修改
  //用var修饰的变量相当于既有get又有set方法
  var name:String = _
  //用private修饰的属性，该属性对象私有变量，只有本类和伴生对象能访问到
  private var age:Int= _
  //只能在本类中访问，伴生对象也访问不到，属于对象私有变量
  private [this] val gender = "男"
}
//该类是静态的，也叫伴生对象
object Person{
  def main(args: Array[String]): Unit = {
    val p =new  Person()
    println(p.id)
    p.name="tom"
    p.age=26
    println(p.name)
    println(p.age)
  }
}
