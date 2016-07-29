package scalalab3.chatbotengine.telegram

import org.scalatest.{FunSpec, Matchers}

import scala.concurrent.duration._
import scala.concurrent.Await
import com.typesafe.config.ConfigFactory

import scalalab3.chatbotengine.core.BotCredential

class TelegramClientSpec extends FunSpec with Matchers {
  describe("Telegram client") {
    val cfg = ConfigFactory.load()
    val auth = cfg.getString(s"bots.EchoChatBot")
    val token = cfg.getString(s"tokens.$auth")

    it("should successfully exec getMe") {
      val client = TelegramClient(BotCredential(auth, token))
      val user = Await.result(client.getMe, 180.seconds)
      user.username shouldBe Some(auth)
    }

    ignore("should get updates") {
      val client = TelegramClient(BotCredential(auth, token))
      val updates = Await.result(client.getUpdates(0), 180.seconds)
      updates.length should be > 0
    }
  }
}
