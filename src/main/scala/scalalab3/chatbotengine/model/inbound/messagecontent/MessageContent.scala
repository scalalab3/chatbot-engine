package scalalab3.chatbotengine.model.inbound.messagecontent

import play.api.libs.functional.syntax._
import play.api.libs.json._

import scalalab3.chatbotengine.model.inbound.messagecontent.chatevents.ChatActions
import scalalab3.chatbotengine.model.inbound.messagecontent.media.Media
import scalalab3.chatbotengine.model.inbound.messagecontent.text.Text

trait MessageContent

object MessageContent {
  implicit val messageContentReads = {
    __.read[Text].map(x => x: MessageContent) or
      __.read[Media].map(x => x: MessageContent) or
      __.read[ChatActions].map(x => x: MessageContent)
  }
}