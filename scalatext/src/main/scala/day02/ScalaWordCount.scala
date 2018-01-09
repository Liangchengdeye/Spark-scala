package day02
//scala 实现wordCount

object ScalaWordCount {
  def main(args: Array[String]): Unit = {
    val lines = List("hello java hello scala", "hello scala", "hello python")
    //单词计数
    //切分并压平
    val n1 = lines.flatMap(_.split(" "))
    //把每个单词生成一个一个的tuple或pair
    val n2 = n1.map((_,1))
    //分组 以key分组
    val n3 = n2.groupBy(_._1) //第一个下划线代表拿到每个元祖，第二个_1代表元组中第一个元素
//    统计list个数 value的长度
    val n4 = n3.mapValues(_.size)
//    排序,以value排序
    val n5 = n4.toList.sortBy(_._2).reverse
    println(n5)
  }
}
