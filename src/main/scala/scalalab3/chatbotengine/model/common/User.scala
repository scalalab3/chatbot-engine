package scalalab3.chatbotengine.model.common

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class User (
    id: Int,
    firstName: String,
    lastName: Option[String],
    username: Option[String])

object User {
  implicit val userFormat = JsonNaming.snakecase(Json.format[User])
}