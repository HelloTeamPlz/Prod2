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
        println(randomDate())
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


    def country(): String = {
        val country = "United States of America"
        return country
    }

    def state(): String = {
        val filePath = "src/main/scala/example/states.txt"
        val file = new File(filePath)
        val stateList = Source.fromFile(file).getLines().toList
        val state = stateList(random.nextInt(stateList.length))
        return state
    }

    // def randomDate(): String = {
    //     val day = new scala.util.Random.nextInt(30).toString
    //     val month = new scala.util.Random.nextInt(12).toString
    //     val year = new scala.util.Random.nextInt(2022).toString
    //     print(day)
    // }


    def randomDate(): String = {
        val yearAdd = 201
        val month = scala.util.Random
        val day = scala.util.Random
        val year = scala.util.Random
        // println((month.nextInt(12).toString), (day.nextInt(30).toString))
        println(month.nextInt(12).toString)
        println(day.nextInt(30).toString)
        yearAdd.toString + year.nextInt(9).toString
    }

}