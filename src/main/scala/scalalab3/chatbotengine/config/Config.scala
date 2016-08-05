package scalalab3.chatbotengine.config

import com.typesafe.config.{Config, ConfigFactory}

import scala.collection.JavaConverters._

case class BotCredential(auth: String, token: String)
case class AppConfig(botCredentials: Map[String, BotCredential])

object AppConfig {
  def load() = {
    val cfg = ConfigFactory.load()
    val auths = configToMap(cfg.getConfig("bots"))
    val tokens = configToMap(cfg.getConfig("tokens"))

    AppConfig(
      for {
        (className, botName) <- auths
        token <- tokens.get(botName)
      } yield className -> BotCredential(botName, token)
    )
  }

  private def configToMap(cfg: Config) =
    cfg.entrySet().asScala.map(entry => entry.getKey -> entry.getValue.unwrapped.toString).toMap
}
