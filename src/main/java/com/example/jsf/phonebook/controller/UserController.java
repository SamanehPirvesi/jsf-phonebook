package com.example.jsf.phonebook.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.example.jsf.phonebook.model.Contact;
import com.example.jsf.phonebook.model.User;
import com.example.jsf.phonebook.service.UserService;
@SessionScoped
@ManagedBean( name ="userController",eager = true)
public class UserController {

	
	private UserService userService;
	private long loginId;
	private String loginname;

	

	public UserController() {
		userService=new UserService();
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public long getLoginId() {
		return loginId;
	}

	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String doLogin(User user) {
		User readedUser = userService.getUserByUserName(user.getUsername());
		if (user != null && readedUser.getPassword().equals(user.getPassword())) {
			readedUser.setLoggedIn(true);
			userService.updateUser(readedUser);
			loginId=readedUser.getUserId();
			loginname=readedUser.getUsername();
			return "userHome?faces-redirect=true";
		} else {

			return "login";
		}
	}
	
		public List<Contact> listOfContact(long userId){
			return userService.getListOfContact(userId);
		}
	
}
