package scalalab3.chatbotengine.model.outbound

import play.api.libs.functional.syntax._
import play.api.libs.json._

sealed trait Response
case object NoReply extends Response

case class OutMessage (
                        chatId: String,
                        text: String,
                        parseMode: Option[String] = None, //Markdown or HTML
                        disableWebPagePreview: Option[Boolean] = None,
                        disableNotification: Option[Boolean] = None,
                        replyToMessageId: Option[Int] = None,
                        replyMarkup: Option[InlineKeyboardMarkup] = None) extends Response {
  def withKeyboard(board: InlineKeyboardMarkup) = copy(replyMarkup = Some(board))
}

object OutMessage {
  implicit val outMessageFormat: OWrites[OutMessage] = (
    (JsPath \ "chat_id").write[String] and
      (JsPath \ "text").write[String] and
      (JsPath \ "parse_mode").writeNullable[String] and
      (JsPath \ "disable_web_page_preview").writeNullable[Boolean] and
      (JsPath \ "disable_notification").writeNullable[Boolean] and
      (JsPath \ "reply_to_message_id").writeNullable[Int] and
      (JsPath \ "reply_markup").writeNullable[InlineKeyboardMarkup]
    )(unlift(OutMessage.unapply))
}
