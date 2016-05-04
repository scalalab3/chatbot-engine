package scalalab3.chatbotengine.model

case class Document(fileId: String, thumb: Option[PhotoSize] = None,
                    fileName: Option[String] = None, mimeType: Option[String] = None,
                    fileSize: Option[Integer] = None)
