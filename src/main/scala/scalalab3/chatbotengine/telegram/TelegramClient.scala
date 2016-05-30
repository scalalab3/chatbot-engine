package scalalab3.chatbotengine.telegram

import dispatch.Defaults._
import dispatch._
import org.json4s.{DefaultFormats, _}
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.write

import scala.concurrent.Future
import scala.reflect.ClassTag
import scalalab3.chatbotengine.model._

class TelegramClient(userName: String, pass: String) {
  val urlPrefix = s"https://api.telegram.org/bot$pass"

  implicit val formats = DefaultFormats

  def getMe: Future[User] = {
    val getMe = url(s"$urlPrefix/getMe")

    processMessage[User](getMe)
  }

  def sendMessage(message: OutMessage): Future[OutMessage] = {
    val sendMessageUrl =
      url(s"$urlPrefix/sendMessage")
        .setContentType("application/json", "UTF-8")
        .setBody(write(Extraction.decompose(message).snakizeKeys))

    processMessage[OutMessage](sendMessageUrl)
  }

  def getUpdates(offset: Option[Int] = None): Future[Seq[Update]] = {
    val getUpdatesURL =
      url(s"$urlPrefix/getUpdates")
        .addQueryParameter("offset", offset.fold("")(_.toString))

    processMessage[Seq[Update]](getUpdatesURL)
  }

  private def processMessage[T: Manifest] (request: Req): Future[T] =
    Http(request OK { response =>
      val body = response.getResponseBody
      val parsed = parse(body)
      (parsed \\ "result").camelizeKeys.extract[T]
    })
}