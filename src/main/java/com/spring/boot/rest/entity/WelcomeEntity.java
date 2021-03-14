package com.spring.boot.rest.entity;

public class WelcomeEntity {

	private String name;

	private String message;

	public WelcomeEntity() {
		super();
	}

	public WelcomeEntity(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
