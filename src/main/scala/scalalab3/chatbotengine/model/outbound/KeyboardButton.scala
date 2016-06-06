package scalalab3.chatbotengine.model.outbound

case class KeyboardButton(
    text: String, 
    requestContact: Option[Boolean] = None, 
    requestLocation: Option[Boolean] = None)