package scalalab3.chatbotengine.telegram

import dispatch.Defaults._
import dispatch._
import org.json4s.{DefaultFormats, _}
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.write

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

  def sendMessage(message: OutMessage): Future[OutMessage] = {
    val sendMessageUrl =
      url(s"$urlPrefix/sendMessage")
        .setContentType("application/json", "UTF-8")
        .setBody(write(Extraction.decompose(message).snakizeKeys))

    Http(sendMessageUrl OK { request =>
      val body = request.getResponseBody
      val parsed = parse(body)
      (parsed \\ "result").camelizeKeys.extract[OutMessage]
    })
  }

  def getUpdates(offset: Option[Int] = None): Future[Seq[Update]] = {
    val getUpdatesURL = url(s"$urlPrefix/getUpdates").addQueryParameter("offset", offset.fold("")(_.toString))

    Http(getUpdatesURL OK { request =>
      val body = request.getResponseBody
      val parsed = parse(body)
      (parsed \\ "result").camelizeKeys.extract[Seq[Update]]
    })
  }
}