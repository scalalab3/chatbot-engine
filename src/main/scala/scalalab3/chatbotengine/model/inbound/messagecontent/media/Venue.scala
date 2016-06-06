package scalalab3.chatbotengine.model.inbound.messagecontent.media

case class Venue (
    location: Location,
    title: String,
    address: String,
    foursquareId: Option[String]) extends MediaContent