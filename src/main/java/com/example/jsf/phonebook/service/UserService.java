package com.example.jsf.phonebook.service;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.example.jsf.phonebook.dao.UserDao;
import com.example.jsf.phonebook.model.Contact;
import com.example.jsf.phonebook.model.User;

@ManagedBean( name ="userService",eager = true)
public class UserService {

	
	@ManagedProperty(value ="#{uDao}")
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public boolean createUser(User u) {
		return userDao.createUser(u);

	}
	public User getUserById(long id) {
		return userDao.getUserById(id);
		
	}
	public User getUserByUserName(String username) {
		return userDao.getUserByUserName(username);
		
	}
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}
	public List<Contact> getListOfContact(long id) {
		return userDao.getListOfContact(id);
	}
	public boolean updateUser(User u) {
		return userDao.updateUser(u);
		
	}
	public boolean updateUserById(long id, String name) {
		return userDao.updateUserById(id, name);
	}
	public boolean updatePassworldByUsername(String username, String password) {
		return userDao.updatePassworldByUsername(username, password);
		
	}
	public boolean deleteUser(User u) {
		return userDao.deleteUser(u);
		
	}

}