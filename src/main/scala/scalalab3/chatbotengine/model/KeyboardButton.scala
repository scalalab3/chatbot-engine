package scalalab3.chatbotengine.model

case class KeyboardButton(
    text: String, 
    requestContact: Option[Boolean] = None, 
    requestLocation: Option[Boolean] = None)