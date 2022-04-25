package example

//file:///home/maria_dev/SparkSamples/resources/employee_hive.txt

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

object Analyze {

  val path = "hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/"

  def copyFromLocal(): Unit = {
    val src = "file:///home/maria_dev/insurance.csv"
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
    val spark: SparkSession = SparkSession
        .builder()
        .master("local[3]")
        .appName("Synergy")
        .getOrCreate()
        spark.sparkContext.setLogLevel("ERROR")

    val sc = spark.sparkContext
    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv("file:///home/maria_dev/insurance.csv")
    
    //df.show()
    //df.createOrReplaceTempView("insuranceTable") 
    //spark.newSession().sql("SELECT * FROM insuranceTable").show()
    }
}