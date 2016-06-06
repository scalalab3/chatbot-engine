package scalalab3.chatbotengine.model.outbound

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class InlineKeyboardButton(text: String, url: Option[String], callbackData: Option[String])

object InlineKeyboardButton {
  implicit val inlineKeyboardButtonFormat = JsonNaming.snakecase(Json.format[InlineKeyboardButton])
}