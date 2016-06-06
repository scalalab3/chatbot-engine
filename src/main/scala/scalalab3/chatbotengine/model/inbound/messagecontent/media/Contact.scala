package scalalab3.chatbotengine.model.inbound.messagecontent.media

case class Contact (
    phoneNumber: String, 
    firstName: String, 
    lastName: Option[String],
    userId: Option[Int]) extends MediaContent