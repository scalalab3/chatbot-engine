package scalalab3.chatbotengine.core

import akka.actor.ActorRef

import scala.concurrent._
import scala.util._
import scalalab3.chatbotengine.core.BotServiceActor.UpdateProcessed
import scalalab3.chatbotengine.model.outbound.{NoReply, Response}


trait CompletionMagnet[-T] {
  def marshall(value: T, responder: ActorRef): Unit
}

object CompletionMagnet {
  implicit val fromReply: CompletionMagnet[Response] = new CompletionMagnet[Response] {
    override def marshall(value: Response, responder: ActorRef): Unit = { responder ! UpdateProcessed(value) }
  }

  implicit def fromOption[T](implicit magnet: CompletionMagnet[T]): CompletionMagnet[Option[T]] = new CompletionMagnet[Option[T]] {
    override def marshall(value: Option[T], responder: ActorRef): Unit = value match {
      case Some(v) => magnet.marshall(v, responder)
      case None => responder ! UpdateProcessed(NoReply)
    }
  }

  implicit def fromFuture[T](implicit magnet: CompletionMagnet[T], ec: ExecutionContext): CompletionMagnet[Future[T]] =
    new CompletionMagnet[Future[T]] {
      override def marshall(value: Future[T], responder: ActorRef): Unit = value.onComplete {
        case Success(v) => magnet.marshall(v, responder)
        case Failure(_) => responder ! UpdateProcessed(NoReply)
      }
    }
  /*
    implicit def eitherMarshaller[A, B](implicit ma: ToResponseMarshaller[A], mb: ToResponseMarshaller[B]) =
      ToResponseMarshaller[Either[A, B]] { (value, ctx) ⇒
        value match {
          case Left(a)  ⇒ ma(a, ctx)
          case Right(b) ⇒ mb(b, ctx)
        }
      }

    implicit def tryMarshaller[T](implicit m: ToResponseMarshaller[T]) =
      ToResponseMarshaller[Try[T]] { (value, ctx) ⇒
        value match {
          case Success(v) ⇒ m(v, ctx)
          case Failure(t) ⇒ ctx.handleError(t)
        }
      }
   */
}