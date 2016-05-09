package scalalab3.chatbotengine.model


case class Audio(
    fileId: String,
    duration: Int, 
    performer: Option[String] = None, 
    title: Option[String] = None,
    mimeType: Option[String] = None,
    fileSize: Option[Int] = None)