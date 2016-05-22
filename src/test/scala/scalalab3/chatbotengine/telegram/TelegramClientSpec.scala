package scalalab3.chatbotengine.telegram

import org.scalatest.{Matchers, FunSpec}
import scala.concurrent.duration._
import scala.concurrent.Await
import com.typesafe.config.ConfigFactory

class TelegramClientSpec extends FunSpec with Matchers {
  describe("Telegram client") {
    val cfg = ConfigFactory.load().getConfig("EchoChatBot")
    val username = cfg.getString("name")
    val password = cfg.getString("token")

    it("should successfully exec getMe") {
      val client = new TelegramClient(username, password)
      val user = Await.result(client.getMe, 180.seconds)
      user.username shouldBe Some(username)
    }

    it("should get updates") {
      val client = new TelegramClient(username, password)
      val updates = Await.result(client.getUpdates(), 180.seconds)
      updates.length should be > 0
    }
  }
}
