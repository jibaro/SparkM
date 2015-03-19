package sparkm

import org.apache.hadoop.hive.serde2.objectinspector.{StructObjectInspector, ObjectInspector}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql._

import org.apache.hadoop.hive.ql.metadata._
import org.slf4j._
import org.apache.hadoop.mapred.{InputFormat, TextInputFormat}
import scala.reflect.ClassTag
import org.apache.hadoop.io.{LongWritable, Writable,Text}

class IncrBy(val x:Int) extends java.io.Serializable{
  def incr(y:Int)=x+y
}
object PartM{
  def logger = LoggerFactory.getLogger(this.getClass.getName)
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("SparkM")
    conf.set("mapreduce.input.fileinputformat.split.minsize",(1024*1024*128).toString)
    conf.set("mapreduce.input.fileinputformat.split.maxsize",(1024*1024*128).toString)
    val spark = new SparkContext(conf)
    System.out.println(spark.getConf.getAll.mkString(","))
    val sqlContext = new org.apache.spark.sql.hive.HiveContext(spark)
    val hive = Hive.get()
    val default_tables = hive.getAllTables("default").toArray()
    val tti = hive.getTable("default","bucketed_user")
//
//    System.out.println(tti.getCompleteName+","+tti.getDataLocation+","+tti.getAllCols.toArray().mkString("|")+","+tti.getBucketCols.toArray().mkString("|"))
//    System.out.println(tti.getSortCols.toArray.mkString("|")+","+tti.getFields.toArray.mkString("|"))
//    val tti_path = tti.getDataLocation
//    val tti_inputformat = tti.getInputFormatClass.asInstanceOf[java.lang.Class[InputFormat[Writable, Writable]]]
//
//    val tti_de = tti.getDeserializer
//    val sbi:StructObjectInspector = tti_de.getObjectInspector.asInstanceOf[StructObjectInspector]
//
//    val tti_rdd = spark.hadoopFile(tti_path.toString,tti_inputformat,classOf[Writable],classOf[Writable],6)
//    tti_rdd.map( p => {
//      val obj = tti_de.deserialize(p._2)
//      val sfields = sbi.getAllStructFieldRefs
//      for( sfield <- sfields){
//      }
//    })
    //System.out.println(tti_rdd.count())



    System.out.println("Conf size="+sqlContext.getAllConfs.size)

    System.out.println(sqlContext.getAllConfs.filter( p => p._1.contains("hive")).mkString(","))
//    sqlContext.sql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING)")

    val tables = sqlContext.sql("show tables")
    System.out.println("size="+tables.collect().size)
    System.out.println("tables="+tables.collect().mkString(","))

    import sqlContext._

    val orgindata = sqlContext.sql("select * from dufang_test").orderBy('id.asc).map( row => (row(0),row))
    val newdata  = sqlContext.sql("select * from newdf_test").orderBy('id.asc).map( row => (row(0),row))





    //val spark = new SparkContext("spark://k1230.mzhen.cn:7077","semicom")
    //val spark = new SparkContext("spark://k1230.mzhen.cn:7077","semicom",null,Array("hdfs://k1223.mzhen.cn:8020/test/empty.jar"))
//    val arr = Array(1,2,3,4,5)
//    val arrrdd=spark.parallelize(arr)
//    val incrby = new IncrBy(10)
//    val res = arrrdd.map(incrby.incr)
//    System.out.println(res.count()+","+res.collect()(0))
    logger.info("here is a log test")
    spark.stop()
  }
}
