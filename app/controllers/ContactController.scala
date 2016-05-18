package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import models.Contact

@Singleton
class ContactController @Inject() extends Controller {

  def index = Action {
      val contacts = Contact.all

      Ok(views.html.index(contacts,Contact.form))
  }

  def create = Action {implicit request =>

    Contact.form.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Contact.all, errors)),
      contact => {
        Contact.create(contact)
        Redirect(routes.ContactController.index())
      }
    )

  }

  def edit(id: Long) = Action {
    Contact.get(id).map { contact =>
      Ok(views.html.editContact(id, Contact.form.fill(contact)))
    } getOrElse {
      Redirect(routes.ContactController.index())
    }
  }

  def update(id: Long) = Action {implicit request =>
    Contact.form.bindFromRequest.fold(
      errors => BadRequest(views.html.editContact(id,errors)),
      contact => {
        Contact.update(id,contact)
        Redirect(routes.ContactController.index())
      }
    )
  }

  def delete(id: Long) = Action {
    Contact.delete(id)
    Redirect(routes.ContactController.index())
  }

}
