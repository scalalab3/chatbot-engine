package scalalab3.chatbotengine.model.outbound

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class ReplyKeyboardMarkup (
    keyboard: Array[Array[KeyboardButton]], 
    resizeKeyboard: Option[Boolean] = None,
    oneTimeKeyboard: Option[Boolean] = None,
    selective: Option[Boolean] = None)


object ReplyKeyboardMarkup {
  implicit val replyKeyboardMarkupFormat = JsonNaming.snakecase(Json.format[ReplyKeyboardMarkup])
}