package example

import scala.collection.mutable.ListBuffer
import java.io.File
import scala.io.Source
import java.util.Properties
import scala.util.Random
import java.util.Calendar;
import java.time.LocalDateTime
import javax.print.attribute.DateTimeSyntax


object genData {

    def main(args: Array[String]):Unit = {
        println(date())
    }

    val random = new Random()
    val country = "United States of America"



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

    def state(): String = {
        val filePath = "src/main/scala/example/states.txt"
        val file = new File(filePath)
        val stateList = Source.fromFile(file).getLines().toList
        val state = stateList(random.nextInt(stateList.length))
        return state
    }

    def date(): String = {
        val filePath = "src/main/scala/example/date.txt"
        val file = new File(filePath)
        val dateList = Source.fromFile(file).getLines().toList
        val date = dateList(random.nextInt(dateList.length))
        return date
    }

}