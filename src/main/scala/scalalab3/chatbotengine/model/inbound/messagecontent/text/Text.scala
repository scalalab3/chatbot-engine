package scalalab3.chatbotengine.model.inbound.messagecontent.text

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.functional.syntax._
import play.api.libs.json._

import scalalab3.chatbotengine.model._
import scalalab3.chatbotengine.model.common.User
import scalalab3.chatbotengine.model.inbound.messagecontent.MessageContent

case class Text(from: User, editDate: Option[Long], text: String, entities: Seq[TextEntity]) extends MessageContent

object Text {
  implicit val textRead: Reads[Text] = JsonNaming.snakecase((
    (__ \ "from").read[User] and
      (__ \ "editDate").readNullable[Long] and
      (__ \ "text").read[String] and
      (__ \ "entities").readSeqOrEmpty[TextEntity]
    ) (Text.apply _))
}
