package scalalab3.chatbotengine.core

import shapeless.HList
import shapeless.ops.function.FnToProduct
import shapeless.ops.hlist.Prepend

import scalalab3.chatbotengine.model.inbound.update.UpdateContent

trait Directive[X <: HList] { self =>
  import Directive._

  def happly(f: X => Route): Route

  def apply[F](f: F)(implicit fp: FnToProduct.Aux[F, X => Route]) = {
    happly(fp(f))
  }
  def &[Y <: HList](that: Directive[Y])(implicit prepend: Prepend[X, Y]): Directive[prepend.Out] = new Directive[prepend.Out] {
    override def happly(f: prepend.Out => Route): Route = {
      self.happly( x =>
        that.happly( y =>
          f(prepend(x, y))
        )
      )
    }
  }

  def hextract[L <: HList](f: UpdateContent => L): Directive[L] = new Directive[L] {
    def happly(inner: L => Route) = ctx => (f andThen inner)(ctx)(ctx)
  }
//  def extract[T](f: RequestContext ⇒ T): Directive1[T] =
//    hextract(ctx ⇒ f(ctx) :: HNil)

}
object Directive {
  type Route = UpdateContent => Unit
}