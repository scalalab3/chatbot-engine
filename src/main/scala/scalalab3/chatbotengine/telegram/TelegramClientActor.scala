package scalalab3.chatbotengine.telegram

import akka.actor.{Actor, ActorLogging, Props}

import scalalab3.chatbotengine.core.ChatBot
import scalalab3.chatbotengine.telegram.TelegramClientActor.{GetUpdates, NewOffset}

class TelegramClientActor(client: TelegramClient, bot: ChatBot) extends Actor with ActorLogging {
  import context.dispatcher
  var offset: Option[Int] = None

  override def receive: Receive = {
    case GetUpdates =>
      for {
        updates <- client.getUpdates(offset)
//        update <- updates
//        response <- bot.receiveMessage(update)
      } {
        log.info(s"Update received - $updates")
//        self ! NewOffset(update.updateId + 1)
//        client.sendMessage(response)
//        log.info(s"Response sent - $response")
      }
    case NewOffset(updateId) =>
      offset = Some(updateId)
  }
}

object TelegramClientActor {
  sealed trait Messages
  case object GetUpdates extends Messages
  case class NewOffset(updateId: Int) extends Messages

  def props(client: TelegramClient, bot: ChatBot): Props =
    Props(new TelegramClientActor(client, bot))
}