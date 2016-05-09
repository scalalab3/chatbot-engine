package scalalab3.chatbotengine.model

case class MessageEntity(
    entityType: String,
    offset: Integer, 
    length: Integer,
    url: Option[String] = None)