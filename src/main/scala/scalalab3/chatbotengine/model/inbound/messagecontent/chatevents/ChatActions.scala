package scalalab3.chatbotengine.model.inbound.messagecontent.chatevents

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.functional.syntax._
import play.api.libs.json._

import scalalab3.chatbotengine.model.common.User
import scalalab3.chatbotengine.model.inbound.messagecontent.MessageContent
import scalalab3.chatbotengine.model.inbound.messagecontent.media._
import scalalab3.chatbotengine.model.inbound.update.Message

sealed trait ChatActions extends MessageContent

case class NewChatMember(newChatMember: User) extends ChatActions
case class LeftChatMember(leftChatMember: User) extends ChatActions
case class NewChatTitle(newChatTitle: String) extends ChatActions
case class NewChatPhoto(newChatPhoto: Photo) extends ChatActions
case class DeleteChatPhoto(deleteChatPhoto: Boolean) extends ChatActions
case class GroupChatCreated(groupChatCreated: Boolean) extends ChatActions
case class SupergroupChatCreated(SupergroupChatCreated: Boolean) extends ChatActions
case class ChannelChatCreated(channelChatCreated: Boolean) extends ChatActions
case class MigrateToChatId(migrateToChatId: Int) extends ChatActions
case class MigrateFromChatId(migrateFromChatId: Int) extends ChatActions
case class PinnedMessage(pinnedMessage: Message) extends ChatActions

object PinnedMessage {
  implicit lazy val pinnedMessageReads: Reads[PinnedMessage] = (__ \ "pinnedMessage").lazyRead(Message.messageRead).map(PinnedMessage.apply)
}

object ChatActions {
  implicit val f1 = JsonNaming.snakecase(Json.format[NewChatMember])
  implicit val f2 = JsonNaming.snakecase(Json.format[LeftChatMember])
  implicit val f3 = JsonNaming.snakecase(Json.format[NewChatTitle])
  implicit val f4 = JsonNaming.snakecase(Json.format[NewChatPhoto])
  implicit val f5 = JsonNaming.snakecase(Json.format[DeleteChatPhoto])
  implicit val f6 = JsonNaming.snakecase(Json.format[GroupChatCreated])
  implicit val f7 = JsonNaming.snakecase(Json.format[SupergroupChatCreated])
  implicit val f8 = JsonNaming.snakecase(Json.format[ChannelChatCreated])
  implicit val f9 = JsonNaming.snakecase(Json.format[MigrateToChatId])
  implicit val f10 = JsonNaming.snakecase(Json.format[MigrateFromChatId])

  implicit val chatActionsReads: Reads[ChatActions] = {
    __.read[NewChatMember].map(x => x: ChatActions) or
      __.read[LeftChatMember].map(x => x: ChatActions) or
      __.read[NewChatTitle].map(x => x: ChatActions) or
      __.read[NewChatPhoto].map(x => x: ChatActions) or
      __.read[DeleteChatPhoto].map(x => x: ChatActions) or
      __.read[GroupChatCreated].map(x => x: ChatActions) or
      __.read[SupergroupChatCreated].map(x => x: ChatActions) or
      __.read[ChannelChatCreated].map(x => x: ChatActions) or
      __.read[MigrateToChatId].map(x => x: ChatActions) or
      __.read[MigrateFromChatId].map(x => x: ChatActions) or
      __.read[PinnedMessage].map(x => x: ChatActions)
  }
}
