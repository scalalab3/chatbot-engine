package scalalab3.chatbotengine.telegram

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.pattern.pipe
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration.Duration

import scalalab3.chatbotengine.core.ChatBot
import scalalab3.chatbotengine.model.{OutMessage, Update}

object PoolingEngineActor {
  def props(bots: Traversable[ChatBot]): Props = Props(new PoolingEngineActor(bots))

  case class Tick(ref: ActorRef)
  case class BatchUpdate(dest: ActorRef, payload: Seq[Update])
}

class PoolingEngineActor(bots: Traversable[ChatBot]) extends Actor with ActorLogging {
  import PoolingEngineActor._
  import context.dispatcher

  val registry = bots.map { b =>
    val cfg = ConfigFactory.load().getConfig(b.getClass.getSimpleName)
    val username = cfg.getString("name")
    val password = cfg.getString("token")
    val client = new TelegramClient(username, password)

    val bot = context.actorOf(BotActor.props(b))
    (bot, client)
  }.toMap

  registry.keys.foreach { b =>
    context.system.scheduler.schedule(
      Duration.create(1, TimeUnit.SECONDS), Duration.create(1, TimeUnit.SECONDS),
      self, Tick(b))
  }
  val offsets = scala.collection.mutable.HashMap.empty[ActorRef, Int]

  override def receive: Receive = {
    case Tick(b) =>
      val client = registry(b)
      client.getUpdates(offsets.get(b)) map (BatchUpdate(b, _)) pipeTo self

    case BatchUpdate(bot, data) =>
      val newOffset = data.map(_.updateId + 1).reduceOption(_ min _)
      newOffset.fold() { o =>
        offsets += (bot -> o)
      }
      data.foreach {
        bot ! _
      }

    case msg: OutMessage =>
      val client = registry(sender)
      client.sendMessage(msg)
  }
}
