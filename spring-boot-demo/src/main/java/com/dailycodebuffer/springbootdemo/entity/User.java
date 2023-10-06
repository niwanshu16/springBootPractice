package com.dailycodebuffer.springbootdemo.entity;

public class User {
	private String name;
	private String id;
	private String emailID;
	
	public User(String id, String name, String emailID) {
		this.name = name;
		this.id = id;
		this.emailID = emailID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

}
