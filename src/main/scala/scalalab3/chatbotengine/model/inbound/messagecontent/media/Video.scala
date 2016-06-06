package scalalab3.chatbotengine.model.inbound.messagecontent.media

case class Video (
                   fileId: String,
                   width: Int,
                   height: Int,
                   duration: Int,
                   thumb: Option[PhotoSize],
                   mimeType: Option[String],
                   fileSize: Option[Int],
                   caption: String) extends MediaContent