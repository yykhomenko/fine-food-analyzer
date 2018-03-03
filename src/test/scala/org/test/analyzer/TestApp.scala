package org.test.analyzer

import org.scalatest.{Matchers, WordSpec}
import org.test.analyzer.conf.SparkConfig._
import org.test.analyzer.service.Analyzer._
import org.test.analyzer.service.Translator._

class TestApp extends WordSpec with Matchers {

  val file = getClass.getClassLoader.getResource("test_reviews.csv").getFile
  val reviews = rddFrom(file)

  "The fine food analyzer" should {

    "search the most active users" in {
      assert(mostActiveUsers(reviews)(3) === Array("Dan", "Fran W.", "Jared Castle"))
    }

    "search the most commented food items" in {
      assert(mostCommentedFoodItems(reviews)(3) === Array("B001RVFDOO", "B004391DK0", "B005K4Q1VI"))
    }

    "search the most used words" in {
      assert(mostUsedWords(reviews)(3) === Array("and", "i", "the"))
    }

    "translate user comments to other language" in {
      val df = dfFrom(getClass.getClassLoader.getResource("test_reviews.csv").getFile)
      val rows = translateComments(df, "en", "fr")
      val comment = rows.take(1)(0).getAs[String]("Text")

      assert(comment.startsWith("This comment was translated form \'en\' to \'fr\' language ["))
    }
  }
}