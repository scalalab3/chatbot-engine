package scalalab3.chatbotengine.model.inbound.messagecontent.media

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class PhotoSize (
    fileId: String,
    width: Int,
    height: Int,
    fileSize: Option[Int])

object PhotoSize {
  implicit val photoSizeFormat = JsonNaming.snakecase(Json.format[PhotoSize])
}