package day05.CaseclassDemo

import scala.util.Random

object CaseClassDemo {
  def main(args: Array[String]): Unit = {
    val arr = Array(CheckTimeOutTask,SubmiTask("10001","task-001"),HeartBeat(3000))
    arr(Random.nextInt(arr.length)) match {
      case CheckTimeOutTask =>println("CheckTimeOutTask")
      case SubmiTask(port,task) =>println("SubmiTask")
      case HeartBeat(time) =>println("HeartBeat")
      case _ =>println("not Match")

    }
  }
}
//样例类
case class HeartBeat(time:Long)
case class SubmiTask(id:String,taskName:String)
case object CheckTimeOutTask //单例,可以不给参数