name := "fine-food-analyzer"

version := "1.0"

scalaVersion := "2.11.11"

libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.1.1"
libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.1.1"

libraryDependencies += "org.specs2" %% "specs2-core" % "3.8.9" % "test"

javaOptions += "-Xmx512g"