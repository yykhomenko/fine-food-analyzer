package org.test.analyzer.extractor

object FileLineExtractor {

  private val linePattern = """(?s)^(\d+),([^,]+),([^,]+),(".+"|[^,]+),(\d+),(\d+),(\d+),(\d+),(".+"|[^,]+),(".+"|[^,]+)$""".r

  def extractProfileName(line: String) =
    line match {
      case linePattern(_, _, _, profileName, _, _, _, _, _, _) => profileName
      case x => println("ignored: " + x); "unparsed"
    }

  def extractProductId(line: String) =
    line match {
      case linePattern(_, productId, _, _, _, _, _, _, _, _) => productId
      case x => println("ignored: " + x); "unparsed"
    }

  def extractComment(line: String) =
    line match {
      case linePattern(_, _, _, _, _, _, _, _, _, text) => text
      case x => println("ignored: " + x); "unparsed"
    }
}