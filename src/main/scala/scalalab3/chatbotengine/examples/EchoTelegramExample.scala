package scalalab3.chatbotengine.examples

import akka.actor.ActorSystem
import shapeless._
import shapeless.test.illTyped

import scalalab3.chatbotengine._
import core.{TelegramLongPoolingEngine, _}
import model._

object EchoTelegramExample extends App {
  val appConfig = AppConfig.load()

  TelegramLongPoolingEngine()
    .registerChatBot(new EchoChatBot)
    .start(ActorSystem("mySystem"), appConfig.bots)
}

class EchoChatBot extends ChatBot {
  def receiveMessage(update: Update): Option[OutMessage] = {
    for {
      message <- update.message
    } yield {
      val chatId = message.chat.id.toString
      OutMessage(chatId, message.text.getOrElse("Unknown"))
    }
  }
}