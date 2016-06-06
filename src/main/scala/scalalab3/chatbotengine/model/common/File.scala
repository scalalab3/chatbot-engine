package scalalab3.chatbotengine.model.common

case class File(
    fileId: String, 
    fileSize: Option[Integer] = None, 
    filePath: Option[String] = None)