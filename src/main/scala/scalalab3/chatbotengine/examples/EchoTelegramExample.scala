package scalalab3.chatbotengine.examples

import akka.actor.ActorSystem

import scalalab3.chatbotengine._
import core._
import model._
import scalalab3.chatbotengine.telegram.TelegramLongPoolingEngine

object EchoTelegramExample extends App {
  val engine = new TelegramLongPoolingEngine()
  engine.registerChatBot(new EchoChatBot)
  engine.start(ActorSystem("mySystem"))
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