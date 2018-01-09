package CustomSort

//自定义排序
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
//第一种排序
//object MySort{
//  implicit val girlOrdering = new Ordering[Girl]{
//    override def compare(x: Girl, y: Girl): Int = {
//      if (x.faceValue != y.faceValue){
//        x.faceValue -y.faceValue
//      }else{
//          y.age - x.age
//        }
//    }
//
//  }
//}
object CustomSort {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("CustomSort").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val girlInfo = sc.parallelize(Array(("tingting",80,25),("ningning",90,26),("mimi",90,27)))
//    普通排序
//    val res =girlInfo.sortBy(_._2,false)
//    println(res.collect().toBuffer)
    //第一种排序方式
//    import MySort.girlOrdering
//    val res: RDD[(String, Int, Int)] = girlInfo.sortBy(x=>Girl(x._2,x._3),false)
//    println(res.collect.toBuffer)
    //第二种排序方式
    val res: RDD[(String, Int, Int)] = girlInfo.sortBy(x=>Girl(x._2,x._3),false)
    println(res.collect.toBuffer)

    sc.stop()
  }
}
//第一种排序方式
//case class Girl(faceVale:Int,age:Int){}

//第二种排序方式 (重写compare方法)
case class Girl(faceValue:Int,age:Int) extends Ordered[Girl]{
  override def compare(that: Girl): Int = {
    if (this.faceValue != that.faceValue){
      this.faceValue-that.faceValue
    }else{
      that.age-this.age
    }
  }
}