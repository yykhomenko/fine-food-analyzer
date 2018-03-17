package org.test.analyzer.conf

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object SparkConfig {

  lazy val conf = new SparkConf().setAppName("fine-food-analyzer").setMaster("local[*]")

  lazy val sc = SparkContext.getOrCreate(conf)
  lazy val ss = SparkSession.builder.config(conf).getOrCreate()

  def dfFrom(path: String) = ss.read.option("header", "true").csv(path)
  def rddFrom(path: String) = { sc.setLogLevel("ERROR"); sc.textFile(path) }

  sys.addShutdownHook({
    sc.stop()
    ss.stop()
  })
}