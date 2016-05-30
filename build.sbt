name := "chatbot-engine"

version := "1.0"

scalaVersion := "2.11.8"
val akkaVersion = "2.4.0"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "org.json4s" %% "json4s-jackson" % "3.2.11",
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe" % "config" % "1.3.0",
  "com.chuusai" %% "shapeless" % "2.3.1",

  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test"
)