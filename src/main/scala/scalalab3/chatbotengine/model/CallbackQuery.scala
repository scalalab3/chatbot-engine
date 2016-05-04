package scalalab3.chatbotengine.model

case class CallbackQuery(val id: String, val from: User, val message: Message, val inlineMessageId: String,
                         val data: String)
