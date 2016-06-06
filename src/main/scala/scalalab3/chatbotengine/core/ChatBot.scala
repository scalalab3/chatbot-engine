package scalalab3.chatbotengine.core

import scalalab3.chatbotengine.model._
import scalalab3.chatbotengine.model.inbound.update.Update
import scalalab3.chatbotengine.model.outbound.OutMessage

trait ChatBot {
  def receiveMessage(message: Update): Option[OutMessage]
}