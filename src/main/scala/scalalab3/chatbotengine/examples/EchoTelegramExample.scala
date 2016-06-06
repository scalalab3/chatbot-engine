package scalalab3.chatbotengine.examples

import akka.actor.ActorSystem

import scalalab3.chatbotengine.core.{TelegramLongPoolingEngine, _}
import scalalab3.chatbotengine.model.inbound.messagecontent.text.Text
import scalalab3.chatbotengine.model.inbound.update.{Message, Update}
import scalalab3.chatbotengine.model.outbound.OutMessage

object EchoTelegramExample extends App {
  val appConfig = AppConfig.load()

  TelegramLongPoolingEngine()
    .registerChatBot(new EchoChatBot)
    .start(ActorSystem("mySystem"), appConfig.bots)
}

class EchoChatBot extends ChatBot {
  def receiveMessage(update: Update): Option[OutMessage] = update match {
    case Update(_, Message(_, _, chat, _, Text(_, _, text, _))) =>
      Some(OutMessage(chat.id.toString, text))
    case _ =>
      println("NOHIT")
      None
  }
}