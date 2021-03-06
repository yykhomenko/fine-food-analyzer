package org.test.analyzer.conf

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object SparkConfig {

  lazy val conf = new SparkConf().setAppName("fine-food-analyzer").setMaster("local[*]")
//  lazy val conf = new SparkConf().setAppName("fine-food-analyzer").setMaster("spark://192.168.0.15:7077")

  val sc = SparkContext.getOrCreate(conf)
  val ss = SparkSession.builder.config(conf).getOrCreate()

  def rddFrom(path: String) = { sc.setLogLevel("ERROR"); sc.textFile(path) }
  def dfFrom(path: String) = ss.read.option("header", "true").csv(path)
}