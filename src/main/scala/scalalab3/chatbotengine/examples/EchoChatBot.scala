package scalalab3.chatbotengine.examples

import akka.actor.Props

import scalalab3.chatbotengine.core.ChatBot
import scalalab3.chatbotengine.model.common.Chat

class EchoChatBot extends ChatBot {
  override def roomProps(chat: Chat): Props = Props(new EchoRoom(chat))
}