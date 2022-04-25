package example

//file:///home/maria_dev/SparkSamples/resources/employee_hive.txt

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

object Analyze {

  val path = "hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/"
  def main(args: Array[String]) {
    createFile()
    copyFromLocal()
  }

  def copyFromLocal(): Unit = {
    val src = "file:///home/maria_dev/files2.txt"
    val target = path + "files2.txt"
    println(s"Copying local file $src to $target ...")
    
    val conf = new Configuration()
    val fs = FileSystem.get(conf)

    val localpath = new Path(src)
    val hdfspath = new Path(target)
    
    fs.copyFromLocalFile(false, localpath, hdfspath)
    println(s"Done copying local file $src to $target ...")
  }
}