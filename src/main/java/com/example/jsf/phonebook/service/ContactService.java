package com.example.jsf.phonebook.service;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.example.jsf.phonebook.dao.ContactDao;
import com.example.jsf.phonebook.model.Contact;

@ManagedBean(eager = true)
public class ContactService {

	public ContactDao getContactDao() {
		return contactDao;
	}

	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	@ManagedProperty(value = "#{cDao}")
	private ContactDao contactDao;

	public boolean createContact(Contact c) {
		return contactDao.createContact(c);
	}

	public Contact getContactById(long id) {
		return contactDao.getContactById(id);
	}

	public Contact getContactByName(String name) {
		return contactDao.getContactByName(name);
	}

	public boolean updateContact(Contact c) {
		return contactDao.updateContact(c);
	}

	public boolean updateContactById(long id, String name) {
		return contactDao.updateContactById(id, name);
	}

	public boolean updateContactTellNumberById(long id, String tell) {
		return contactDao.updateContactTellNumberById(id, tell);
	}

	public boolean deleteContact(Contact c) {
		return contactDao.deleteContact(c);
	}

	public boolean deleteContactByName(String name) {
		return contactDao.deleteContactByName(name);
	}

}
