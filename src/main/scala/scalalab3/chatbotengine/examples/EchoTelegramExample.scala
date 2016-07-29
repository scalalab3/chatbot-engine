package scalalab3.chatbotengine.examples

import akka.actor.{ActorLogging, ActorSystem, Props}

import scala.concurrent.Future
import scalalab3.chatbotengine.core._
import scalalab3.chatbotengine.model.common.Chat
import scalalab3.chatbotengine.model.inbound.messagecontent.media.{Media, Sticker}
import scalalab3.chatbotengine.model.inbound.messagecontent.text.Text
import scalalab3.chatbotengine.model.outbound.{InlineKeyboardButton, InlineKeyboardMarkup}

object EchoTelegramExample extends App {
  val appConfig = AppConfig.load()
  implicit val system = ActorSystem("mySystem")

  new LongPoolingEngine(appConfig.botCredentials)
    .registerChatBot(new EchoChatBot, "EchoChatBot")
    .start()
}

class EchoChatBot extends ChatBot {
  override def roomProps(chat: Chat): Props = Props(new EchoRoom(chat))
}

class EchoRoom(chat: Chat) extends ChatRoom(chat: Chat) with ActorLogging {
  import context._
  override def receive = {
    case Text(_, _, text, _) =>
      log.info(s"Received text message - $text from $sender")
      complete(textReply(text))
    case Media(_, _: Sticker) =>
      complete{
        Future.successful (
          textReply("Sticker received").withKeyboard(
          InlineKeyboardMarkup(Array(Array(
            InlineKeyboardButton(text = "1", None, Some("1")),
            InlineKeyboardButton(text = "2", None, Some("2"))
          )))
        )
    )}
  }
}