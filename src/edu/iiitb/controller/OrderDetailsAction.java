package edu.iiitb.controller;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.database.DB;
import edu.iiitb.model.Order;

public class OrderDetailsAction {
int addressid;
ArrayList<String> bank = new ArrayList<String>();
private SessionMap<String, Object> sessionMap;
String isLogin;
String emailid;

public SessionMap<String, Object> getSessionMap() {
	return sessionMap;
}

public void setSessionMap(SessionMap<String, Object> sessionMap) {
	this.sessionMap = sessionMap;
}

public ArrayList<String> getBank() {
	return bank;
}

public void setBank(ArrayList<String> bank) {
	this.bank = bank;
}

public int getAddressid() {
	return addressid;
}

public void setAddressid(int addressid) {
	this.addressid = addressid;
}

public String execute() throws Exception 
{
	Map<String, Object> sessionMap = ActionContext.getContext().getSession();
	System.out.println("Cart id::::"+sessionMap.get("cartid"));
	Order o=new Order();
	System.out.println("addressid :"+addressid);
	DB.insertAddid(o,addressid);
	DB.getBankName(bank);
return "success";
}

}
