package com.insart.model;

import java.io.Serializable;

public class RegisterData implements Serializable {
	private boolean regSuccess;
	private String userName;
	private boolean confirmPaswordWrong;
	
	public RegisterData(String userName, boolean regSuccess, boolean confirmPaswordWrong) {
		this.regSuccess = regSuccess;
		this.userName = userName;
		this.confirmPaswordWrong = confirmPaswordWrong;
	}
}
