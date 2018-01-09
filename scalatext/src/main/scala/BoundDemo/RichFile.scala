package BoundDemo

import scala.io.Source

/**
  * 隐式转换--读取文件
  */
object MyPredef{
  implicit def fileToRichFile(file:String)=new RichFile(file)
}
//普通方法
class RichFile(val file:String) {
  def read():String={
      Source.fromFile(file).mkString
  }
}
object RichFile{
  def main(args: Array[String]): Unit = {
    //1、这个过程是显式的实现了read方法
//    val file = "D:\\P_C\\spark_demo\\word.txt"
//    val str:String = new RichFile(file).read()
//    println(str)

    //2、隐式实现read方法（隐式转换）
    import MyPredef.fileToRichFile
    val file = "D:\\P_C\\spark_demo\\word.txt"
    val content = file.read()
    println(content)
  }
}
