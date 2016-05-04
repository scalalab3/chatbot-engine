package scalalab3.chatbotengine.model

case class InlineQuery(val id: String, val from: User, val location: Location, val query: String, val offset: String)