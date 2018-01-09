package SqlSpark

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.spark.{SparkConf, SparkContext}

object Sqlinster {

  case class Blog(name: String, count: Int)

  def myFun(iterator: Iterator[(String, Int)]): Unit = {
    var conn: Connection = null
    var ps: PreparedStatement = null
    val sql = "insert into blog(name, count) values (?, ?)"
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root", "root")
      iterator.foreach(data => {
        ps = conn.prepareStatement(sql)
        ps.setString(1, data._1)
        ps.setInt(2, data._2)
        ps.executeUpdate()
        println("inster successful")
      }
      )
    } catch {
      case e: Exception => println("Mysql Exception")
    } finally {
      if (ps != null) {
        ps.close()
      }
      if (conn != null) {
        conn.close()
      }
    }
  }

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("RDDToMysql").setMaster("local")
    val sc = new SparkContext(conf)
    val data = sc.parallelize(List(("www", 10), ("iteblog", 20), ("com", 30)))
    data.foreachPartition(myFun)
  }
}