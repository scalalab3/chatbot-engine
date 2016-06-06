package scalalab3.chatbotengine.model.inbound.messagecontent.media

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class Audio (
    fileId: String,
    duration: Int, 
    performer: Option[String],
    title: Option[String],
    mimeType: Option[String],
    fileSize: Option[Int]) extends MediaContent

object Audio {
  implicit val audioFormat = JsonNaming.snakecase(Json.format[Audio])
}