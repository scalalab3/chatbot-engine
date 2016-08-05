name := "chatbot-engine"

version := "1.0"

scalaVersion := "2.11.8"
val akkaVersion = "2.4.8"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "com.typesafe.play" %% "play-json" % "2.5.3",
  "com.github.tototoshi" %% "play-json-naming" % "1.1.0",
  "org.julienrf" %% "play-json-derived-codecs" % "3.3",

  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
  "com.typesafe" % "config" % "1.3.0",
//  "org.scalaz" %% "scalaz-core" % "7.2.3",
  "com.chuusai" %% "shapeless" % "2.3.1",

  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test"
)

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.3"