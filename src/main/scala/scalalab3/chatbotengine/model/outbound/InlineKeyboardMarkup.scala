package scalalab3.chatbotengine.model.outbound

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class InlineKeyboardMarkup(inlineKeyboard: Array[Array[InlineKeyboardButton]])

object InlineKeyboardMarkup {
  implicit val inlineKeyboardMarkupFormat = JsonNaming.snakecase(Json.format[InlineKeyboardMarkup])
}