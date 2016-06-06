package scalalab3.chatbotengine.model.inbound.messagecontent.media

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json.Json

case class Location(longitude: Float, latitude: Float) extends MediaContent

object Location {
  implicit val locationFormat = JsonNaming.snakecase(Json.format[Location])
}