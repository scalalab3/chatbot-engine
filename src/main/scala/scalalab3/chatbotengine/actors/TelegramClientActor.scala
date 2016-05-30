package scalalab3.chatbotengine.actors

import akka.actor.{Actor, ActorLogging, Props}

import scalalab3.chatbotengine.actors.TelegramClientActor.{GetUpdates, NewOffset}
import scalalab3.chatbotengine.core.ChatBot
import scalalab3.chatbotengine.telegram.TelegramClient

class TelegramClientActor(client: TelegramClient, bot: ChatBot) extends Actor with ActorLogging {
  import context.dispatcher
  var offset: Option[Int] = None

  override def receive: Receive = {
    case GetUpdates =>
      for {
        updates <- client.getUpdates(offset)
        update <- updates
        response <- bot.receiveMessage(update)
      } {
        self ! NewOffset(update.updateId + 1)
        client.sendMessage(response)
        log.info(s"Response sent - $response")
      }
    case NewOffset(updateId) =>
      offset = Some(updateId)
  }
}

object TelegramClientActor {
  case object GetUpdates
  case class NewOffset(updateId: Int)

  def props(client: TelegramClient, bot: ChatBot): Props =
    Props(new TelegramClientActor(client, bot))
}