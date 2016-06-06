package scalalab3.chatbotengine.model.inbound.messagecontent.media

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class Video (
                   fileId: String,
                   width: Int,
                   height: Int,
                   duration: Int,
                   thumb: Option[PhotoSize],
                   mimeType: Option[String],
                   fileSize: Option[Int],
                   caption: String) extends MediaContent

object Video {
  implicit val videoFormat = JsonNaming.snakecase(Json.format[Video])
}