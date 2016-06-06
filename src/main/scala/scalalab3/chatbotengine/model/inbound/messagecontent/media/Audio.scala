package scalalab3.chatbotengine.model.inbound.messagecontent.media

case class Audio (
    fileId: String,
    duration: Int, 
    performer: Option[String],
    title: Option[String],
    mimeType: Option[String],
    fileSize: Option[Int]) extends MediaContent