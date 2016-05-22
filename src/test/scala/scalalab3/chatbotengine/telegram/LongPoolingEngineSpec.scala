package scalalab3.chatbotengine.telegram

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.io.Source
import scalalab3.chatbotengine.examples.EchoChatBot
import scalalab3.chatbotengine.model.{OutMessage, Update}

class LongPoolingEngineSpec extends TestKit(ActorSystem("MySpec")) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {
  implicit val formats = DefaultFormats

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "An EchoBot" must {
    "send back messages" in {
      val echoBot = system.actorOf(BotActor.props(new EchoChatBot))

      val raw = Source.fromURL(getClass.getResource("/updates.json")).getLines().mkString
      val testMsg = (parse(raw) \\ "result").camelizeKeys.extract[Seq[Update]]

      testMsg.foreach { msg =>
        echoBot ! msg
        expectMsgAllClassOf(classOf[OutMessage])
      }
    }
  }
}