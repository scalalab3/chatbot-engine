package scalalab3.chatbotengine.model

case class User(
    id: Integer,
    firstName: String,
    lastName: Option[String] = None,
    username: Option[String] = None);