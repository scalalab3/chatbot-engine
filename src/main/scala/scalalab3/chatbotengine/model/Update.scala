package scalalab3.chatbotengine.model

case class Update(
    updateId: Int,
    message: Option[Message] = None,
    inlineQuery: Option[InlineQuery] = None,
    chosenInlineResult: Option[ChosenInlineResult] = None,
    callbackQuery: Option[CallbackQuery] = None)