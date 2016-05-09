package scalalab3.chatbotengine.model

case class PhotoSize(
    fileId: String, 
    width: Integer, 
    height: Integer, 
    fileSize: Option[Integer] = None)