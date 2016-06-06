package scalalab3.chatbotengine.model.inbound.update

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.functional.syntax._
import play.api.libs.json._

import scalalab3.chatbotengine.model.common.{Chat, User}
import scalalab3.chatbotengine.model.inbound.messagecontent.MessageContent
import scalalab3.chatbotengine.model.inbound.messagecontent.media.Location

sealed trait UpdateContent

case class Message (
                    messageId: Long,
                    date: Int,
                    chat: Chat,
//                    from: Option[User],
//                    forwardFrom: Option[User],
//                    forwardFromChat: Option[Chat],
//                    forwardDate: Option[Int],
                    replyToMessage: Option[Message],
                    content: MessageContent
                   ) extends UpdateContent

object Message {
  implicit val messageRead: Reads[Message] = JsonNaming.snakecase((
    (__ \ "messageId").read[Long] and
      (__ \ "date").read[Int] and
      (__ \ "chat").read[Chat] and
      (__ \ "replyToMessage").lazyReadNullable(messageRead) and
      __.read[MessageContent]
    ) (Message.apply _))
}

case class InlineQuery (
                        id: String,
                        from: User,
                        query: String,
                        offset: String,
                        location: Option[Location]) extends UpdateContent

case class ChosenInlineResult (
                               resultId: String,
                               from: User,
                               query: String,
                               location: Option[Location],
                               inlineMessageId: Option[String]) extends UpdateContent

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

object UpdateContent {
  implicit val m2 = JsonNaming.snakecase(Json.format[InlineQuery])
  implicit val m3 = JsonNaming.snakecase(Json.format[ChosenInlineResult])

  implicit val updateContentReads = JsonNaming.snakecase{
    (__ \ "message").read[Message].map(x => x: UpdateContent) or
      (__ \ "inlineQuery").read[InlineQuery].map(x => x: UpdateContent) or
      (__ \ "chosenInlineResult").read[ChosenInlineResult].map(x => x: UpdateContent) or
      (__ \ "callbackQuery").read[CallbackQuery].map(x => x: UpdateContent)
  }
}