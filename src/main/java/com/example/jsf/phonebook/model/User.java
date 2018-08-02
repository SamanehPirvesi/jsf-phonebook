package com.example.jsf.phonebook.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@ManagedBean(eager = true)
@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@Column(unique = true)
	private String username;
	private String password;
	private boolean isLoggedIn;
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Contact> listContact = new ArrayList<>();

	public User() {

	}

	/**
	 * every time make the user by default isLoggedIn is false
	 * 
	 * @param username
	 * @param password
	 */

	public User(String username, String password) {

		this.username = username;
		this.password = password;
		this.isLoggedIn = false;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void addContact(Contact c) {
		listContact.add(c);
	}

	public List<Contact> getListContact() {
		return listContact;
	}

	public void setListContact(List<Contact> listContact) {
		this.listContact = listContact;
	}

}
