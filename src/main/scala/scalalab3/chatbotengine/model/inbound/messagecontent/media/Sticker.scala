package scalalab3.chatbotengine.model.inbound.messagecontent.media

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class Sticker (
                     fileId: String,
                     width: Int,
                     height: Int,
                     thumb: Option[PhotoSize],
                     fileSize: Option[Int]) extends MediaContent

object Sticker {
  implicit val stickerFormat = JsonNaming.snakecase(Json.format[Sticker])
}