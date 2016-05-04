package scalalab3.chatbotengine.model

case class MessageEntity(val entityType: String, val offset: Integer, val length: Integer, val url: String)