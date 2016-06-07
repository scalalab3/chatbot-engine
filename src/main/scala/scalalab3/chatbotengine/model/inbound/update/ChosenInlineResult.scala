package scalalab3.chatbotengine.model.inbound.update

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

import scalalab3.chatbotengine.model.common.User
import scalalab3.chatbotengine.model.inbound.messagecontent.media.Location

case class ChosenInlineResult (
                                resultId: String,
                                from: User,
                                query: String,
                                location: Option[Location],
                                inlineMessageId: Option[String]) extends UpdateContent

object ChosenInlineResult {
  implicit val chosenInlineResultFormat = JsonNaming.snakecase(Json.format[ChosenInlineResult])
}