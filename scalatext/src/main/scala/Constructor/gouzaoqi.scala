package Constructor
//构造器()
/**
  *主构造器的参数列表要放到类名的后面，和类名放到一起
  * val修饰的构造参数具有不可变性
  * var修饰的构造参数具有可变性
  * 不添加关键字则默认为val 而且要用get方法，否则只能在该类中使用
  * @param name
  * @param age
  * @param faceValue
  */
class gouzaoqi(val name: String,var age:Int,faceValue:Int=90){
  var gender:String= _
  def getFaceValue():Int={                  //默认值
    faceValue //返回一个int类型的            //不添加val或者var关键字就要通过方法才能返回该对象
  }
  //辅助构造器
  def this(name:String,age:Int,faceValue:Int,gender:String){
    this(name,age,faceValue) //第一行辅助构造器，先调用主构造器
    this.gender = gender
  }
}
object gouzaoqi{
  def main(args: Array[String]): Unit = {
    val s=new gouzaoqi("kevin",26,80)//构造器赋值
    println(s.name)
    println(s.age)
    println(s.getFaceValue())
    //调用辅助构造器
    val s1=new gouzaoqi("shasha",26,98,"女")
    println(s1.gender)
    println(s1.name)
    println(s1.age)
    println(s1.getFaceValue())
  }
}
