package scalalab3.chatbotengine.model.inbound.messagecontent.media

import play.api.libs.functional.syntax._
import play.api.libs.json._

import scalalab3.chatbotengine.model.inbound.messagecontent.MessageContent

trait MediaContent extends MessageContent
//TODO: add FROM field

object MediaContent {
  implicit val mediaContentReads: Reads[MediaContent] = {
    __.read[Audio].map(x => x: MediaContent) or
      __.read[Contact].map(x => x: MediaContent) or
      __.read[Document].map(x => x: MediaContent) or
      __.read[Location].map(x => x: MediaContent) or
      __.read[Photo].map(x => x: MediaContent) or
      __.read[Sticker].map(x => x: MediaContent) or
      __.read[Venue].map(x => x: MediaContent) or
      __.read[Video].map(x => x: MediaContent) or
      __.read[Voice].map(x => x: MediaContent)
  }
}
