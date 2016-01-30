import sbt.Keys._
import sbtprotobuf.{ProtobufPlugin=>PB}

organization := "com.whil"

name := "WhilProtobuf"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-unchecked", "-deprecation")

traceLevel in Test := 0

testOptions in Test += Tests.Argument("-oF")

persistTraceLevel := 0

parallelExecution in Test := false

net.virtualvoid.sbt.graph.Plugin.graphSettings

Seq(PB.protobufSettings: _*)

version in PB.protobufConfig := "2.6.1"

libraryDependencies += "junit" % "junit" % "4.5" % "test"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.1.5" % "test"

libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value % "test"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

libraryDependencies += "log4j" % "log4j" % "1.2.17"

resolvers += "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots"

resolvers += "eaio.com" at "http://eaio.com/maven2"

resolvers += "releases" at "http://oss.sonatype.org/content/repositories/releases"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "krasserm at bintray" at "http://dl.bintray.com/krasserm/maven"
