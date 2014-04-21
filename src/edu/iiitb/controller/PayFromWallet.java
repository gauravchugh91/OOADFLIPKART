package edu.iiitb.controller;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.database.DB;

public class PayFromWallet {

	Map<String, Object> sessionMap = ActionContext.getContext().getSession();
	
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception
	
	{
		System.out.println("Wallet");
		DB.walletUpdate((int)sessionMap.get("userID"),(int)sessionMap.get("cartid"));
		DB.insertOrder((int)sessionMap.get("userID"),(int)sessionMap.get("cartid"),(int)sessionMap.get("addrid"));
		return "success";
	}
	
	
	
	
	
}
