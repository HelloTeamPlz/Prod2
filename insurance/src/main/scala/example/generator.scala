package example

import scala.collection.mutable.ListBuffer
import java.io.File
import scala.io.Source
import java.util.Properties
import scala.util.Random
import java.util.UUID

object genData {

    val random = new Random()

    def main(args: Array[String]):Unit = {
        println(names())
    }

    def getFileLines(filePath: String): List[Any] = {
        val file = new File(filePath)//This is passed to the function as a paramater 
        val fileLines = Source.fromFile(file).getLines().toList // creates file contents as a list 
        return fileLines // returns file contents as a list of type: Any if you want to use a diferent type it must be converted
    }

    def names(): String = {
        val nameList = getFileLines("src/main/scala/example/names.txt")
        val name = nameList(random.nextInt(nameList.length)).toString
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