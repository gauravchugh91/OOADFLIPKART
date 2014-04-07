package edu.iiitb.controller;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;
import edu.iiitb.model.DeliveryAddress;
import edu.iiitb.model.UserWho;


public class OrderLogin extends ActionSupport{
	public String email;
	public String password;
	ArrayList<DeliveryAddress> addr;
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
		selectionModifier = " where " + "email = '" + email + "'"
				+ " and " + "password = '" + password + "'";

		System.out.println("I m here");
		System.out.println(getPassword());
		System.out.println(getEmail());
		System.out.println("checked value"+my_checkbox);
		DB.getBankName(bank);
		if (my_checkbox==true){
			UserWho sysUser = DB.whoIsLogin(selectionModifier);
			System.out.println(sysUser.getUserID() +"***"+sysUser.getPassword());
		
			if (sysUser.equals(null)){
				return "failure";
				// addActionError("Incorrect email address/password.");	
			}else {
				DB.getAddress(addr,sysUser.getUserID());
				emailid=sysUser.getEmail();
				isLogin="true";
				return "success";
			}
			
		}
		else
		{

			//insert into customer
			int userid = DB.Unreguser(email);
			System.out.println("********" +userid);
			return "success";
		}
		
		
	}

}
