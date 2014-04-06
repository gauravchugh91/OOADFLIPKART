package edu.iiitb.controller;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;
import edu.iiitb.model.UserWho;


public class OrderLogin extends ActionSupport{
	public String email;
	public String password;
	
	
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


public String execute() throws Exception   {
		
	
		System.out.println("I m here");
		System.out.println(getPassword());
		System.out.println(getEmail());
		

		UserWho sysUser = DB.whoIsLogin(email,password);
		System.out.println(sysUser.getUserID() +"***"+sysUser.getPassword());
		if (sysUser!=null)
		{
		System.out.println("Error");
			//addActionError("Incorrect email address/password.");
			
			return "success";
			
		} 
		System.out.println("Success");
		return "failure";
	}
}
