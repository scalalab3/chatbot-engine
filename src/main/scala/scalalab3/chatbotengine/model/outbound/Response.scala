package scalalab3.chatbotengine.model.outbound

import play.api.libs.functional.syntax._
import play.api.libs.json._

sealed trait Response
case object NoReply extends Response

case class OutMessage (
                        chatId: String,
                        text: String,
                        replyToMessageId: Option[Int] = None,
                        replyMarkup: Option[InlineKeyboardMarkup] = None) extends Response {
  def withKeyboard(board: InlineKeyboardMarkup) = copy(replyMarkup = Some(board))
}

object OutMessage {
  implicit val outMessageFormat: OWrites[OutMessage] = (
    (JsPath \ "chat_id").write[String] and
      (JsPath \ "text").write[String] and
      (JsPath \ "reply_to_message_id").writeNullable[Int] and
      (JsPath \ "reply_markup").writeNullable[InlineKeyboardMarkup]
    )(unlift(OutMessage.unapply))
}
