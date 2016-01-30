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

libraryDependencies += "org.apache.httpcomponents" % "httpcore" % "4.0.1"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.2"

libraryDependencies += "org.apache.commons" % "commons-io" % "1.3.2"

libraryDependencies += "oauth.signpost" % "signpost-core" % "1.2"

libraryDependencies += "oauth.signpost" % "signpost-commonshttp4" % "1.2"



