package edu.iiitb.controller;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.database.DB;

public class NetBankingAction {
	public String bank;
	public String accountid;
	public String accountpass;
	public int Amount;
	public int flag;
	public String errormessage="";
	int cartid;
	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	Map<String, Object> sessionMap = ActionContext.getContext().getSession();
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getAccountpass() {
		return accountpass;
	}

	public void setAccountpass(String accountpass) {
		this.accountpass = accountpass;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}


	public String execute() throws Exception{
		System.out.println("I m here");
		System.out.println(getBank());
		System.out.println(getAccountid());
		System.out.println(getAccountpass());
		return "success";
		
	}
	
	public String check() throws Exception{
		System.out.println(getAccountid());
		System.out.println(getAccountpass());
	cartid=(int)sessionMap.get("cartid");
		errormessage= DB.Payfromnetbank(getAccountid(), getAccountpass(), cartid);
		
		
		/*if(errormessage.equals("successfull!!"))
		{
			
			if (sessionMap.containsKey("unregistered"))
			{
			DB.insertOrder((int)sessionMap.get("unregistered"),(int)sessionMap.get("cartid"),(int)sessionMap.get("addrid"));
			}
			else
			{
				DB.insertOrder((int)sessionMap.get("userID"),(int)sessionMap.get("cartid"),(int)sessionMap.get("addrid"));
			}
		}*/
		/*if(status.equals("incorrectdetails")){
			flag=0;
		}
		else if(status.equals("insufficient"))
			flag=1;*/
		
		return "success";
		
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	



	
	
	
}
