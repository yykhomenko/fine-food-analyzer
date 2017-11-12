package org.test.analyzer

import org.apache.spark.rdd.RDD
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.test.analyzer.conf.SparkConfig._
import org.test.analyzer.service.Analyzer._
import org.test.analyzer.service.Translator._

@RunWith(classOf[JUnitRunner])
class AnalyzerTest extends FunSuite {

  val rdd: RDD[String] = rddFrom(getClass.getClassLoader.getResource("test_reviews.csv").getFile)

  test("mostActiveUsers function works correctly") {
    assert(mostActiveUsers(rdd)(3) === Array("Dan", "Fran W.", "Jared Castle"))
  }

  test("mostCommentedFoodItems function works correctly") {
    assert(mostCommentedFoodItems(rdd)(3) === Array("B001RVFDOO", "B004391DK0", "B005K4Q1VI"))
  }

  test("mostUsedWords function works correctly") {
    assert(mostUsedWords(rdd)(3) === Array("and", "i", "the"))
  }

  test("translateComments function works correctly") {
    val df = dfFrom(getClass.getClassLoader.getResource("test_reviews.csv").getFile)
    assert(translateComments(df, "en", "fr")
            .take(1)(0)
            .getAs[String]("Text")
            .startsWith("This comment was translated form \'en\' to \'fr\' language [") === true)
  }
}