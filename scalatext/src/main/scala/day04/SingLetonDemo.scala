package day04

import scala.collection.mutable.ArrayBuffer
//创建单例对象
/**
  * 在scala中没有静态方法和静态字段，但是可以使用object关键字加类名的语法结构实现同样的功能
  * 1、工具类存放常量和工具方法
  * 2、实现单例模式
  */
object SingLetonDemo {
  def main(args: Array[String]): Unit = {
    val factory = SessionFactory
    println(factory.getSessions)
    println(factory.getSessions.size)
    println(factory.getSessions(0))
    println(factory.removeSession)
  }
}

object SessionFactory{
  /**
    * 静态块
    */
  println("我被调用")
  var i =5
  private val session = new ArrayBuffer[Session]()
  while (i>0){
    session += new Session
    i -=1
  }
  def getSessions = session

  def removeSession: Unit = { //unit和java的void一样，表示无返回值类型
    val s=session(0)
    session.remove(0)
    println("session被移除"+s)
  }

}
class Session{}