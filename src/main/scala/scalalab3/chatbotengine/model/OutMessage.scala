package scalalab3.chatbotengine.model

case class OutMessage(
    chatId: String,
    text: String,
    parseMode: Option[String] = None,
    disableWebPagePreview: Option[Boolean] = None,
    disableNotification: Option[Boolean] = None,
    replyToMessageId: Option[Int] = None) 
    // TODO add replyMarkup option