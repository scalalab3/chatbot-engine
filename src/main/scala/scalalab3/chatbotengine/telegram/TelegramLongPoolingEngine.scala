package scalalab3.chatbotengine.telegram

import akka.actor.ActorSystem

import scala.collection.mutable.ListBuffer
import scalalab3.chatbotengine.core._

class TelegramLongPoolingEngine extends ChatBotEngine {
  var registeredBots: scala.collection.mutable.ListBuffer[ChatBot] = ListBuffer.empty

  def registerChatBot(bot: ChatBot): Unit = registeredBots += bot

  def start(as: ActorSystem) =
    as.actorOf(PoolingEngineActor.props(registeredBots.toList))
}

