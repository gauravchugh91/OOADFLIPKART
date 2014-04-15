package edu.iiitb.controller;

import java.util.ArrayList;
import java.util.Map;


import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.database.DB;
import edu.iiitb.model.DeliveryAddress;
import edu.iiitb.model.Order;
import edu.iiitb.model.OrderItem;

public class DeliveryAddressAction  {

	int userid;
	String name;
	String address;
	String city;
	String state;
	String country;
	int pincode;
	String emailid;
	int phone;
	int addressid;
	
	int cartid;
	
	Map<String, Object> sessionMap = ActionContext.getContext().getSession();
	ArrayList<OrderItem> orditm ;
	
	
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public ArrayList<OrderItem> getOrditm() {
		return orditm;
	}
	public void setOrditm(ArrayList<OrderItem> orditm) {
		this.orditm = orditm;
	}
	public int getAddressid() {
		return addressid;
	}
	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
ArrayList<DeliveryAddress> addr = new ArrayList<DeliveryAddress>();
	
ArrayList<String> bank = new ArrayList<String>();
	public ArrayList<String> getBank() {
	return bank;
}
public void setBank(ArrayList<String> bank) {
	this.bank = bank;
}
	public ArrayList<DeliveryAddress> getAddr() {
		return addr;
	}

	public void setAddr(ArrayList<DeliveryAddress> addr) {
		this.addr = addr;
	}
public String execute() throws Exception {
	
	//unregistered user is put in session whenever unregistered user places an order
	if (sessionMap.containsKey("unregistered")) {
		System.out.println("Unregistered user Entry");
		DB.AddDeliveryAddress((int)sessionMap.get("unregistered"),name,address,city,state,country,pincode,emailid,phone);
		return "success";
		
	} else
	{	
		userid=Integer.parseInt(sessionMap.get("userID").toString());
		emailid=(String) sessionMap.get("email");
		DB.AddDeliveryAddress(userid,name,address,city,state,country,pincode,emailid,phone);
		DB.getAddress(addr,userid);
		DB.getBankName(bank);
		return "success";
		
	}
	
	}
public String existingAddress () throws Exception { 
	orditm= new ArrayList<OrderItem>();
	cartid=(int)sessionMap.get("cartid");
	System.out.println("Cart id::::"+sessionMap.get("cartid"));
	sessionMap.put("addrid", addressid);
	
	/*DB.getProducts(orditm,cartid);
	for (OrderItem o:orditm)
	System.out.println("p name"+o.getProductname());
    System.out.println("Address::"+addressid);*/


return "success";
}
}