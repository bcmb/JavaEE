package com.insart.model;

import java.io.Serializable;

public class AuthData implements Serializable {
	private String username;
	private boolean authenticated;
	private boolean authorized;
	
	public AuthData (String username, boolean authenticated, boolean authorized) {
		this.username = username;
		this.authenticated = authenticated;
		this.authorized = authorized;
	}
}

