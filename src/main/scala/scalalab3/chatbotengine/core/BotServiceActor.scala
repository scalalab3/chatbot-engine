package scalalab3.chatbotengine.core

import akka.actor._
import akka.pattern.pipe

import scalalab3.chatbotengine.model.inbound.update.Update
import scalalab3.chatbotengine.model.outbound._
import scalalab3.chatbotengine.telegram.TelegramClientBase

class BotServiceActor(botRef: ActorRef, client: TelegramClientBase) extends Actor with ActorLogging {
  import context.dispatcher

  import BotServiceActor._

  var offset: Int = 0

  override def receive: Receive = {
    case GetUpdates =>
      client.getUpdates(offset) map UpdatesReceived pipeTo self

    case UpdatesReceived(updates) =>
      val news = updates.filter( _.updateId >= offset )
      offset = news.map(_.updateId).fold(offset)(_ max _) + 1
      news.foreach { update =>
        botRef ! update.updateContent
      }
    case UpdateProcessed(NoReply) => log.info("No response required")
    case UpdateProcessed(msg: OutMessage) =>
      log.info("Response received")
      client.sendMessage(msg)
    case s => log.info(s"unmatched response - $s")
  }
}

object BotServiceActor {
  def props(botRef: ActorRef, client: TelegramClientBase): Props =
    Props(new BotServiceActor(botRef, client))

  sealed trait Messages
  case object GetUpdates extends Messages
  case class UpdatesReceived(updates: Seq[Update]) extends Messages

  case class UpdateProcessed(response: Response) extends Messages
  case object NoResponse extends Messages
}
