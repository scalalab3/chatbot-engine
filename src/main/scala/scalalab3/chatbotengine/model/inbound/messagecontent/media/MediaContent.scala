package scalalab3.chatbotengine.model.inbound.messagecontent.media

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.json._
import scalalab3.chatbotengine.model._

import scalalab3.chatbotengine.model.inbound.messagecontent.MessageContent

trait MediaContent extends MessageContent
//TODO: add FROM field

object MediaContent {
  implicit val f1 = JsonNaming.snakecase(Json.format[Audio])
  implicit val f2 = JsonNaming.snakecase(Json.format[Contact])
  implicit val f3 = JsonNaming.snakecase(Json.format[Document])
  implicit val f4 = JsonNaming.snakecase(Json.format[Location])
  implicit val f5 = JsonNaming.snakecase(Json.format[Photo])
  implicit val f6 = JsonNaming.snakecase(Json.format[Sticker])
  implicit val f7 = JsonNaming.snakecase(Json.format[Venue])
  implicit val f8 = JsonNaming.snakecase(Json.format[Video])
  implicit val f9 = JsonNaming.snakecase(Json.format[Voice])

  implicit val mediaContentReads: Reads[MediaContent] = {
    __.read[Audio].map(x => x: MediaContent) orElse
      __.read[Contact].map(x => x: MediaContent) orElse
      __.read[Document].map(x => x: MediaContent) orElse
      __.read[Location].map(x => x: MediaContent) orElse
      (__ \ "photo").readSeqOrEmpty[PhotoSize].map(x => Photo(x): MediaContent) orElse
      __.read[Sticker].map(x => x: MediaContent) orElse
      __.read[Venue].map(x => x: MediaContent) orElse
      __.read[Video].map(x => x: MediaContent) orElse
      __.read[Voice].map(x => x: MediaContent)
  }
}
