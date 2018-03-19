package org.test.analyzer.service

import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SaveMode}

object Translator {

  def translateFileComments(df: DataFrame, fromLang: String, toLang: String, outPath: String) =
    translateComments(df, fromLang, toLang)
      .coalesce(1)
      .write
      .mode(SaveMode.Overwrite)
      .csv(outPath)

  def translateComments(df: DataFrame, fromLang: String, toLang: String) = {

    // In this place we must go to google translate over, for example, play! ws library with configured thread pool
    // don’t have time
    val sqlfunc = udf((comment: String) => s"This comment was translated form \'$fromLang\' to \'$toLang\' language [" + comment + "]")

    df
      .distinct()
      .withColumn("Text", sqlfunc(col("Text")))
  }


  def mostActiveUsersSQL(df: DataFrame)(number: Int): Array[String] = {

    val result = df
      .groupBy("ProfileName")
      .agg(count("Id"))
      .orderBy(col("count(Id)").desc)
      .limit(1000)
      .rdd.collect()

    result.foreach(println)
//    val result = df.select("ProfileName").count()
//    val result = df.count().toString

    Array(result.toString)
  }
}