package com.example.jsf.phonebook.model;
import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@ManagedBean(eager=true)
@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long contact_id;
	private String name;
	private String tellnumber;
	@ManyToOne
	private User user;

	public Contact() {
	}

	public Contact(String name, String tellnumber) {
		this.name = name;
		this.tellnumber = tellnumber;
	}

	public long getContact_id() {
		return contact_id;
	}

	public void setContact_id(long contact_id) {
		this.contact_id = contact_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTellnumber() {
		return tellnumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setTellnumber(String tellnumber) {
		this.tellnumber = tellnumber;
	}

	
}
