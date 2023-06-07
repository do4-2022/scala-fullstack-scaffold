package todo

import zio.Task
import zio._

import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._
import java.util.concurrent.FutureTask

object TodoService {
  private val todosCollection: MongoCollection[Todo] = {
    val client = MongoDBClient.createClient(
      MongoDBConfig("mongodb://root:root@localhost:27017", "todoapp")
    )
    val database = client.getDatabase("todoapp")

    database.getCollection[Todo]("todos")
  }

  def getTodos(): Task[List[Todo]] = ???
//     ZIO.fromFuture(_ => todosCollection.find().toFuture())

  def getTodoById(id: Int): Task[Option[Todo]] =
    ZIO.fromFuture(_ => todosCollection.find(equal("id", id)).headOption())

  def createTodo(): Task[Todo] =
    ???

  def updateTodo(todoId: Int, todo: Todo): Task[Todo] =
    ???

  def deleteTodoById(id: Int): Task[Unit] =
    ???
}