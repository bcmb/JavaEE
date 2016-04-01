package com.insart.utils;

import org.apache.log4j.Logger;

import com.insart.model.Session;
import com.insart.model.User;

import com.insart.dao.UserManagementJdbcImpl;

public class UserUtilsJdbc implements UserUtils {
	private final static Logger logger = Logger.getLogger(UserUtilsJdbc.class);
	private static UserManagementJdbcImpl userManager = new UserManagementJdbcImpl();

	public boolean addUser(String username, String password) {
		return userManager.addUser(username, password);
	}
	
	public User getUser(String username) {
		return userManager.getUser(username);
	}
	
	public boolean createSession(String userName, String sessionId) {
		return userManager.createSession(userName, sessionId);
	}
	
	public boolean dropSession(String sessionId) {
		return userManager.dropSession(new Session(sessionId));
	}
}
