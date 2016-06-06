package scalalab3.chatbotengine.model.inbound.messagecontent.media

case class Document (
                      fileId: String,
                      thumb: Option[PhotoSize],
                      fileName: Option[String],
                      mimeType: Option[String],
                      fileSize: Option[Int],
                      caption: String) extends MediaContent