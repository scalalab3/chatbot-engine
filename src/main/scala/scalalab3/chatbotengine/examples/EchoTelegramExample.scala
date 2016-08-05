package scalalab3.chatbotengine.examples

import akka.actor.ActorSystem

import scalalab3.chatbotengine.config.AppConfig
import scalalab3.chatbotengine.core._

object EchoTelegramExample extends App {
  val appConfig = AppConfig.load()
  implicit val system = ActorSystem("mySystem")

  new LongPoolingEngine(appConfig.botCredentials)
    .registerChatBot(new EchoChatBot, "EchoChatBot")
    .start()
}

object EchoTelegramExample2 extends App {
  val appConfig = AppConfig.load()
  implicit val system = ActorSystem("mySystem")

  new WebHookEngine
//  (appConfig.botCredentials)
//    .registerChatBot(new EchoChatBot, "EchoChatBot")
//    .start()
}