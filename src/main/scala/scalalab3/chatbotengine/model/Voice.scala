package scalalab3.chatbotengine.model

case class Voice(fileId: String, duration: Integer, mimeType: Option[String] = None, fileSize: Option[Integer] = None)
