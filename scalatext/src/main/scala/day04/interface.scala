package day04

object interface {
  def main(args: Array[String]): Unit = {
    val human = new Human
    println( human.name)
    println( human.climb)
    println( human.fly)
  }
}
/**
  * 特质/接口
  */
trait Flyable{
  //申明一个没有值的字段
  val distance : Int
  //申明一个有值的字段
  val distance2 : Int=1000
  //申明一个没有实现对方法
  def fight:String
  //声明一个实现的方法
  def fly:Unit={
    println("i can fly")
  }
}
abstract class Animal{
  //声明一个没有赋值的字段
  val name:String
  //声明没有实现的方法
  def run():String
  //声明实现的方法
  def climb :String={
    "i can climb"
  }
}
class Human extends Animal with Flyable{
  override val name: String = "张三"
  //重写了抽象类中没有实现的方法
  override def run(): String = "i can run"

  override val distance: Int = 2000
  //重写了特质中没有实现的方法
  override def fight: String = "with knife"

  override def climb: String = "override climb"
  //实现了特种中有实现的方法
  override def fly: Unit = println("override fly")
}