package day04

/**
  * 与类名相同并且用object修饰的对象叫伴生对象
  * 类和其伴生对象之间可以互相访问私有的方法和属性
  */
class Dog {
  private  var name="二哈"
  def prinyName():Unit={
    //在dog类中访问其伴生对象的私有属性
    println(Dog.CONSTANT+name)
  }
}
//伴生对象
object Dog{

    private val CONSTANT="汪汪汪："
    def main(args: Array[String]): Unit = {
      val p = new Dog
      //访问类中的私有字段name
      p.name="哈士奇"
      p.prinyName()
    }
}
