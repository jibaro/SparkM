package sparkm.hbase

import java.io.{DataOutputStream, ByteArrayOutputStream}

import org.apache.spark.{SparkContext, SparkConf}
import org.slf4j.LoggerFactory
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.protobuf.ProtobufUtil;
import org.apache.hadoop.hbase.protobuf.generated.ClientProtos;
import org.apache.hadoop.hbase.util.Base64;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
/**
 * Created by vv on 15-3-19.
 * Email : yangwei@semidata.com
 */
object HBaseDump {
  def logger = LoggerFactory.getLogger(this.getClass.getName)
  def main(args:Array[String])  {
    val conf = new SparkConf().setAppName("HBaseDump")
    val spark = new SparkContext(conf)
    val hbaseConf = HBaseConfiguration.create()
    hbaseConf.set(TableInputFormat.INPUT_TABLE,"kk_kkxx_orz")

    val scan = new org.apache.hadoop.hbase.client.Scan();
    scan.setStartRow(Bytes.toBytes("0"))
    scan.setStopRow(Bytes.toBytes("1"))
    scan.addFamily(Bytes.toBytes("0"))
    scan.addColumn(Bytes.toBytes("0"),Bytes.toBytes("XSSD"))

    val proto = ProtobufUtil.toScan(scan)
    val scanStr = Base64.encodeBytes(proto.toByteArray())
    hbaseConf.set(TableInputFormat.SCAN,scanStr)

    val hbaseRDD = spark.newAPIHadoopRDD(hbaseConf,classOf[TableInputFormat],classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable],classOf[org.apache.hadoop.hbase.client.Result])
    System.out.println(hbaseRDD.take(1).toString)
    logger.info("now we count kk_kkxx_orz = " + hbaseRDD.count())
  }
}
