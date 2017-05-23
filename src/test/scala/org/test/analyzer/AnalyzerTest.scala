package org.test.analyzer

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragments
import org.test.analyzer.conf.SparkConfig._
import org.test.analyzer.service.Analyzer._
import org.test.analyzer.service.Translator._

trait BeforeAllAfterAll extends Specification {
  override def map(fragments: => Fragments) = step(beforeAll()) ^ fragments ^ step(afterAll())
  protected def beforeAll()
  protected def afterAll()
}

object AnalyzerTest extends Specification with BeforeAllAfterAll {

  val rdd = rddFrom(getClass.getClassLoader.getResource("test_reviews.csv").getFile)

  def beforeAll() = println("Starting AnalyzerTest")
  def afterAll() = { stopContext(); println("Ending AnalyzerTest") }

  "AnalyzerTest" should {

    "mostActiveUsers function works correctly" in {
      mostActiveUsers(rdd, 3) === Array("Dan", "Fran W.", "Jared Castle")
    }

    "mostCommentedFoodItems function works correctly" in {
      mostCommentedFoodItems(rdd, 3) === Array("B001RVFDOO", "B004391DK0", "B005K4Q1VI")
    }

    "mostUsedWords function works correctly" in {
      mostUsedWords(rdd, 3) === Array("and", "i", "the")
    }

    "translateComments function works correctly" in {
      val df = dfFrom(getClass.getClassLoader.getResource("test_reviews.csv").getFile)
      translateComments(df, "en", "fr")
        .take(1)(0)
        .getAs[String]("Text")
        .startsWith("This comment was translated form \'en\' to \'fr\' language [") === true
    }
  }
}