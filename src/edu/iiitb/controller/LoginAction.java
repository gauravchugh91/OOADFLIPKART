package edu.iiitb.controller;

import java.io.*;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;
import edu.iiitb.model.UserWho;

public class LoginAction extends ActionSupport {

	public int getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(int isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	private String email;
	private String password;
	private int isLoggedIn;
	private String msg;

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


	public static String isEmailNotValid(String email,String password) {
		
		System.out.println("Index of '@' in "+email+" ="+email.indexOf("@"));
		
		if((email.indexOf('@')<0 && email.indexOf(".",email.indexOf("@"))<0 ))
			    {
				System.out.println("I was executed! 1)");
				     return "Please specify a VALID Email Address";	    	
			    }
		
		else if(password==null||password==" ")
			    {
		            return "Please specify a password";
		        }
			  return null;
		   }
		

	

	public String execute() {
		Map<String, Object> sessionMap = ActionContext.getContext()
				.getSession();
		setIsLoggedIn(0);
		String selectionModifier;
		System.out.println(getEmail() + " " + getPassword());
		selectionModifier = " where " + "email = '" + getEmail() + "'"
				+ " and " + "password = '" + getPassword() + "'";

		UserWho sysUser = DB.whoIsLogin(selectionModifier);
		if (sysUser != null) {
			System.out.println(sysUser.getIsAdmin() + sysUser.getUserID());
			sessionMap.put("login", "true");
			sessionMap.put("email", sysUser.getEmail());
			sessionMap.put("userID", sysUser.getUserID());
			Wishlist wishlist = new Wishlist();
			wishlist.WishlistInsert();

			setIsLoggedIn(1);
			if (sysUser.getIsAdmin() == 1) {
				System.out.println("Admin " + getIsLoggedIn());
				return "admin";
			} else if (sysUser.getIsAdmin() == 0) {
				System.out.println("Customer " + getIsLoggedIn());
				return "customer";
			}
		}
		msg = isEmailNotValid(getEmail(), getPassword());
		return "error";
	}

	/*
	 * public void validate() { System.out.print("Hi"); if ((email == null ||
	 * email.trim().equals(""))) addFieldError("email",
	 * "Email cannot be left blank"); if ((password == null ||
	 * password.trim().equals(""))) addFieldError("password",
	 * "Password cannot be left blank");
	 * 
	 * }
	 */


}
