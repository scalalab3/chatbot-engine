package scalalab3.chatbotengine.model.inbound.messagecontent.media

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.functional.syntax._
import play.api.libs.json._

import scalalab3.chatbotengine.model.common.User
import scalalab3.chatbotengine.model.inbound.messagecontent.MessageContent

case class Media(from: User, updateContent: MediaContent) extends MessageContent

object Media {
  implicit val mediaRead: Reads[Media] = JsonNaming.snakecase((
    (__ \ "from").read[User] and
      __.read[MediaContent]
    ) (Media.apply _))
}

trait MediaContent

object MediaContent {
  implicit val mediaContentReads: Reads[MediaContent] = {
    (__ \ "audio").read[Audio].map(x => x: MediaContent) or
      (__ \ "contact").read[Contact].map(x => x: MediaContent) or
      (__ \ "document").read[Document].map(x => x: MediaContent) or
      (__ \ "location").read[Location].map(x => x: MediaContent) or
      (__ \ "photo").read[Photo].map(x => x: MediaContent) or
      (__ \ "sticker").read[Sticker].map(x => x: MediaContent) or
      (__ \ "venue").read[Venue].map(x => x: MediaContent) or
      (__ \ "video").read[Video].map(x => x: MediaContent) or
      (__ \ "voice").read[Voice].map(x => x: MediaContent)
  }
}
