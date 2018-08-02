package com.example.jsf.phonebook.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.example.jsf.phonebook.model.User;
import com.example.jsf.phonebook.service.UserService;

@ManagedBean(eager = true)
public class UserController {

	@ManagedProperty(value ="#{userService}")
	UserService userService;

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
			return "userHome?faces-redirect=true";
		} else {

			return "login";
		}

	}
}
