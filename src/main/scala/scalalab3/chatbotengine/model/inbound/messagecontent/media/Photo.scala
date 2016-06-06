package scalalab3.chatbotengine.model.inbound.messagecontent.media

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.functional.syntax._
import play.api.libs.json._

import scalalab3.chatbotengine.model._

case class Photo(photoSizes: Seq[PhotoSize], caption: Option[String] = None) extends MediaContent

object Photo {
  implicit lazy val photoReads: Reads[Photo] = JsonNaming.snakecase((
    (__ \ "photo").readSeqOrEmpty[PhotoSize] and
      (__ \ "caption").readNullable[String]
    ) (Photo.apply _))
}
