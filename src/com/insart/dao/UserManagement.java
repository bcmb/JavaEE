package com.insart.dao;

import com.insart.model.Session;
import com.insart.model.User;

public interface UserManagement {
	User getUser(String userName);
	boolean addUser(String username, String password);
	boolean createSession(String userName, String sessionId);
	Session getSession(Session s);
	boolean dropSession(Session s);
}
