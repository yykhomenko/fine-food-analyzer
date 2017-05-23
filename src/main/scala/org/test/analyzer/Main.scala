package org.test.analyzer

import org.test.analyzer.conf.SparkConfig._
import org.test.analyzer.extractor.CommandLineExtractor
import org.test.analyzer.helper.CommonHelper._
import org.test.analyzer.service.Analyzer._
import org.test.analyzer.service.Translator._

object Main extends App {

  val CommandLineExtractor(filePath, action, fromLang, toLang, outPath) = args

  val startTime = currentTime
  val startMem = currentHeap

  action match {
    case "1" => mostActiveUsers(rddFrom(filePath), 1000)
      .foreach(println(_))
      stopContext()

    case "2" => mostCommentedFoodItems(rddFrom(filePath), 1000)
      .foreach(println(_))
      stopContext()

    case "3" => mostUsedWords(rddFrom(filePath), 1000)
      .foreach(println(_))
      stopContext()

    case "4" => translateFileComments(dfFrom(filePath), fromLang, toLang, outPath)
      printCheckDirectory(outPath)
      stopSession()

    case _   => printUnknownActionAndExit()
  }

  printMemoryConsumed(startMem)
  printSpentTime(startTime)
}