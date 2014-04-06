package edu.iiitb.controller;

import java.util.ArrayList;
import java.util.Map;






import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.database.DB;

public class DeliveryAddressAction  {

	int userid;
	String name;
	String address;
	String city;
	String state;
	String country;
	int pincode;
	String email;
	int phone;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
public String execute() throws Exception {
	//Map<String, Object> sessionMap = ActionContext.getContext()
		//	.getSession();
	//if (sessionMap.containsKey("userid")) {
		DB.AddDeliveryAddress(userid,name,address,city,state,country,pincode,email,phone);
		
		return "success";
	//} else
	//	return "error";
}
}