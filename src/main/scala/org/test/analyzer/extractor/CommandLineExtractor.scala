package org.test.analyzer.extractor

import org.test.analyzer.helper.CommonHelper._

object CommandLineExtractor {

  def unapplySeq(args: Array[String]): Option[Seq[String]] = {

    if (args.length < 2) printInfoAndExit()

    val filePath = args(0).trim
    val action = args(1).trim
    val translate = if (args.length >= 5) (args(2).trim, args(3).trim, args(4)) else ("", "", "")

    Some(Seq(filePath, action, translate._1, translate._2, translate._3))
  }
}