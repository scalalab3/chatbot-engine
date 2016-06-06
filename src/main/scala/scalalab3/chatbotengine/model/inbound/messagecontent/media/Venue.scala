package scalalab3.chatbotengine.model.inbound.messagecontent.media

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class Venue (
    location: Location,
    title: String,
    address: String,
    foursquareId: Option[String]) extends MediaContent

object Venue {
  implicit val venueFormat = JsonNaming.snakecase(Json.format[Venue])
}