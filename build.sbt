name := "SparkM"

unmanagedJars in Compile +=
  Attributed.blank(
    file(scala.util.Properties.javaHome) / "/lib/jfxrt.jar")

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-library" % "2.10.4",
  "org.scala-lang" % "scala-actors" % "2.10.4",
  "org.apache.spark" % "spark-core_2.10" % "1.2.0",
  "org.apache.spark" % "spark-sql_2.10" % "1.2.0",
  "org.apache.spark" % "spark-hive_2.10" % "1.2.0",
  "org.slf4j" % "slf4j-log4j12" % "1.7.7",
  "org.scalatest" % "scalatest_2.10" % "2.2.2",
  "org.apache.hadoop" % "hadoop-mapreduce-client-core" % "2.6.0",
  "org.apache.hadoop" % "hadoop-common" % "2.6.0",
  "org.apache.hadoop" % "hadoop-client" % "2.6.0",
  "org.apache.hadoop" % "hadoop-hdfs" % "2.6.0",
  "org.apache.hadoop" % "hadoop-yarn-common" % "2.6.0",
  "org.apache.hbase" % "hbase-client" % "0.98.11-hadoop2",
  "org.apache.hbase" % "hbase-common" % "0.98.11-hadoop2",
  "org.apache.hbase" % "hbase" % "0.98.11-hadoop2",
  "org.apache.hbase" % "hbase-server" % "0.98.11-hadoop2")

libraryDependencies += "org.scalafx" % "scalafx_2.10" % "2.2.67-R10"

version := "1.0"
