package scalalab3.chatbotengine.model

case class ChosenInlineResult(val resultId: String, val from: User, val location: Location,
                              val inlineMessageId: String, val query: String)
