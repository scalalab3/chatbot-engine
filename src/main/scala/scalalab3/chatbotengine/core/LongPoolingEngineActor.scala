package scalalab3.chatbotengine.core

import akka.actor.{Actor, ActorRef, Props}

import scala.concurrent.duration._
import scala.language.postfixOps
import scalalab3.chatbotengine.telegram.{TelegramClient, TelegramClientBase}

class LongPoolingEngineActor(client: BotCredential => TelegramClientBase) extends Actor {
  import context._
  import BotServiceActor._
  import LongPoolingEngineActor._

  override def receive: Receive = receiveInternal(List.empty)

  def receiveInternal(updaters: List[ActorRef]): Receive = {
    case RegisterBot(botRef, credential) =>
      val childRef = context.actorOf(BotServiceActor.props(botRef, client(credential)), name = s"bot-service-${credential.auth}")
      context.become(receiveInternal(childRef :: updaters))

    case Start => updaters.foreach { childRef =>
      context.system.scheduler.schedule(1 second, 1 second, childRef, GetUpdates)
    }
  }
}

object LongPoolingEngineActor {
  def props: Props = Props(new LongPoolingEngineActor(TelegramClient.apply))

  sealed trait Messages
  case class RegisterBot(botRef: ActorRef, credential: BotCredential) extends Messages
  case object Start extends Messages
}

