package scalalab3.chatbotengine.model.inbound.update

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.functional.syntax._
import play.api.libs.json._

import scalalab3.chatbotengine.model.common.User

case class CallbackQuery (
                           id: String,
                           from: User,
                           data: String,
                           message: Option[Message],
                           inlineMessageId: Option[String]) extends UpdateContent

object CallbackQuery {
  implicit lazy val callbackQueryReads: Reads[CallbackQuery] = JsonNaming.snakecase((
    (__ \ "id").read[String] and
      (__ \ "from").read[User] and
      (__ \ "data").read[String] and
      (__ \ "message").lazyReadNullable(Message.messageRead) and
      (__ \ "inlineMessageId").readNullable[String]
    ) (CallbackQuery.apply _))
}