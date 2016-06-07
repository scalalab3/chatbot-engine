package scalalab3.chatbotengine.model.inbound.messagecontent.text

import julienrf.json.derived
import play.api.libs.json._

import scalalab3.chatbotengine.model.StringUtil

sealed trait TextEntity
case class Mention(offset: Int, length: Int) extends TextEntity
case class HashTag(offset: Int, length: Int) extends TextEntity
case class BotCommand(offset: Int, length: Int) extends TextEntity
//TODO: add the rest of possible types

object TextEntity {
  implicit val textEntityRead: Reads[TextEntity] = derived.flat.reads((__ \ "type").read[String].map(StringUtil.camelcase))
  //TODO: custom TypeTagReads implementation
}
