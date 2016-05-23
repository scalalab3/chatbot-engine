package scalalab3.chatbotengine.telegram

import dispatch.Defaults._
import dispatch._
import org.json4s.{DefaultFormats, _}
import org.json4s.jackson.JsonMethods._

import scala.concurrent.Future
import scalalab3.chatbotengine.model._

class TelegramClient(userName: String, pass: String) {
  val urlPrefix = s"https://api.telegram.org/bot$pass"

  implicit val formats = DefaultFormats

  def getMe: Future[User] = {
    val getMe = url(s"$urlPrefix/getMe")

    Http(getMe OK { request =>
      val body = request.getResponseBody
      val parsed = parse(body)
      (parsed \\ "result").camelizeKeys.extract[User]
    })
  }

  def sendMessage(message: Message): Future[Message] = {
    // FIXME implement me
    ???
  }

  def getUpdates: Future[Seq[Update]] = {
    val getUpdatesURL = url(s"$urlPrefix/getUpdates")

    Http(getUpdatesURL OK { request =>
      val body = request.getResponseBody
      val parsed = parse(body)
      (parsed \\ "result").camelizeKeys.extract[Seq[Update]]
    })
  }
}