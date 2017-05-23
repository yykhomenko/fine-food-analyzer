package org.test.analyzer.service

import org.apache.spark.rdd.RDD
import org.test.analyzer.extractor.FileLineExtractor._

object Analyzer {

  def mostActiveUsers(rdd: RDD[String], number: Int) =
    rdd
      .distinct()
      .map(extractProfileName)
      .map((_, 1))
      .reduceByKey(_ + _)
      .sortBy(_._2, ascending = false)
      .take(number)
      .sortBy(_._1)
      .map(_._1)

  def mostCommentedFoodItems(rdd: RDD[String], number: Int) =
    rdd
      .distinct()
      .map(extractProductId)
      .map((_, 1))
      .reduceByKey(_ + _)
      .sortBy(_._2, ascending = false)
      .take(number)
      .sortBy(_._1)
      .map(_._1)

  def mostUsedWords(rdd: RDD[String], number: Int) =
    rdd
      .distinct()
      .map(extractComment)
      .flatMap(_.split("\\W+")
        .filterNot(word => word.isEmpty || word.matches("\\d+")) //non-empty and non-digits
        .map(_.toLowerCase))
      .map((_, 1))
      .reduceByKey(_ + _)
      .sortBy(_._2, ascending = false)
      .take(number)
      .sortBy(_._1)
      .map(_._1)
}