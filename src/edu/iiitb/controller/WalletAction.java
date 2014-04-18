package edu.iiitb.controller;

import java.util.Map;

import edu.iiitb.database.DB;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class WalletAction extends ActionSupport {

	public int userid;
	public int walletamount;
	
	public int getWalletamount() {
		return walletamount;
	}
	public void setWalletamount(int walletamount) {
		this.walletamount = walletamount;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String execute(){
		Map<String, Object> sessionMap = ActionContext.getContext()
				.getSession();
		if(sessionMap.containsKey("userID")) {
		
		System.out.println("****************************************herre*********");
		walletamount = DB.getamount(Integer.parseInt(sessionMap.get("userID").toString()));
		System.out.print(getUserid());
		
		System.out.println(walletamount+"in action****");
		return "success";
		}
		return "success";
		
	}
	
	
}
