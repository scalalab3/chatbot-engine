package scalalab3.chatbotengine.model.inbound.messagecontent.media

case class Photo(photoSizes: Seq[PhotoSize], caption: Option[String] = None) extends MediaContent