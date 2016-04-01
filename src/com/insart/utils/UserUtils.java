package com.insart.utils;

import com.insart.model.User;

public interface UserUtils {
	public boolean addUser(String username, String password);
	public User getUser(String username);
	public boolean createSession(String userName, String sessionId);
	public boolean dropSession(String sessionId);
}
