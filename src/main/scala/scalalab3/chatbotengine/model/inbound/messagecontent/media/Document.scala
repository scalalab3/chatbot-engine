package scalalab3.chatbotengine.model.inbound.messagecontent.media

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class Document (
                      fileId: String,
                      thumb: Option[PhotoSize],
                      fileName: Option[String],
                      mimeType: Option[String],
                      fileSize: Option[Int],
                      caption: String) extends MediaContent

object Document {
  implicit val documentFormat = JsonNaming.snakecase(Json.format[Document])
}