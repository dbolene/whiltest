import sbtprotobuf.{ProtobufPlugin=>PB}
import Keys._

organization := "com.whil"

name := "whil-test"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-feature")

val akkaVersion = "2.3.11"

lazy val protobuf = Project(id = "protobuf",
                            base = file("protobuf"))

lazy val common = Project(id = "common",
                            base = file("common"))
                            .dependsOn(protobuf)

lazy val rest = Project(id = "rest",
                            base = file("rest"))
                            .dependsOn(common)

lazy val root = Project(id = "root",
                            base = file("."))
                          .aggregate(protobuf, common, rest)


