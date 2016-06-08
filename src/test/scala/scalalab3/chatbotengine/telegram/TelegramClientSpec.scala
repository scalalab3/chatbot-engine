package scalalab3.chatbotengine.telegram

import org.scalatest.{Matchers, FunSpec}
import scala.concurrent.duration._
import scala.concurrent.Await
import com.typesafe.config.ConfigFactory

class TelegramClientSpec extends FunSpec with Matchers {
  describe("Telegram client") {
    val cfg = ConfigFactory.load()
    val auth = cfg.getString(s"bots.EchoChatBot")
    val token = cfg.getString(s"tokens.$auth")

    it("should successfully exec getMe") {
      val client = new TelegramClient(auth, token)
      val user = Await.result(client.getMe, 180.seconds)
      user.username shouldBe Some(auth)
    }

    ignore("should get updates") {
      val client = new TelegramClient(auth, token)
      val updates = Await.result(client.getUpdates(), 180.seconds)
      updates.length should be > 0
    }
  }
}
