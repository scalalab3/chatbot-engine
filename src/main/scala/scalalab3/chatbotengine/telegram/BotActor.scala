package scalalab3.chatbotengine.telegram

import akka.actor.{Actor, ActorLogging, Props}

import scalalab3.chatbotengine.core.ChatBot
import scalalab3.chatbotengine.model.Update

object BotActor {
  def props(bot: ChatBot): Props = Props(new BotActor(bot))
}

class BotActor(bot: ChatBot) extends Actor with ActorLogging {
  override def receive: Receive = {
    case a: Update =>
      bot.receiveMessage(a).foreach { r =>
        log.info(s"Response sent - $r")
        sender ! r
      }
  }
}