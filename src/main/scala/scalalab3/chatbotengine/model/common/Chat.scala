package scalalab3.chatbotengine.model.common

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class Chat(
    id: Int,
    `type`: String,
    title: Option[String],
    username: Option[String],
    firstName: Option[String],
    lastName: Option[String])
//TODO: implement adt for “private”, “group”, “supergroup” or “channel”

object Chat {
  implicit val chatFormat = JsonNaming.snakecase(Json.format[Chat])
}