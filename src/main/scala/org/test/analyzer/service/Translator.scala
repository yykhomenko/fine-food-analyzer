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
}