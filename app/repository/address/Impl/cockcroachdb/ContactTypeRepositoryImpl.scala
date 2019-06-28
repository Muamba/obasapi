package repository.address.Impl.cockcroachdb

import domain.address.ContactType
import repository.address.ContactTypeRepository
import repository.address.Impl.cockcroachdb.tables.ContactTypeTable

import scala.concurrent.Future

class ContactTypeRepositoryImpl extends ContactTypeRepository{
  override def saveEntity(entity: ContactType): Future[Boolean] = {
    Future.successful(ContactTypeTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[ContactType]] = {
    ContactTypeTable.getEntities
  }

  override def getEntity(ContactType: String): Future[Option[ContactType]] = {
    ContactTypeTable.getEntity(ContactType)
  }

  override def deleteEntity(entity: ContactType): Future[Boolean] = {
    Future.successful(ContactTypeTable.deleteEntity(entity.ContactType).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(ContactTypeTable.createTable)
  }
}
