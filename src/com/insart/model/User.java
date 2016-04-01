package com.insart.model;

import java.io.Serializable;

import javax.enterprise.inject.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Model
@Entity
@Table(name = "users")
public class User implements Serializable {
	
	
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
		
	@Id
	@Column(name="username", unique = true, nullable = false, length = 45)
	private String username;

	@Column(name="password", unique = false, nullable = false, length = 45)
	private String password;
	@Transient
	private boolean confirmPaswordWrong;
	@Transient
	private boolean auntheticated;
	@Transient
	private boolean registered;
	
	public User() {
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public boolean isAuntheticated() {
		return auntheticated;
	}

	public void setAuntheticated(boolean auntheticated) {
		this.auntheticated = auntheticated;
	}
		
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public boolean isConfirmPaswordWrong() {
		return confirmPaswordWrong;
	}

	public void setConfirmPaswordWrong(boolean confirmPaswordWrong) {
		this.confirmPaswordWrong = confirmPaswordWrong;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
