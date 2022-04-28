package example

//file:///home/maria_dev/SparkSamples/resources/employee_hive.txt

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

object Analyze {

  val path = "hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/"
  val otherSRC = "file:///home/maria_dev/project_csv.csv"
  val src = "file:///home/maria_dev/insurance.csv"
  val spark: SparkSession = SparkSession
    .builder()
    .master("local[3]")
    .appName("Synergy")
    .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")

  def copyFromLocal(): Unit = {
    val target = path + "insurance.csv"
    println(s"Copying local file $src to $target ...")
    
    val conf = new Configuration()
    val fs = FileSystem.get(conf)

    val localpath = new Path(src)
    val hdfspath = new Path(target)
    
    fs.copyFromLocalFile(false, localpath, hdfspath)
    println(s"Done copying local file $src to $target ...")
  }

def fromCSVFile(): Unit = {
    val sc = spark.sparkContext
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(otherSRC)
    
    //df.show()
    //df.createOrReplaceTempView("insuranceTable") 
    //spark.sql("""
    //SELECT state, AVG(customer_age)
    //FROM insuranceTable
    //GROUP BY state
    //ORDER BY AVG(customer_age) ASC
    //""").show()
    }


def avgAge(): Unit = {

    val sc = spark.sparkContext
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(otherSRC)
    
    println("Average Customer Age By State")
    df.createOrReplaceTempView("insuranceTable") 
    spark.sql("""
    SELECT state, AVG(customer_age)
    FROM insuranceTable
    GROUP BY state
    ORDER BY AVG(customer_age) ASC
    """).show(50)
    }

def claimsByCategory(): Unit = {

    val sc = spark.sparkContext
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(otherSRC)
    
    println("Claim Count By Category")
    df.createOrReplaceTempView("insuranceTable") 
    spark.sql("""
    SELECT claim_category, COUNT(claim_amount)
    FROM insuranceTable
    GROUP By claim_category
    ORDER BY COUNT(claim_amount) DEsC
    """).show()
    }

def amountByCategory(): Unit = {

    val sc = spark.sparkContext
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(otherSRC)
    
    println("Average Claim Amount By Category")
    df.createOrReplaceTempView("insuranceTable") 
    spark.sql("""
    SELECT claim_category, AVG(claim_amount)
    FROM insuranceTable
    GROUP By claim_category
    ORDER BY AVG(claim_amount) DEsC
    """).show()
    }

def highestFillingStates(): Unit = {

    val sc = spark.sparkContext
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(otherSRC)


    println("Top 10 Highest Claim Filing States")
    df.createOrReplaceTempView("insuranceTable") 
    spark.sql("""
    SELECT state, COUNT(claim_amount)
    FROM insuranceTable
    GROUP By state, country
    ORDER BY COUNT(claim_amount) DEsC LIMIT 10
    """).show()
    }

def highestClaims(): Unit = {

    val sc = spark.sparkContext
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(otherSRC)
    
    println("Top 10 Highest Claim Amounts Nationwide")
    df.createOrReplaceTempView("insuranceTable") 
    spark.sql("""
    SELECT customer_name, state, claim_category, MAX(claim_amount)
    FROM insuranceTable
    GROUP By customer_name, state, country, claim_category
    ORDER BY MAX(claim_amount) DEsC LIMIT 10
    """).show()
    }

def mostFiledReason(): Unit = {

    val sc = spark.sparkContext
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(otherSRC)
    
    println("Most Common Claim Statuses")
    df.createOrReplaceTempView("insuranceTable") 
    spark.sql("""
    SELECT failure_reason, COUNT(claim_amount)
    FROM insuranceTable
    GROUP BY failure_reason
    ORDER BY COUNT(failure_reason) DEsC
    """).show()
    }

//they dont have an aproval col
// def approvalByCategory(): Unit = { 

//     val sc = spark.sparkContext
//     val df = spark.read
//       .option("header", true)
//       .option("inferSchema", true)
//       .csv(otherSRC)
    
//     df.createOrReplaceTempView("insuranceTable") 
//     spark.sql("""
//     SELECT claim_category, approval, COUNT(approval)
//     FROM insuranceTable
//     WHERE approval = "Y"
//     GROUP By claim_category, approval
//     ORDER BY COUNT(approval) DEsC
//     """).show()
//     }

//they dont have an aproval col
// def unApprovalByCategory(): Unit = {

//     val sc = spark.sparkContext
//     val df = spark.read
//       .option("header", true)
//       .option("inferSchema", true)
//       .csv(otherSRC)
    
//     df.createOrReplaceTempView("insuranceTable") 
//     spark.sql("""
//     SELECT claim_category, approval, COUNT(approval)
//     FROM insuranceTable
//     WHERE approval = "N"
//     GROUP By claim_category, approval
//     ORDER BY COUNT(approval) DEsC
//     """).show()
//     }


}