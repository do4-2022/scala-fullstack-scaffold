package livechart.api.todos

import org.scalajs.dom._
import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.Thenable.Implicits._
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

import livechart.state.TodoItem
import livechart.api.API_URL

def createTodo(item: TodoItem): Future[TodoItem] = {

  val headers = new Headers()
  headers.append("Content-Type", "application/json")
  val options = js.Dynamic.literal(
    method = "POST",
    headers = headers,
    body = item.asJson.noSpaces
  )
  val requestInit =
    options.asInstanceOf[RequestInit]

  fetch(s"${API_URL}/api/todos", requestInit)
    .flatMap(_.text().toFuture)
    .flatMap(json => {
      decode[TodoItem](json) match {
        case Right(value: TodoItem) => Future.successful(value)
        case Left(error)            => Future.failed(error)
      }
    })
}
