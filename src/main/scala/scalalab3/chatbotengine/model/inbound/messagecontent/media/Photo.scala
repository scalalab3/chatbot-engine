package scalalab3.chatbotengine.model.inbound.messagecontent.media

import play.api.libs.json._

case class Photo(photoSizes: List[PhotoSize]) extends MediaContent

object Photo {
    implicit lazy val photoReads: Reads[Photo] = __.read[List[PhotoSize]].map(Photo.apply)
}
