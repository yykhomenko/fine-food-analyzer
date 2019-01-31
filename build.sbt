name := "fine-food-analyzer"
version := "1.0"
scalaVersion := "2.12.8"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test

javaOptions += "-Xmx512g"