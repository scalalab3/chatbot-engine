package scalalab3.chatbotengine.model.inbound.messagecontent.media

case class Sticker (
    fileId: String,
    width: Int,
    height: Int,
    thumb: Option[PhotoSize],
    fileSize: Option[Int]) extends MediaContent