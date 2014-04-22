package edu.iiitb.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.database.DB;
import edu.iiitb.model.CardCredentials;
import edu.iiitb.model.Order;


public class PaymentGateway {
	public int bankcardid;
	public String password;
	public int Amount;
	public int userid;
	int statusflag=0;
	public int getStatusflag() {
		return statusflag;
	}


	public void setStatusflag(int statusflag) {
		this.statusflag = statusflag;
	}


	
	
	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	Map<String, Object> sessionMap = ActionContext.getContext().getSession();
	
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}


	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}


	ArrayList<CardCredentials> CardC = new ArrayList<CardCredentials>();
	
	public int getAmount() {
		return Amount;
	}


	public void setAmount(int amount) {
		Amount = amount;
	}


	
	

	public ArrayList<CardCredentials> getCardC() {
		return CardC;
	}


	public void setCardC(ArrayList<CardCredentials> cardC) {
		CardC = cardC;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getBankcardid() {
		return bankcardid;
	}


	public void setBankcardid(int bankcardid) {
		this.bankcardid = bankcardid;
	}


	public String execute() throws SQLException {
		System.out.println(getPassword());
		System.out.println(getBankcardid());
		
		System.out.println("Debit card:::"+bankcardid+password+(int)sessionMap.get("cartid"));
		
		String status= DB.PaymentfromCard(bankcardid, password, (int)sessionMap.get("cartid"));
		System.out.println("Status::"+status);
		
		System.out.println("Unregistered"+(int)sessionMap.get("addrid"));
		if(status.equals("success"))
		{	
			statusflag=1;
			
			if (sessionMap.containsKey("unregistered"))
			{
			DB.insertOrder((int)sessionMap.get("unregistered"),(int)sessionMap.get("cartid"),(int)sessionMap.get("addrid"));
			}
			else
			{
				DB.insertOrder((int)sessionMap.get("userID"),(int)sessionMap.get("cartid"),(int)sessionMap.get("addrid"));
			}
			
			return "success";
		}
			
		statusflag=2;
		System.out.println("Statusflag::"+statusflag);
		return "success";
		/***
		 * 
		 * stock should be updated here................
		 * 
		 */
		
	}
	
}
	
	
	
	
	