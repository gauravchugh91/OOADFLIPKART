package edu.iiitb.controller;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.database.DB;

public class UpdateEmail {

	private String new_email;
	int status;

	public String getNew_email() {
		return new_email;
	}

	public void setNew_email(String new_email) {
		this.new_email = new_email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String navigate() {
		System.out.println("Update email now.");
		status=2;
		return "success";
	}

	public String update() {
		Map<String, Object> sessionMap = ActionContext.getContext()
				.getSession();
		
		setStatus(0);

		if (sessionMap.containsKey("userID")) {
			if (LoginAction.isEmailNotValid(getNew_email(), "abc")==null) {
				
				String query = "update usercredentials set email = '"
						+ getNew_email() + "' where userid="
						+ Integer.parseInt(sessionMap.get("userID").toString());
				
				setStatus(DB.updateAccount(query));
			 
				sessionMap.put("email", getNew_email());
				return "success";
			}
		}
		return "error";

	}
}
