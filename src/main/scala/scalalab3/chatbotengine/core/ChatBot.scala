package scalalab3.chatbotengine.core

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

import scalalab3.chatbotengine.model.common.Chat
import scalalab3.chatbotengine.model.inbound.update._

trait ChatBot extends Actor {
  private var rooms = Map.empty[Int, ActorRef]
  private def findOrCreate(chat: Chat): ActorRef = rooms.getOrElse(chat.id, {
    val newRoom = context.actorOf(roomProps(chat), name = s"chat-room-${chat.firstName.getOrElse(chat.id)}")
    rooms += chat.id -> newRoom
    newRoom
  })

  override def receive: Receive = {
    case Message(_, _, chat, _, content) =>
      findOrCreate(chat) forward content
//    case content: CallbackQuery => ???
  }

  def roomProps(chat: Chat): Props
}