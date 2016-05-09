package scalalab3.chatbotengine.model

case class ReplyKeyboardMarkup(
    keyboard: Array[Array[KeyboardButton]], 
    resizeKeyboard: Option[Boolean] = None,
    oneTimeKeyboard: Option[Boolean] = None,
    selective: Option[Boolean] = None)