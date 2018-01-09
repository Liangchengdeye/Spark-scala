package day04
//构造器参数列表
class ApplyDemo(val name:String,var age:Int,var faceValue:Int) {

}
object ApplyDemo{
  /**
    * 调用apply方法在伴生对象里做一个初始化方法
    * apply方法：注入方法
    * 参数列表不需要和构造器的参数列表统一
    * @param name
    * @param age
    * @param faceVale
    * @return
    */
  def apply(name:String,age:Int,faceVale:Int):
      ApplyDemo=new ApplyDemo(name,age,faceVale)

  /**
    * 提取方法
    * 使用该方法来提取固定数量的对象
    * unapply方法会返回一个序列（option）,内部生成一个some对象来存放一些值
    * apply方法和unapply方法会被隐式调用
    * @param applyDemo
    * @return
    */
  def unapply(applyDemo: ApplyDemo): Option[(String, Int, Int)] = {
      if (applyDemo ==null)
      {
        None
      }else
      {
        Some(applyDemo.name,applyDemo.age,applyDemo.faceValue)
      }
  }

}
object applyTest{
  def main(args: Array[String]): Unit = {
    //创建实例
    val applyDemo = ApplyDemo("津津",26,96)//调用apply方法
    //模式匹配
    applyDemo match {
      //隐式调用unapply方法
      case ApplyDemo("津津",age,faceValue)=>println(s"age:$age")
      case _ =>println("NO match nothing")
    }
  }
}