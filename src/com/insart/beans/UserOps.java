package com.insart.beans;

import com.insart.model.User;
import com.insart.utils.UserUtils;

public class UserOps {
	private User user;
	private UserUtils utils;
	
	public UserOps(User user, UserUtils utils) {
		this.user = user;
		this.utils = utils;
	}
	
	public void authenticateUser(String username, String password) {
		User u = utils.getUser(username);
		if (u == null) {
			user.setAuntheticated(false);
			return;
		}
		if (username.equals(u.getUsername()) & password.equals(u.getPassword())) {
			user.setAuntheticated(true);
		} else {
			user.setAuntheticated(false);
		}
	}
	
	public void registerUser(String username, String password) {
		if (utils.addUser(username, password)) {
			user.setRegistered(true);
		} else {
			user.setRegistered(false);
		}
	}
	
	public void registerUser(String username, String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
			user.setConfirmPaswordWrong(false);
			if (utils.addUser(username, password)) {
				user.setRegistered(true);
			} else {
				user.setRegistered(false);
			}
		} else {
			user.setConfirmPaswordWrong(true);
		}
	}
}
