name := "fine-food-analyzer"
version := "1.0"
scalaVersion := "2.11.12"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test

javaOptions += "-Xmx512g"