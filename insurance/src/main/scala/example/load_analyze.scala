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
  val sc = spark.sparkContext
  val sqlContext = new org.apache.spark.sql.hive.HiveContext(sc)

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
  def toHive(): Unit = {
      
      copyFromLocal() // moves the file into hdfs
      sqlContext.sql("""CREATE TABLE IF NOT EXISTS default.Insurance (
      claim_id string,
      customer_id string,
      customer_name string, 
      customer_age int,
      agent_id int,
      agent_name string,
      claim_category string,
      amount int,
      reason string,
      agent_rating int,
      datetime string,
      country string,
      state string,
      approval string,
      reimbursement_id string,
      failure_reason string)
      COMMENT 'Insurance Table'
      ROW FORMAT DELIMITED
      FIELDS TERMINATED BY ','""")
    
    sqlContext.sql("""LOAD DATA INPATH '/user/maria_dev/insurance.csv' INTO TABLE default.Insurance""")
    //var data = sqlContext.sql("""SELECT * FROM default.Insurance""")
    }

def fromCSVFile(): Unit = {
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
    println("Average Customer Age By State")
    var data = sqlContext.sql("""
    SELECT state, AVG(customer_age)
    FROM default.Insurance
    GROUP BY state
    ORDER BY AVG(customer_age) ASC
    """)
    data.show(50)
    }

def claimsByCategory(): Unit = {
    println("Claim Count By Category")
    var data = sqlContext.sql("""
    SELECT claim_category, COUNT(amount)
    FROM default.Insurance
    GROUP By claim_category
    ORDER BY COUNT(amount) DEsC
    """)
    data.show()
    }

def amountByCategory(): Unit = {
    println("Average Claim Amount By Category")
    var data = spark.sql("""
    SELECT claim_category, AVG(amount)
    FROM default.Insurance
    GROUP By claim_category
    ORDER BY AVG(amount) DEsC
    """)
    data.show()
    }

def highestFillingStates(): Unit = {
    println("Top 10 Highest Claim Filing States")
    var data = spark.sql("""
    SELECT state, COUNT(amount)
    FROM default.Insurance
    GROUP By state, country
    ORDER BY COUNT(amount) DEsC LIMIT 10
    """)
    data.show()
    }

def highestClaims(): Unit = {
    println("Top 10 Highest Claim Amounts Nationwide")
    var data = spark.sql("""
    SELECT customer_name, state, claim_category, MAX(amount)
    FROM default.Insurance
    GROUP By customer_name, state, country, claim_category
    ORDER BY MAX(amount) DEsC LIMIT 10
    """)
    data.show()
    }

def mostFiledReason(): Unit = {
    println("Most Common Claim Statuses")
    var data = spark.sql("""
    SELECT failure_reason, COUNT(amount)
    FROM default.Insurance
    GROUP BY failure_reason
    ORDER BY COUNT(failure_reason) DEsC
    """)
    data.show()
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