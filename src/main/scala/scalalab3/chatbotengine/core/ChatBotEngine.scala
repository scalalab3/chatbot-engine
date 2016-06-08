package scalalab3.chatbotengine.core

import shapeless._

trait ChatBotEngine[L <: HList] {
  def registerChatBot[U <: ChatBot](bot: U)(implicit ev: IsDistinctConstraint[U :: L]): ChatBotEngine[U :: L]
}
