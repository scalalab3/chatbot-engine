package scalalab3.chatbotengine.model.inbound.update

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.functional.syntax._
import play.api.libs.json._

import scalalab3.chatbotengine.model.common.Chat
import scalalab3.chatbotengine.model.inbound.messagecontent.MessageContent

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