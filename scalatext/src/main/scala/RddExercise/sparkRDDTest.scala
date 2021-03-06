package RddExercise

import org.apache.spark.{SparkConf, SparkContext}

//RDD练习
object sparkRDDTest {
  def main(args: Array[String]): Unit = {
    println("Hello World!")
    //配置信息类                              //[*]有多少空闲cpu就开多少线程
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
    //上下文对象
    val sc = new SparkContext(sparkConf)

    //通过并行化生成rdd
    val rdd1 = sc.parallelize(List(5,6,4,7,3,8,2,9,1,10))
    //对rdd1里的每个元素乘以2然后排序
    val res1 = rdd1.map(_ *2).sortBy(x =>x,true) //正序排序，倒序为false
    println(res1.collect().toBuffer)
    //过滤出大于等于10的元素
    val res2 = res1.filter(_ >=10)
    //将元素以数组方式打印出来
    println(res2.collect().toBuffer)

    val rdd2 = sc.parallelize(Array("a b c","d e f","h i j"))
    //将rdd2里面的每个元素先切分再压平
    val res3 = rdd2.flatMap(_.split(" "))
    println(res3.collect().toBuffer)

    val rdd3 = sc.parallelize(List(List("a b c","a b b"),List("e f g","a f g"),List("h i j","a a b")))
    //将rdd3里面的每一个元素先切分再压平
    val res4 = rdd3.flatMap(_.flatMap(_.split(" ")))
    println(res4.collect().toBuffer)

    val rdd4 = sc.parallelize(List(5,6,4,3))
    val rdd5 = sc.parallelize(List(1,2,3,4))
    //求并集
    val res5 = rdd4 union(rdd5)
    println(res5.collect().toBuffer)
    //求交集
    val res6 = rdd4.intersection(rdd5)
    println(res6.collect().toBuffer)
    //去重
    val res7 = res5.distinct()
    println(res7.collect().toBuffer)

    val rdd6 = sc.parallelize(List(("tom",1),("jerry",3),("kitty",2)))
    val rdd7 = sc.parallelize(List(("jerry",2),("tom",1),("shuke",2)))
    //求join
    val res8 = rdd6 join(rdd7)
    println(res8.collect().toBuffer)
    //求左连接和右连接
    val res9 = rdd6.leftOuterJoin(rdd7)
    val res10 = rdd6.rightOuterJoin(rdd7)
    println(res9.collect().toBuffer)
    println(res10.collect().toBuffer)
    //求并集
    val res11 = rdd6 union(rdd7)
    println(res11.collect().toBuffer)
    //按key进行分组
    val res12 = res11.groupByKey()
    println(res12.collect().toBuffer)
    //分别用groupBey和reduceByKey实现单词计数 注意区别
    val res13 = res11.groupByKey().mapValues(_.sum)
    println(res13.collect().toBuffer)
    val res14 = res11.reduceByKey(_ + _) //先局部聚合，再全局聚合
    println(res14.collect().toBuffer)

    val rdd8 = sc.parallelize(List(("tom",1),("tom",2),("jerry",3),("kitty",2)))
    val rdd9 = sc.parallelize(List(("jerry",2),("tom",1),("shuke",2)))
    //cogroup 与groupByKey区别
    val res15 = rdd8.cogroup(rdd9)
    println(res15.collect().toBuffer)
    val rdd10 = sc.parallelize(List(1,2,3,4,5))
    //reduce聚合
    val res16 = rdd10.reduce(_+_)
    println(res16)

    val rdd11 = sc.parallelize(List(("tom",1),("shuke",2),("jerry",3),("kitty",2)))
    val rdd12 = sc.parallelize(List(("jerry",2),("tom",1),("shuke",2),("kitty",5)))
    //按照key进行聚合
    val res17 = rdd11 union(rdd12)
    println(res17.collect().toBuffer)
    //按value的降序排序                            //翻转          //降序排列
    val res18 = res17.reduceByKey(_+_).map(t =>(t._2,t._1)).sortByKey(false).map(t =>(t._2,t._1))
    println(res18.collect().toBuffer)
    //笛卡尔积
    val res19 = rdd11.cartesian(rdd12)
    println(res19.collect().toBuffer)
    //其他：count，top,take,first,takeOrdered
  }
}
