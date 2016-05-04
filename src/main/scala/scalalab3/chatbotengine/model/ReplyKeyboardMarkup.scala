package scalalab3.chatbotengine.model

case class ReplyKeyboardMarkup(val keyboard: Array[Array[KeyboardButton]], val resizeKeyboard: Boolean,
                               val oneTimeKeyboard: Boolean, val selective: Boolean)