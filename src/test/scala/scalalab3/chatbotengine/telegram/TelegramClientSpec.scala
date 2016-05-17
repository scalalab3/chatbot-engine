package scalalab3.chatbotengine.telegram

import org.scalatest.{Matchers, FunSpec}
import scala.concurrent.duration._
import scala.concurrent.Await

class TelegramClientSpec extends FunSpec with Matchers {
  describe("Telegram client") {
    val username = "scalalab3_dev1_bot"
    val password = "<please take from storage>"

    it("should successfully exec getMe") {
      val client = new TelegramClient(username, password)
      val user = Await.result(client.getMe, 180.seconds)
      user.username shouldBe Some(username)
    }

    it("should get updates") {
      val client = new TelegramClient(username, password)
      val updates = Await.result(client.getUpdates, 180.seconds)

      updates.length should be > 0
    }
  }
}
