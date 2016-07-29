package scalalab3.chatbotengine.model.outbound

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class KeyboardButton (
    text: String, 
    requestContact: Option[Boolean] = None, 
    requestLocation: Option[Boolean] = None)

object KeyboardButton {
  implicit val keyboardButtonFormat = JsonNaming.snakecase(Json.format[KeyboardButton])
}