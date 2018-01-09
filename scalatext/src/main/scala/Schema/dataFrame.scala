package Schema
//通过反射推断schema
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

object dataFrame {
  def main(args: Array[String]): Unit = {
//    模板代码
    val conf =new SparkConf().setAppName("SQL-1").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
//    获取数据
    val linesRDD = sc.textFile("file:/D:/spark_parogram/Spark-scala/scalatext/src/main/scala/day07/002.txt").map(_.split(","))
//    将RDD和case class进行关联
    val personRDD = linesRDD.map(x=>Person(x(0).toInt,x(1),x(2).toInt,x(3).toInt))
//    创建DataFrame
    import sqlContext.implicits._
    val personDF = personRDD.toDF
//    注册表
    personDF.registerTempTable("t_person")
//    查询
    val df:DataFrame = sqlContext.sql("select * from t_person")
//    输出
    df.write.json("file:/D:/spark_parogram/Spark-scala/scalatext/src/main/scala/day07/003.json")
    df.show()
    sc.stop()
  }
}
case class Person(id:Int,name:String,age:Int,faceValue:Int)