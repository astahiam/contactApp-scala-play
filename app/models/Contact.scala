package models

import anorm._
import play.api.db._
import play.api.Play.current
import play.api.data._
import play.api.data.Forms._

case class Contact(id: Long, name: String, emailAddress: String, phoneNumber: String, homeAddress: String)

object Contact {

  def all = {
      DB.withConnection {implicit connection =>
        SQL("SELECT * FROM contact")().map { row =>
          Contact(
            id = row[Long]("id"),
            name = row[String]("name"),
            emailAddress = row[String]("emailAddress"),
            phoneNumber = row[String]("phoneNumber"),
            homeAddress = row[String]("homeAddress")
          )
        }.toList
      }
  }

  def create(contact: Contact){
    DB.withConnection { implicit connection =>
      SQL("INSERT INTO contact(name, emailAddress, phoneNumber, homeAddress) VALUES ({name}, {emailAddress}, {phoneNumber}, {homeAddress})").on(
        'name -> contact.name,
        'emailAddress -> contact.emailAddress,
        'phoneNumber -> contact.phoneNumber,
        'homeAddress -> contact.homeAddress
      ).executeUpdate()
    }
  }

  def get(id: Long): Option[Contact] = {
  		DB.withConnection { implicit connection =>
  			SQL("""
  				select * from contact where id = {id}
  			""").on(
  				'id -> id
  			)().headOption.map { row =>
          Contact(
            id = row[Long]("id"),
            name = row[String]("name"),
            emailAddress = row[String]("emailAddress"),
            phoneNumber = row[String]("phoneNumber"),
            homeAddress = row[String]("homeAddress")
          )
        }

  		}
  	}

    def update(id: Long, contact: Contact): Unit = DB.withConnection { implicit c =>
    		SQL("""
    			update contact set
    				name = {name},
            emailAddress = {emailAddress},
            phoneNumber = {phoneNumber},
    				homeAddress = {homeAddress}
    			where
    				id = {id}
    		""").on(
    			'id				-> id,
    			'name			-> contact.name,
          'emailAddress			-> contact.emailAddress,
          'phoneNumber			-> contact.phoneNumber,
    			'homeAddress	-> contact.homeAddress
    		).executeUpdate()
    	}

    def delete(id: Long) = DB.withConnection { implicit c =>
    		SQL(
    			"delete from contact where id = {id}"
    		).on('id -> id).executeUpdate()
    	}

  val form = Form(
    mapping(
      "id" -> ignored(0L),
      "name" -> nonEmptyText,
      "emailAddress" -> email,
      "phoneNumber" -> text,
      "homeAddress" -> text
    )(Contact.apply)(Contact.unapply)
  )

}
