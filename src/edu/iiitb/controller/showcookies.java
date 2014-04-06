package edu.iiitb.controller;

import com.opensymphony.xwork2.ActionSupport;

public class showcookies extends ActionSupport {
	private String message;
	private String mycook;
	

	public String getMycook() {
		return mycook;
	}

	public void setMycook(String mycook) {
		this.mycook = mycook;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String execute() throws Exception {
		setMessage("Hello " + getMycook());
		return "success";
	}
}