package scalalab3.chatbotengine.model.inbound.messagecontent.media

case class Voice (
    fileId: String,
    duration: Int,
    mimeType: Option[String],
    fileSize: Option[Int]) extends MediaContent