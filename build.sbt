name := "chatbot-engine"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "org.json4s" %% "json4s-jackson" % "3.2.11",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)