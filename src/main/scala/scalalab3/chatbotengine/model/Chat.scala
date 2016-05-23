package scalalab3.chatbotengine.model

case class Chat(
    id: Int, 
    `type`: String,
    title: Option[String] = None, 
    username: Option[String] = None, 
    firstName: Option[String] = None,
    lastName: Option[String] = None)
