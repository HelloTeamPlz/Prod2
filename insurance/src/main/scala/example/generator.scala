package example

import scala.collection.mutable.ListBuffer
import java.io._
import scala.io.Source
import java.util.Properties
import scala.util.Random
import java.util.UUID
import scala.collection.JavaConverters._
import scala.util._

object genData {

    val random = new Random()
    val country = "United States of America"
    val insData = "src/main/scala/example/insurance.csv"
    val feilds = "claim_id"+ "," +"customer_id"+ "," +"customer_name"+ "," +"Customer_age"+ "," +"agent_id"+ "," +"agent_name"+ "," +"claim_category"+ "," +"amount"+ "," +"reason"+ "," +"agent_rating"+ "," +"datetime"+ "," +"country"+ "," +"state"+ "," +"approval"+ "," +"reimbursement_id"

    def main(args: Array[String]):Unit = {
      writeToFile(insData, feilds)
      println("Creating Data")
      for(i <- 1 until 10000)
      {
        val claim = claimCat()
        println(s"Creating Data: ${i + 1}")
        val data = id() + "," + id() + "," + names() + "," + age() + "," + agentid() + "," + agent_name() + "," + claimCat() + "," + amount() + "," + reasonCC(claim) + ","  + agentRating() + "," + date() + "," + country + "," + state() 
        appendToFile(insData, data)
      }
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

    def using[A <: {def close(): Unit}, B](param: A)(f: A => B): B =
      try { f(param) } finally { param.close() }

    def writeToFile(fileName:String, data:String) = 
      using (new FileWriter(fileName)) 
      {
        fileWriter => fileWriter.write(data)
      }

    def appendToFile(fileName:String, textData:String) =
      using (new FileWriter(fileName, true)){ 
      fileWriter => using (new PrintWriter(fileWriter)) {
        printWriter => printWriter.println(textData)
      }
    }

    def id(): String = {
      val randID = UUID.randomUUID().toString() // gives rand uuid 
      return randID
    }

    def amount() : String = {
        val amountList = (500 to 30000).toList
        var amount = amountList(random.nextInt(amountList.length)).toString
        return amount
    }

    def claimCat() : String = {
        val categoryList = List( "Dental", "Vision", "Medical", "Life")
        var category = categoryList(random.nextInt(categoryList.length)).toString()


        return category
    }

    def reasonCC(claimCat : String) : String = {
      
      val reasonList1 = List("Teeth cleaning","Cavity", "Braces", "Dental Xrays")
      val reasonList2 = List("Traffic accident", "Sickness/Injury/Death", 
      "Terrorist attack", "Heart Attack")
      val reasonList3 = List("New glasses","Eye exam", "contacts")
      val reasonList = List("Health check up", "Broken Bone", "Flu diagnosis")

      if (claimCat == "Dental") {
        val dent = reasonList1(random.nextInt(reasonList1.length)).toString()
        return dent
      }
      else if(claimCat == "Vision"){
        val vis = reasonList3(random.nextInt(reasonList3.length)).toString()
        return vis
      }
      else if(claimCat == "Medical"){
        val med = reasonList(random.nextInt(reasonList3.length)).toString()
        return med
      }
      else {
        val el = reasonList2(random.nextInt(reasonList2.length)).toString()
        return el
      }  
    }

    def agentid(): String = {
      val agentsid = (1 to 10).toList
      var iD = agentsid(random.nextInt(agentsid.length)).toString()
      return iD
  }

    def agentage(): String = {
      val ageList = (30 to 90).toList
      var age = ageList(random.nextInt(ageList.length)).toString()
      return age
    }

    def agent_name(): String = {
      val names = List("Michael","Christopher","Jessica","Matthew","Ashley","Jennifer","Joshua","Amanda","Daniel","David")
      var name = names(random.nextInt(names.length)).toString()
      return name
    }

    def agentRating(): String = {
      val ratingList = (2 to 10).toList
      val rating = ratingList(random.nextInt(ratingList.length)).toString()
      return rating
    }
}