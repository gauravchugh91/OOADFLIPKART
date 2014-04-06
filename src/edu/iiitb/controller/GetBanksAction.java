package edu.iiitb.controller;

import java.util.ArrayList;

import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.database.DB;

//Get All the banks from DB
public class GetBanksAction {
	public String isLogin;
	
	public String getIsLogin() {
			return isLogin;
		}


		public void setIsLogin(String isLogin) {
			this.isLogin = isLogin;
		}
		private SessionMap<String, Object> sessionMap;

		public SessionMap<String, Object> getSessionMap() {
			return sessionMap;
		}

		public void setSessionMap(SessionMap<String, Object> sessionMap) {
			this.sessionMap = sessionMap;
		}
	ArrayList<String> bank = new ArrayList<String>();
	public ArrayList<String> getBank() {
		return bank;
	}

	public void setBank(ArrayList<String> bank) {
		this.bank = bank;
	}
	 String emailid;
	public String getEmailid() {
		return emailid;
	}


	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}


	public String execute() throws Exception {
		 sessionMap = (SessionMap<String, Object>) ActionContext.getContext().getSession();
		if(!sessionMap.isEmpty())
			
		{ 
			if(sessionMap.containsKey("login"))
			{
			isLogin=(String) sessionMap.get("login");
		  
		 if(isLogin.equals("true"))
		 {
			emailid=(String) sessionMap.get("email");
		 }
			}
		}//System.out.println(isLogin+"blah blah ..");
		 
		 //if (sessionMap.containsKey("userid")) {
			
			DB.getBankName(bank);
		
			return "success";
		//} else
		//	return "error";
	}
	
	
}
