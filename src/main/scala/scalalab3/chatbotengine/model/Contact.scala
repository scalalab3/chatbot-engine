package scalalab3.chatbotengine.model

case class Contact(
    phoneNumber: String, 
    firstName: String, 
    lastName: Option[String] = None, 
    userId: Option[Integer] = None)
