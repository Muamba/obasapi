package repositories.mail

import domain.mail.SmtpConfig
import org.scalatest.FunSuite
import repository.mail.SmtpConfigRepository

import scala.concurrent.Await
import scala.concurrent.duration._


class SmtpConfigRepositoryTest extends FunSuite{
  val entity = SmtpConfig("1",20,"test","hello","hest")
  val repository = SmtpConfigRepository
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.id), 2 minutes)
    assert(result.head.id==entity.id)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(port = 2018)
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.id), 2 minutes)
    assert(result.head.port==updatedEntity.port)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.id), 2 minutes)
    assert(result.isEmpty)

  }
}
