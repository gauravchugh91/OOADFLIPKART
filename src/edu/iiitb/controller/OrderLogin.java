package edu.iiitb.controller;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;
import edu.iiitb.model.DeliveryAddress;
import edu.iiitb.model.OrderItem;
import edu.iiitb.model.UserWho;
 /* Login action on Place Order Page 
  * Author: Chirag Saraiya
  * */

public class OrderLogin extends ActionSupport  {
	public String email;
	public String password;
	ArrayList<DeliveryAddress> addr;
	int existingemail=0;
	int userid=0;
	
	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public int getExistingemail() {
		return existingemail;
	}


	public void setExistingemail(int existingemail) {
		this.existingemail = existingemail;
	}

	ArrayList<OrderItem> orditm =new ArrayList<OrderItem>();
	int cartid;
	
	public int getCartid() {
		return cartid;
	}


	public void setCartid(int cartid) {
		this.cartid = cartid;
	}


	public ArrayList<OrderItem> getOrditm() {
		return orditm;
	}


	public void setOrditm(ArrayList<OrderItem> orditm) {
		this.orditm = orditm;
	}


	public ArrayList<DeliveryAddress> getAddr() {
		return addr;
	}


	public void setAddr(ArrayList<DeliveryAddress> addr) {
		this.addr = addr;
	}

	String isLogin;
	String emailid;

	public String getEmailid() {
		return emailid;
	}


	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}


	public String getIsLogin() {
		return isLogin;
	}


	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}


	

	public boolean my_checkbox;

	public boolean isMy_checkbox() {
		return my_checkbox;
	}


	public void setMy_checkbox(boolean my_checkbox) {
		this.my_checkbox = my_checkbox;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	 ArrayList<String> bank = new ArrayList<String>();

	public ArrayList<String> getBank() {
		return bank;
	}


	public void setBank(ArrayList<String> bank) {
		this.bank = bank;
	}


	public String execute()   {
		String selectionModifier;
		addr=new ArrayList<DeliveryAddress>();
		
		//Here query is written here because Login method takes this as parameter//
		
		
		selectionModifier = " where " + "email = '" + email + "'"
				+ " and " + "password = '" + password + "'";
		
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		
	//int userid=(int)sessionMap.get("userID");
		
		System.out.println("I m here");
		System.out.println(getPassword());
		System.out.println(getEmail());
		System.out.println("checked value"+my_checkbox);
		DB.getBankName(bank);
		//* checks check box for existing Users
		if (my_checkbox==true){
			UserWho sysUser = DB.whoIsLogin(selectionModifier);
			System.out.println(sysUser.getUserID() +"***"+sysUser.getPassword());
		
			
			
			if (sysUser.equals(null)){
				addActionError("Incorrect email address/password.");	
				return "failure";
				// 
			}else {
				userid=sysUser.getUserID();
				System.out.println("User id------>"+userid);
				/* Setting userID in session for existing account user on place order page */
				System.out.println("User id^^^^^"+userid);
				sessionMap.put("userID", sysUser.getUserID());
				
				cartid=(int)sessionMap.get("cartid");
				DB.getProducts(orditm,cartid);
				DB.getAddress(addr,sysUser.getUserID());
				emailid=sysUser.getEmail();
				isLogin="true";    //flag to display Login Form on order.jsp page
				return "success";
			}
			
		}
		else
		{ /* For unregistered user */
			
			System.out.println("check box");
			//insert into customer
			userid = DB.Unreguser(email);
			System.out.println("Userid--------->"+userid);
			if(userid==0)
			{
				System.out.println("User id inside orderlogin"+userid);
				existingemail=1;
				return "success";
				
			}
			emailid=email;
			//isLogin="false"; //flag to check on JSP whether user is logedin or not
		// for unregistered user set email session
			sessionMap.put("unregistered", userid); 
			/* Getting Cart item for Order Summery page*/
			cartid=(int)sessionMap.get("cartid");
			DB.getProducts(orditm,cartid);

			System.out.println("********" +userid);
			return "success";
		}
		
		
	}

	
	
	
}
/*
not logged and placed order

specified email address but not logged in
	1. account already exists
	 then you fetch the corresponding user id from given email(unique) 
	 and set things but make sure he doesn't log in in website ....

	2. account does not exists
		simply insert entry into Customer table , get user id and perform operations




specified email and logged in




*/