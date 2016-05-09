package scalalab3.chatbotengine.model

case class Video(
    fileId: String,
    width: Integer,
    height: Integer,
    duration: Integer,
    thumb: Option[PhotoSize] = None,
    mimeType: Option[String] = None,
    fileSize: Option[Integer] = None)