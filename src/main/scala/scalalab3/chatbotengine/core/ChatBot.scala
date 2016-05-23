package scalalab3.chatbotengine.core

import scalalab3.chatbotengine.model._

trait ChatBot {
  def receiveMessage(message: Update): Option[OutMessage]
}