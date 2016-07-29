package scalalab3.chatbotengine.core

import akka.actor.{ActorSystem, Props}

class LongPoolingEngine (creds: Map[String, BotCredential])
                        (implicit actorSystem: ActorSystem) extends ChatBotEngine {
  import LongPoolingEngineActor._

  val root = actorSystem.actorOf(LongPoolingEngineActor.props, name = "root")

  def registerChatBot(bot: => ChatBot, name: String): LongPoolingEngine = {
    creds.get(name).foreach { credential =>
      val botRef = actorSystem.actorOf(Props(bot), name = s"bot-${credential.auth}")
      root ! RegisterBot(botRef, credential)
    }
    this
  }

  def start(): Unit = root ! Start
}
