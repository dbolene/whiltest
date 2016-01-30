name := """rest"""

version := "1.0-SNAPSHOT"

lazy val rest = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

val akkaVersion = "2.3.11"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "org.apache.httpcomponents" % "httpcore" % "4.2",
  "org.apache.httpcomponents" % "httpclient" % "4.2",
  "org.apache.commons" % "commons-io" % "1.3.2",
  "oauth.signpost" % "signpost-core" % "1.2",
  "oauth.signpost" % "signpost-commonshttp4" % "1.2",
  "junit" % "junit" % "4.5" % "test",
  "org.scalatest" % "scalatest_2.11" % "2.1.5" % "test"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
