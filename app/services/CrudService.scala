package services

import scala.concurrent.Future

trait CrudService[A] {

  def saveEntity(entity: A): Future[Option[A]]

  def getEntities: Future[Seq[A]]

  def getEntity(id: String): Future[Option[A]]

  def deleteEntity(entity: A): Future[Boolean]

  def createTable:Future[Boolean]
}
