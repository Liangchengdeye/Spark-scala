package day01

object Test4 {
  def list_Arr(): Unit ={

  }
  def main(args: Array[String]): Unit = {
    //1.创建一个list
    val list0 = List(4,2,5,3,6,7,1,8)
    //2.将list0中每个元素乘以2生成一个新的list
    val list1 = list0.map(_ *2) //map第一个参数就是逐个取出list集合内元素，第二个参数是执行过程
    //3.将list0中的偶数取出来生成一个新的list
    val list2 = list0.filter(_ %2 == 0) //filter过滤，方法使用同上
    //4.将list0排序生成一个新的集合
    val list3 = list0.sorted
    //5.翻转排序
    val list4 = list3.reverse
    //6.将list0中的元素4个一组，类型为Iterator[list[Int]]
    val it = list0.grouped(4) //迭代器
    val list5 = it.toList //或者it.tobuffer
    //7.将多个list压扁成一个list
    val list6 = list5.flatten
    //8.先按空格切分在压平
    val lines = List("hello java hello scala", "hello scala", "hello python")
    val words = lines.map(_.split(" "))
    val res = lines.flatMap(_.split(" "))//同上，简易写法
    val list7 = words.flatten
    //9.list计算求和
    val list8 = list0.sum

    println(list0); println("-------------------")
    println(list1); println("-------------------")
    println(list2); println("-------------------")
    println(list3); println("-------------------")
    println(list4); println("-------------------")
    println(list5); println("-------------------")
    println(list6); println("-------------------")
    println(list7); println("-------------------")
    println(list8); println("-------------------")
    //---------------------------------------------------
    //1.[并行]计算求和
    val arr = Array(1,2,3,4,5,6,7,8,9,10)
    //普通求和
    val arr1 = arr.sum
    //并行
    val arr2 = arr.par.sum //和线程有关，每个线程计算一部分 ((1+2+3+4)+(5+6+7+8)+(9+10))
    //按照特定的顺序进行聚合
    val arr3 = arr.reduce(_+_) //第一个元素+第二个元素 .reduceLeft()
    val arr4 = arr.par.reduce(_+_) //并行聚合
    //折叠：有初始值（无特定顺序）
    val arr5 = arr.fold(0)(_+_) //fold(0) 0 是初始值，会累加到后面，若是比如10 则会累加+10
    //折叠：有初始值（有特定顺序）
    val arr6 = arr.foldLeft(0)(_+_)

    println(arr1); println("-------------------")
    println(arr2); println("-------------------")
    println(arr3); println("-------------------")
    println(arr5); println("-------------------")
    println(arr6); println("-------------------")

    //聚合
    val list9 = List(List(1,2,3),List(3,4,5),List(2),List(0))
    val res0 = list9.flatten.reduce(_+_)
    val res1 = list9.aggregate(0)(_+_.sum,_+_)
    println(res0); println("-------------------")
    println(res1); println("-------------------")

    //求并集
    val l1  =List(5,6,3,9)
    val l2 = List(1,4,3,7)
    val res2 = l1 union l2
//    求交集
    val res3 = l1 intersect l2
//    求差集
    val res4 = l1 diff l2 //哪个在前就依哪个为准
    println(res2); println("------------------")
    println(res3); println("------------------")
    println(res4); println("------------------")
  }
}
