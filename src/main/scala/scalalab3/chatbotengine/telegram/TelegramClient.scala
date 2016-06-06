package scalalab3.chatbotengine.telegram

import dispatch.Defaults._
import dispatch._
import play.api.libs.json.{Json, Reads}

import scala.concurrent.Future
import scalalab3.chatbotengine.model.common.User
import scalalab3.chatbotengine.model.inbound.update.Update
import scalalab3.chatbotengine.model.outbound.OutMessage

class TelegramClient(userName: String, pass: String) {
  val urlPrefix = s"https://api.telegram.org/bot$pass"

  def getMe: Future[User] = {
    val getMe = url(s"$urlPrefix/getMe")

    processMessage[User](getMe)
  }

  def sendMessage(message: OutMessage): Future[OutMessage] = {
    val sendMessageUrl =
      url(s"$urlPrefix/sendMessage")
        .setContentType("application/json", "UTF-8")
        .setBody(Json.toJson(message).as[String])

    processMessage[OutMessage](sendMessageUrl)
  }

  def getUpdates(offset: Option[Int] = None): Future[Seq[Update]] = {
    val getUpdatesURL =
      url(s"$urlPrefix/getUpdates")
        .addQueryParameter("offset", offset.fold("")(_.toString))
    Http(getUpdatesURL OK { response =>
      val body = response.getResponseBody
      Json.parse(body)
    }).onComplete(println)

    processMessage[Seq[Update]](getUpdatesURL)
  }

  private def processMessage[T: Reads] (request: Req): Future[T] =
    Http(request OK { response =>
      val body = response.getResponseBody
      (Json.parse(body) \ "result").as[T]
    })
}