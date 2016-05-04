package scalalab3.chatbotengine.model


case class Audio(val fileId: String, val duration: Integer, val performer: String, val title: String,
                 val mimeType: String, val fileSize: Integer)