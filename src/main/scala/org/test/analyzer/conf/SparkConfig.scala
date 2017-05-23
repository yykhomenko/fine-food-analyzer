package org.test.analyzer.conf

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object SparkConfig {

  lazy val conf = new SparkConf().setAppName("fine-food-analyzer").setMaster("local[*]")
  lazy val sc = new SparkContext(conf)

  lazy val sparkSession = SparkSession.builder
    .config(conf)
    .appName("fine-food-analyzer")
    .getOrCreate()

  def dfFrom(path: String) = sparkSession.read.option("header", "true").csv(path)
  def rddFrom(path: String) = { sc.setLogLevel("ERROR"); sc.textFile(path) }

  def stopContext() = sc.stop()
  def stopSession() = sparkSession.stop()
}