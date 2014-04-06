package edu.iiitb.controller;


import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class Logout {
	public String execute() throws Exception {
		Map<String, Object> sessionMap = ActionContext.getContext()
				.getSession();

		if (sessionMap.containsKey("userID")) {
			
			sessionMap.remove("userID");
		}

		return "success";
	}
}
