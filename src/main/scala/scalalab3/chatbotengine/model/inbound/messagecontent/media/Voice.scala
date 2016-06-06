package scalalab3.chatbotengine.model.inbound.messagecontent.media

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class Voice (
    fileId: String,
    duration: Int,
    mimeType: Option[String],
    fileSize: Option[Int]) extends MediaContent

object Voice {
  implicit val voiceFormat = JsonNaming.snakecase(Json.format[Voice])
}