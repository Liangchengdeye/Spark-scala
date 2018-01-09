package SparkSql.height.UserData

import org.apache.spark.sql.SQLContext
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkContext, SparkConf}


//define case class for user
case class User(userID: String, gender: String, age: Int,registerDate: String,role: String, region: String)
//define case class for consuming data
case class Order(orderID: String, orderDate: String, productID: Int, price: Int, userID: String)

object UserConsumingDataStatistics {
  def main(args: Array[String]) {
//    if (args.length < 1) {
//      println("Usage:UserConsumingDataStatistics userDataFilePath consumingDataFilePath")
//      System.exit(1)
//    }
    val FILE_PATH_CONSUMING = "file:/D:/spark_parogram/Spark-scala/scalatext/src/main/scala/SparkSql/userData/sample_consuming_data.txt"
    val FILE_PATH_USER = "file:/D:/spark_parogram/Spark-scala/scalatext/src/main/scala/SparkSql/userData/sample_user_data.txt"


    val conf = new SparkConf().setAppName("Spark Exercise:User Consuming Data Statistics").setMaster("local[*]")
    //Kryo serializer is more quickly by default java serializer
    //Kryo serializer是默认的java序列化程序更迅速
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val ctx = new SparkContext(conf)
    val sqlCtx = new SQLContext(ctx)
    import sqlCtx.implicits._
    //Convert user data RDD to a DataFrame and register it as a temp table
    //将用户数据盘一帧登记为临时表
    val userDF = ctx.textFile(FILE_PATH_USER).map(_.split(" ")).map(u => User(u(0), u(1), u(2).toInt,u(3),u(4),u(5))).toDF()
    userDF.registerTempTable("user")
    //Convert consuming data RDD to a DataFrame and register it as a temp table
    val orderDF = ctx.textFile(FILE_PATH_CONSUMING).map(_.split(" ")).map(o => Order(o(0), o(1), o(2).toInt,o(3).toInt,o(4))).toDF()
    orderDF.registerTempTable("orders")
    //cache the DF in memory with serializer should make the program run much faster
      //用串行存储器将DF缓存在内存中应该使程序运行得更快。
    userDF.persist(StorageLevel.MEMORY_ONLY_SER)
    orderDF.persist(StorageLevel.MEMORY_ONLY_SER)
    //The number of people who have orders in the year 2015
    val count = orderDF.filter(orderDF("orderDate").contains("2015")).join(userDF, orderDF("userID").equalTo(userDF("userID"))).count()
    println("The number of people who have orders in the year 2015:" + count)
    //total orders produced in the year 2014
    val countOfOrders2014 = sqlCtx.sql("SELECT * FROM orders where orderDate like '2014%'").count()
    println("total orders produced in the year 2014:" + countOfOrders2014)
    //Orders that are produced by user with ID 1 information overview
    val countOfOrdersForUser1 = sqlCtx.sql("SELECT o.orderID,o.productID,o.price,u.userID FROM orders o,user u where u.userID =1 and u.userID = o.userID").show()
    println("Orders produced by user with ID 1 showed.")
    //Calculate the max,min,avg prices for the orders that are producted by user with ID 10
    val orderStatsForUser10 = sqlCtx.sql("SELECT max(o.price) as maxPrice,min(o.price) as minPrice,avg(o.price) as avgPrice,u.userID FROM orders o, user u where u.userID = 10 and u.userID = o.userID group by u.userID")
    println("Order statistic result for user with ID 10:")
    orderStatsForUser10.collect().map(order => "Minimum Price=" + order.getAs("minPrice")
      + ";Maximum Price=" + order.getAs("maxPrice")
      + ";Average Price=" + order.getAs("avgPrice")
    ).foreach(result => println(result))
  }
}