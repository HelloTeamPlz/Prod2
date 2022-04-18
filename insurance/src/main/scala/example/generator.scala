package example

import scala.collection.mutable.ListBuffer
import java.io.File
import scala.io.Source
import java.util.Properties
import scala.util.Random

object genData {
    def main(args: Array[String]):Unit = {
        println(data())
    }

    val random = new Random()

    def name(): String = {
        val filePath = "src/main/scala/example/names.txt"
        val file = new File(filePath)
        val nameList = Source.fromFile(file).getLines().toList
        val name = nameList(random.nextInt(nameList.length))
        return name
    }
    def age(): String = {
        val ageList = (20 to 90).toList
        var age = ageList(random.nextInt(ageList.length)).toString
        return age
    }
    def data(): Any = {
        val cvsFeilds = Array("id", "name", "age")
    }
    def reasons(): String={}
}