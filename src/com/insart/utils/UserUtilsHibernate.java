package com.insart.utils;

import com.insart.dao.UserManagementHibernateImpl;
import com.insart.model.Session;
import com.insart.model.User;

public class UserUtilsHibernate implements UserUtils {
	private static UserManagementHibernateImpl userManager = new UserManagementHibernateImpl();; 
	
	public UserUtilsHibernate(){
	}
	
	@Override
	public boolean addUser(String username, String password) {
		return userManager.addUser(username, password);
	}
	@Override
	public User getUser(String username) {
		return userManager.getUser(username);
	}
	@Override
	public boolean createSession(String userName, String sessionId) {
		return userManager.createSession(userName, sessionId);
	}
	@Override
	public boolean dropSession(String sessionId) {
		return userManager.dropSession(new Session(sessionId));
	}

}
