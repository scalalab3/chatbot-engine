package scalalab3.chatbotengine.model.inbound.update

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

import scalalab3.chatbotengine.model.common.User
import scalalab3.chatbotengine.model.inbound.messagecontent.media.Location

case class InlineQuery (
                         id: String,
                         from: User,
                         query: String,
                         offset: String,
                         location: Option[Location]) extends UpdateContent

object InlineQuery {
  implicit val inlineQueryFormat = JsonNaming.snakecase(Json.format[InlineQuery])
}