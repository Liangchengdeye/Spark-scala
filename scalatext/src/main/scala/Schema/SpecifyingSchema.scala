package Schema



import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

//通过structtype直接指定schema
object SpecifyingSchema {
  def main(args: Array[String]): Unit = {
    //    模板代码
    val conf =new SparkConf().setAppName("SQL-1").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    //    获取数据
    val linesRDD = sc.textFile("file:/D:/spark_parogram/Spark-scala/scalatext/src/main/scala/day07/002.txt").map(_.split(","))
//   通过structType指定每个字段的schema
    val schema = StructType(
      List(
        StructField("id",IntegerType,true),
        StructField("name",StringType,true),
        StructField("age",IntegerType,true),
        StructField("faceValue",IntegerType,true)
      )
    )
    //    将RDD映射到rowRDD并创建DdataFrame
    val rowRDD = linesRDD.map(x=>Row(x(0).toInt,x(1),x(2).toInt,x(3).toInt))
    val personDF = sqlContext.createDataFrame(rowRDD,schema)
    //    注册表
    personDF.registerTempTable("t_person")
    //    查询
    val df:DataFrame = sqlContext.sql("select * from t_person order by faceValue desc limit 3")
    //    输出
//    df.write.json("file:/D:/spark_parogram/Spark-scala/scalatext/src/main/scala/day07/003.json")
    df.show()
    sc.stop()
  }
}
