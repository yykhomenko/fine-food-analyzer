package org.test.analyzer.service

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, count}
import org.test.analyzer.extractor.FileLineExtractor._

object Analyzer {

  private def prepare(rdd: RDD[String])(number: Int): Array[String] = rdd
    .map((_, 1))
    .reduceByKey(_ + _)
    .sortBy(_._2, ascending = false)
    .take(number)
    .map(_._1)

  def mostActiveUsers(rdd: RDD[String]): Int => Array[String] =
    prepare(rdd.distinct().map(extractProfileName))

  def mostCommentedFoodItems(rdd: RDD[String]): Int => Array[String] =
    prepare(rdd.distinct().map(extractProductId))

  def mostUsedWords(rdd: RDD[String]): Int => Array[String] = prepare(
    rdd
      .distinct()
      .map(extractComment)
      .flatMap(
        _.split("\\W+")
          .filterNot(word => word.isEmpty || word.matches("\\d+"))
          .map(_.toLowerCase)
      )
  )

  def mostActiveUsersSQL(df: DataFrame)(number: Int): Array[String] = df
    .distinct()
    .groupBy("ProfileName")
    .agg(count("Id"))
    .orderBy(col("count(Id)").desc)
    .limit(number)
    .select("ProfileName")
    .rdd
    .collect()
    .map(_.getString(0))

  def mostCommentedFoodItemsSQL(df: DataFrame)(number: Int): Array[String] = df
    .distinct()
    .groupBy("ProductId")
    .agg(count("Id"))
    .orderBy(col("count(Id)").desc)
    .limit(number)
    .select("ProductId")
    .rdd
    .collect()
    .map(_.getString(0))
}