package example

import scala.collection.mutable.ListBuffer
import java.io._
import scala.io.Source
import java.util.Properties
import scala.util.Random
import java.util.Calendar;
import java.time.LocalDateTime
import javax.print.attribute.DateTimeSyntax
import java.util.UUID
import com.opencsv._
import scala.collection.JavaConverters._
import scala.util._

object genData {


object genData {

    val random = new Random()
    val country = "United States of America"

    def main(args: Array[String]):Unit = {
        println(date())
    }

    def getFileLines(filePath: String): List[Any] = {
        val file = new File(filePath)//This is passed to the function as a paramater 
        val fileLines = Source.fromFile(file).getLines().toList // creates file contents as a list
        return fileLines // returns file contents as a list of type: Any if you want to use a diferent type it must be converted
    }

    def names(): String = {
        val nameList = getFileLines("src/main/scala/example/names.txt") //returns a list of names using the getfilelines function
        val name = nameList(random.nextInt(nameList.length)).toString // randomly gets a name from the list
        // commented code is only if you want each name to have a unique id attached to it
        // val id = nameList.indexOf(name) 
        // val idName = s"$id,$name"
        return name
    }

    def age(): String = {
        val ageList = (20 to 90).toList // 20-90 as a list
        var age = ageList(random.nextInt(ageList.length)).toString // randomomly selects a num in the list
        return age
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


    def writeCsvFile(fileName: String,header: List[String], rows: List[List[String]]): Try[Unit] =
    Try(new CSVWriter(new BufferedWriter(new FileWriter(fileName)))).flatMap((csvWriter: CSVWriter) =>
        Try{
        csvWriter.writeAll(
            (header +: rows).map(_.toArray).asJava
        )
        csvWriter.close()
        } match {
        case f @ Failure(_) =>
            // Always return the original failure.  In production code we might
            // define a new exception which wraps both exceptions in the case
            // they both fail, but that is omitted here.
            Try(csvWriter.close()).recoverWith{
            case _ => f
            }
        case success =>
            success
        }
    )

  def id(): String = {
        val randID = UUID.randomUUID().toString() // gives rand uuid 
        return randID
    }
}