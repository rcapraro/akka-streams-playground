name := "akka-streams-playground"

version := "0.1"

scalaVersion := "2.13.8"

lazy val akkaVersion = "2.6.18"
lazy val scalaTestVersion = "3.2.11"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion
)

