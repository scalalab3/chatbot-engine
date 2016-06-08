package scalalab3.chatbotengine.telegram

import org.scalatest.{FunSpec, Ignore, Matchers}
import shapeless.test.illTyped

class LongPoolingEngineSpec extends FunSpec with Matchers {
  describe("LongPoolingEngineSpec") {
    it("shouldn't compile with two similar bots") {
      illTyped ("""
      import scalalab3.chatbotengine.core.TelegramLongPoolingEngine
      import scalalab3.chatbotengine.examples.EchoChatBot
      import akka.actor.ActorSystem

      TelegramLongPoolingEngine()
      .registerChatBot(new EchoChatBot)
      .registerChatBot(new EchoChatBot)
      .start(ActorSystem("mySystem"), ???)
      """)
      true shouldBe true
    }
  }
}