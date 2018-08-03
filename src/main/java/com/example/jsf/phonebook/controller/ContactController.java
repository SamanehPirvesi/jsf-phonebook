package com.example.jsf.phonebook.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.example.jsf.phonebook.model.Contact;
import com.example.jsf.phonebook.model.User;
import com.example.jsf.phonebook.service.ContactService;
import com.example.jsf.phonebook.service.UserService;
@SessionScoped
@ManagedBean(eager = true)
public class ContactController {

	
	private ContactService contactService=new ContactService();
	private UserService userService=new UserService();
	@ManagedProperty(value="#{userController}")
	private UserController userController;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public ContactService getContactService() {
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	public String registerContact(Contact contact) {
		User readedUser=userService.getUserById(userController.getLoginId());
		contact.setUser(readedUser);
		readedUser.addContact(contact);
		if (contactService.createContact(contact) == true) {
			return "contactList?faces-redirect=true";
		} else {

			return "newContact?faces-redirect=true";
		}
	}
	
	public void onRowEdit(RowEditEvent event) {
	
        FacesMessage msg = new FacesMessage("contact saved", ((Contact) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
    	
        FacesMessage msg = new FacesMessage("contact deleted", ((Contact) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
