package Schema

/**
  * 单词个数统计 spark
  */
//import scala.actors.Actor
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object SparkWordCount{
  def main(args: Array[String]): Unit = {
    println("Hello World!")
    //配置信息类                              //[*]有多少空闲cpu就开多少线程
    val sparkConf = new SparkConf().setAppName("SprakWC").setMaster("local[*]")
    //上下文对象
    val sparkContext = new SparkContext(sparkConf)
                                      //file表示本地运行，不在集成环境中
    val lines = sparkContext.textFile(args(0))
    println(lines)
    val words:RDD[String] = lines.flatMap(_.split(" "))
    val parired :RDD[(String ,Int)] = words.map((_,1))
    val reduced:RDD[(String ,Int)] =parired.reduceByKey(_+_)
    val res:RDD[(String ,Int)] =reduced.sortBy(_._2,false)//排序，取第二个元素排序

//    res.saveAsTextFile(args(1))
    println("结果：",res.collect().toBuffer)
    sparkContext.stop()
  }
}