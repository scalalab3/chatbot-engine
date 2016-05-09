package scalalab3.chatbotengine.model

case class CallbackQuery(
    id: String, 
    from: User, 
    data: String,
    message: Option[Message] = None, 
    inlineMessageId: Option[String] = None)
