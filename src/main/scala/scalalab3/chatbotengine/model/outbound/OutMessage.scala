package scalalab3.chatbotengine.model.outbound

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class OutMessage(
    chatId: String,
    text: String,
    parseMode: Option[String] = None,
    disableWebPagePreview: Option[Boolean] = None,
    disableNotification: Option[Boolean] = None,
    replyToMessageId: Option[Int] = None,
    inlineKeyboardMarkup: Option[InlineKeyboardMarkup] = None)
    // TODO add replyMarkup option

object OutMessage {
  implicit val outMessageFormat = JsonNaming.snakecase(Json.format[OutMessage])
}