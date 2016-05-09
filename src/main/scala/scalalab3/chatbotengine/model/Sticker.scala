package scalalab3.chatbotengine.model

case class Sticker(
    fileId: String,
    width: Integer,
    height: Integer,
    thumb: Option[PhotoSize] = None,
    fileSize: Option[Integer] = None)
