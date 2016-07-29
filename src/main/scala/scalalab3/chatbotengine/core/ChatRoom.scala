package scalalab3.chatbotengine.core

import akka.actor.{Actor, ActorLogging, ActorRef}
import play.api.libs.json.{JsValue, Json, Writes}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}
import scalalab3.chatbotengine.core.BotServiceActor.{Messages, NoResponse, UpdateProcessed}
import scalalab3.chatbotengine.model.common.Chat
import scalalab3.chatbotengine.model.outbound._

abstract class ChatRoom(chat: Chat) extends Actor with ActorLogging {
  def textReply(text: String) = OutMessage(chat.id.toString, text)
  def noReply = NoReply
  def complete[T](value: T)(implicit magnet: CompletionMagnet[T]): Unit = magnet.marshall(value, sender)
}