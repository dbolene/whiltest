name := "common"

organization := "com.whil"


scalaVersion := "2.11.6"

scalacOptions ++= Seq("-unchecked", "-deprecation")

traceLevel in Test := 0

testOptions in Test += Tests.Argument("-oF")

persistTraceLevel := 0

parallelExecution in Test := false

val akkaVersion = "2.4.0"

net.virtualvoid.sbt.graph.Plugin.graphSettings

libraryDependencies += "junit" % "junit" % "4.5" % "test"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.1.5" % "test"

libraryDependencies += "com.typesafe" % "config" % "1.3.0"

