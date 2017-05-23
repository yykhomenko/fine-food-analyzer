Clone
===
```
git clone https://github.com/yurii-khomenko/fine-food-analyzer.git
```
Test
===
In project directory type(http://www.scala-sbt.org/):
```
sbt test
```
Run
===
sbt "run [path to Reviews.csv] [Number of task: 1..4] [fromLanguage] [toLanguage] [outPath]"

For example:
```
sbt "run c:\\work\\project\\Reviews.csv 1"

sbt "run c:\\work\\project\\Reviews.csv 2"

sbt "run c:\\work\\project\\Reviews.csv 3"

sbt "run c:\\work\\project\\Reviews.csv 4 en fr c:\\translated_reviews_out"
```

Question
===

1) We are interested in clean testable code (add tests if you have time). 
2) How do you make sure that there are no duplicates in the file? 
3) We are interested in using full multi core CPU power. 
4) We will be running this on machine with 500MB of RAM. How do you make sure that we are not using more than that? How are you going to monitor the memory usage of your program? 
5) Our goal is to support the files with up to 100M reviews on multiple machines with 500MB of RAM and 4 core CPUs. How are you going to make it happen? 
  
Answers
===
1) Done with tests.
2) I used distinct() function
3) Spark use full multi core CPU power
4) I used 'javaOptions += "-Xmx512g"' option for heap, perhaps on machine with 500mb the app will be swapped. 
Memory monitoring is not implemented yet. I think grafite(https://graphiteapp.org/) or kairosdb(https://kairosdb.github.io/ match simple deploy) + grafana(https://grafana.com/) then what should.
5) It's not a problem for Spark, because it was originally written disturbed, only provide the required number of servers.
6) Not implemented multi-threaded http client for access to google api. Just stub.

Result
===
This program was tested on ultrabook with parameters: i7 6700HQ, Ram: 16gb, SSD: 256gb, Win10Pro, Java 8u131. 
File Reviews.csv have size 286.9mb and 568 455 lines. Here's what happened:
1) 14s 280mb
2) 14s 276mb
3) 20s 200mb
4) 16s 271mb