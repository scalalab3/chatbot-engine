package scalalab3.chatbotengine.core

import akka.actor.{ActorRef, ActorSystem}
import shapeless._
import shapeless.ops.hlist.IsHCons

import scala.concurrent.duration._
import scala.language.postfixOps

import scalalab3.chatbotengine.actors.TelegramClientActor
import scalalab3.chatbotengine.actors.TelegramClientActor._
import scalalab3.chatbotengine.telegram.TelegramClient

class TelegramLongPoolingEngine[L <: HList](bots: List[ChatBot]) extends ChatBotEngine[L] {
  def registerChatBot[U <: ChatBot](bot: U)
                                   (implicit ev: IsDistinctConstraint[U :: L]) =
    new TelegramLongPoolingEngine[U :: L](bot :: bots)

  def start(actorSystem: ActorSystem, creds: Map[String, BotCredential])
           (implicit hasOne: IsHCons[L]): List[ActorRef] = {
    for {
      bot <- bots
      cred <- creds.get(bot.getClass.getSimpleName)
    } yield {
      val client = new TelegramClient(cred.auth, cred.token)
      val clientActorRef = actorSystem.actorOf(TelegramClientActor.props(client, bot))
      actorSystem.scheduler.schedule(1 second, 1 second, clientActorRef, GetUpdates)(actorSystem.dispatcher)
      clientActorRef
    }
  }
}

object TelegramLongPoolingEngine {
  def apply() = new TelegramLongPoolingEngine[HNil](List.empty)
}