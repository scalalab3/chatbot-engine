package scalalab3.chatbotengine.model.outbound

case class ReplyKeyboardHide(
    hideKeyboard: Boolean = true, 
    selective: Option[Boolean] = None)