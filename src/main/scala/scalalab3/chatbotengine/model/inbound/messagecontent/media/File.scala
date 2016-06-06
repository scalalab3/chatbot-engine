package scalalab3.chatbotengine.model.inbound.messagecontent.media

case class File(
    fileId: String, 
    fileSize: Option[Integer] = None, 
    filePath: Option[String] = None)