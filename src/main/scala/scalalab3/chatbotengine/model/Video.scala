package scalalab3.chatbotengine.model

case class Video(val fileId: String, val width: Integer, val height: Integer, val duration: Integer,
                 val thumb: PhotoSize, val mimeType: String, val fileSize: Integer)