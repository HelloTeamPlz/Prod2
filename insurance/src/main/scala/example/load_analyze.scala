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
    
    df.createOrReplaceTempView("insuranceTable") 
    spark.sql("""
    SELECT state, AVG(customer_age)
    FROM insuranceTable
    GROUP BY state
    ORDER BY AVG(customer_age) ASC
    """).show()
    }

def claimsByCategory(): Unit = {

    val sc = spark.sparkContext
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(otherSRC)
    
    df.createOrReplaceTempView("insuranceTable") 
    spark.sql("""
    SELECT claim_category, COUNT(amount)
    FROM insuranceTable
    GROUP By claim_category
    ORDER BY COUNT(amount) DEsC
    """).show()
    }


def highestFillingStates(): Unit = {

    val sc = spark.sparkContext
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(otherSRC)
    
    df.createOrReplaceTempView("insuranceTable") 
    spark.sql("""
    SELECT state, COUNT(amount)
    FROM insuranceTable
    GROUP By state, country
    ORDER BY COUNT(amount) DEsC LIMIT 10
    """).show()
    }

def mostFiledReason(): Unit = {

    val sc = spark.sparkContext
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(otherSRC)
    
    df.createOrReplaceTempView("insuranceTable") 
    spark.sql("""
    SELECT reason, claim_category, COUNT(amount)
    FROM insuranceTable
    GROUP BY reason, claim_category
    ORDER BY COUNT(reason) DEsC LIMIT 5
    """).show()
    }

def approvalByCategory(): Unit = {

    val sc = spark.sparkContext
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(otherSRC)
    
    df.createOrReplaceTempView("insuranceTable") 
    spark.sql("""
    SELECT claim_category, approval, COUNT(approval)
    FROM insuranceTable
    WHERE approval = "Y"
    GROUP By claim_category, approval
    ORDER BY COUNT(approval) DEsC
    """).show()
    }

def unApprovalByCategory(): Unit = {

    val sc = spark.sparkContext
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(otherSRC)
    
    df.createOrReplaceTempView("insuranceTable") 
    spark.sql("""
    SELECT claim_category, approval, COUNT(approval)
    FROM insuranceTable
    WHERE approval = "N"
    GROUP By claim_category, approval
    ORDER BY COUNT(approval) DEsC
    """).show()
    }


}