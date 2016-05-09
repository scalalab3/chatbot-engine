package scalalab3.chatbotengine.model

case class ReplyKeyboardHide(
    hideKeyboard: Boolean = true, 
    selective: Option[Boolean] = None)