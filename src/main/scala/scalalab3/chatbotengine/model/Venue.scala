package scalalab3.chatbotengine.model

case class Venue(
    location: Location,
    title: String,
    address: String,
    foursquareId: Option[String] = None)
