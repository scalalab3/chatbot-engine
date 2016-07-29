package scalalab3.chatbotengine.telegram

import dispatch.Defaults._
import dispatch._
import play.api.libs.json.{Json, Reads, Writes}

import scala.concurrent.Future
import scalalab3.chatbotengine.core.BotCredential
import scalalab3.chatbotengine.model.common.User
import scalalab3.chatbotengine.model.inbound.update.{Message, Update}

trait TelegramClientBase {
  def sendMessage[A : Writes](message: A): Future[Message]
  def getUpdates(offset: Int): Future[Seq[Update]]
}

case class TelegramClient(credential: BotCredential) extends TelegramClientBase {
  val urlPrefix = s"https://api.telegram.org/bot${credential.token}"

  def sendMessage[A : Writes](message: A): Future[Message] = {
    val sendMessageUrl =
      url(s"$urlPrefix/sendMessage")
        .setContentType("application/json", "UTF-8")
        .setBody(Json.stringify(Json.toJson(message)))

    processMessage[Message](sendMessageUrl)
  }
  def getUpdates(offset: Int): Future[Seq[Update]] = {
    val getUpdatesURL = url(s"$urlPrefix/getUpdates").addQueryParameter("offset", offset.toString)
    processMessage[List[Update]](getUpdatesURL)
  }

  def getMe: Future[User] = {
    val getMe = url(s"$urlPrefix/getMe")

    processMessage[User](getMe)
  }

  private def processMessage[T: Reads] (request: Req): Future[T] =
    Http(request OK { response =>
      val body = response.getResponseBody
      (Json.parse(body) \ "result").as[T]
    })
}