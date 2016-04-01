package com.insart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sessions")
public class Session {
	
	
	@Column(name = "user_id", unique = true, nullable = false, length = 255)
	private String userId;
	
	@Id
	@Column(name = "session_id", unique = true, nullable = false, length = 255)
	private String sessionId;
	
	public Session() {
	}
	
	public Session(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public Session(String userId, String sessionId) {
		this.sessionId = sessionId;
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
