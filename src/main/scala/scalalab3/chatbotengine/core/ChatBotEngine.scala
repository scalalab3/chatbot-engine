package scalalab3.chatbotengine.core

trait ChatBotEngine {
  def registerChatBot(bot: => ChatBot, name: String): ChatBotEngine
  def start(): Unit
}