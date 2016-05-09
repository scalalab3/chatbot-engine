package scalalab3.chatbotengine.model

case class ChosenInlineResult(
    resultId: String, 
    from: User, 
    query: String,
    location: Option[Location] = None,
    inlineMessageId: Option[String] = None)
