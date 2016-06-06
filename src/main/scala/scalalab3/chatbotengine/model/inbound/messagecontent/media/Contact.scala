package scalalab3.chatbotengine.model.inbound.messagecontent.media

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class Contact (
    phoneNumber: String, 
    firstName: String, 
    lastName: Option[String],
    userId: Option[Int]) extends MediaContent

object Contact {
  implicit val contactFormat = JsonNaming.snakecase(Json.format[Contact])
}