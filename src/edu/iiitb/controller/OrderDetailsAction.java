package edu.iiitb.controller;

import java.util.ArrayList;

import org.apache.struts2.dispatcher.SessionMap;

import edu.iiitb.database.DB;
import edu.iiitb.model.OrderDetails;

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
	
	OrderDetails o=new OrderDetails();
	System.out.println("addressid :"+addressid);
	DB.inserAddid(o,addressid);
	DB.getBankName(bank);
return "success";
}

}
