package edu.iiitb.controller;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.database.DB;

public class DeactivateAccount {

	private String password;
    int status;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String navigate() {
		//System.out.println("Deactivate");
		status=2;
		return "success";
	}
	
	public String deactivate() {
		
		Map<String, Object> sessionMap = ActionContext.getContext()
				.getSession();
		
		status=0;

		if (sessionMap.containsKey("userID")) {
			String query = "update usercredentials set isactivated = 0 where userid="
					+ Integer.parseInt(sessionMap.get("userID").toString());
			status= DB.updateAccount(query);
			if(status==1) {
				sessionMap.put("userID",-1);
				sessionMap.remove("email");
				sessionMap.remove("cartid");
			}
			return "success";
		}
		return "error";

	}
	
}
