package scalalab3.chatbotengine.model.inbound.update

import com.github.tototoshi.play.json.JsonNaming
import play.api.libs.functional.syntax._
import play.api.libs.json._

case class Update(updateId: Int, updateContent: UpdateContent)

object Update {
  val read0: Reads[Update] = (
    (__ \ "updateId").read[Int] and
      __.read[UpdateContent]
    ) (Update.apply _)
  implicit val updateRead: Reads[Update] = JsonNaming.snakecase(read0)
}