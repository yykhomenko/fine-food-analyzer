package org.test.analyzer.service

import org.apache.spark.rdd.RDD
import org.test.analyzer.extractor.FileLineExtractor._

object Analyzer {

  private def prepare(rdd: RDD[String])(number: Int): Array[String] =
    rdd
      .map((_, 1))
      .reduceByKey(_ + _)
      .sortBy(_._2, ascending = false)
      .take(number)
      .sortBy(_._1)
      .map(_._1)

  def mostActiveUsers(rdd: RDD[String]): Int => Array[String] =
    prepare(
      rdd
        .distinct()
        .map(extractProfileName))

  def mostCommentedFoodItems(rdd: RDD[String]): Int => Array[String] =
    prepare(
      rdd
        .distinct()
        .map(extractProductId))

  def mostUsedWords(rdd: RDD[String]): Int => Array[String] =
    prepare(
      rdd
        .distinct()
        .map(extractComment)
        .flatMap(_.split("\\W+")
          .filterNot(word => word.isEmpty || word.matches("\\d+")) //non-empty and non-digits
          .map(_.toLowerCase)))
}