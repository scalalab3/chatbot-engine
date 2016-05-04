package scalalab3.chatbotengine.model

case class Update(val updateId: Integer, val message: Message, val inlineQuery: InlineQuery,
                  val chosenInlineResult: ChosenInlineResult, val callbackQuery: CallbackQuery)