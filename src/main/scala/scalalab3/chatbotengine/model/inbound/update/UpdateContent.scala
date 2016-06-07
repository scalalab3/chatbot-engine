package scalalab3.chatbotengine.model.inbound.update

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.functional.syntax._
import play.api.libs.json._

trait UpdateContent

object UpdateContent {
  implicit val updateContentReads = JsonNaming.snakecase{
    (__ \ "message").read[Message].map(x => x: UpdateContent) or
      (__ \ "inlineQuery").read[InlineQuery].map(x => x: UpdateContent) or
      (__ \ "chosenInlineResult").read[ChosenInlineResult].map(x => x: UpdateContent) or
      (__ \ "callbackQuery").read[CallbackQuery].map(x => x: UpdateContent)
  }
}