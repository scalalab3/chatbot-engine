package scalalab3.chatbotengine.model

case class InlineQuery(id: String, from: User, query: String, offset: String, location: Option[Location] = None)