package sparkm

import org.apache.spark.{SparkContext, SparkConf}
import org.slf4j.LoggerFactory

/**
 * Created by vv on 14-12-29.
 * Email : yangwei@semidata.com
 */
object GenData {
  def logger = LoggerFactory.getLogger(this.getClass.getName)
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("SparkM")
    val spark = new SparkContext(conf)
    val part = spark.parallelize(1 to 9 , 9)
    val mappart = part.map( num => {
      for(cnt <- 0 to 9)
        yield num +""+cnt
    })
    mappart.map(vstring => vstring.mkString(",")).saveAsTextFile("/tmp/mappart")
    val mp = spark.wholeTextFiles("/tmp/mappart").flatMap(p => p._2.trim().split(",").map(_.toInt)).reduce(_+_)
//    part.foreachPartition( idStart => { for(nid <- 0 to 10) println idStart.} )

  }
}
