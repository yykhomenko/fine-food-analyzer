package org.test.analyzer.helper

object CommonHelper {

  def currentTime = System.nanoTime()
  def currentHeap = Runtime.getRuntime.totalMemory() - Runtime.getRuntime.freeMemory()
  def toMs(nanoseconds: Long) = nanoseconds / 1000000
  def toMb(bytes: Long) = bytes / 1048576

  def printInfoAndExit() {
    println("\nPlease type 2 arguments(input file path and number of action)\n\n" +
      "[path to Reviews.csv] [Number of task: 1..4] [fromLanguage] [toLanguage] [outPath]" +
      "\n" +
      "tasks:\n" +
      "\t 1) Finding 1000 most active users (profile names)\n" +
      "\t 2) Finding 1000 most commented food items (item ids).\n" +
      "\t 3) Finding 1000 most used words in the reviews\n" +
      "\t 4) Translate all the reviews using Google Translate API(stub).")
    System.exit(1)
  }

  def printUnknownActionAndExit() { println("Unknown action, please type action from 1 to 4"); System.exit(2) }

  def printCheckDirectory(path: String) = println("\nTranslate completed! Check the directory \'" + path + '\'')

  def printSpentTime(startTime: Long) = println(s"Spent time: ${toMs(currentTime - startTime)}ms.")

  def printMemoryConsumed(startMem: Long) = println(s"\nMemory consumed: ${toMb(currentHeap - startMem)}mb.")

  def withMetrics(block: => Unit) = {
    val startTime = currentTime
    val startMem = currentHeap

    block

    printSpentTime(startTime)
    printMemoryConsumed(startMem)
  }
}