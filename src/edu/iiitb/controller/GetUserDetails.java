package edu.iiitb.controller;

import java.util.ArrayList;

import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.database.DB;
import edu.iiitb.model.DeliveryAddress;
import edu.iiitb.model.OrderItem;



public class GetUserDetails {
	public String isLogin;
	 String emailid;
	 int userid;
	 ArrayList<OrderItem> orditm=new ArrayList<OrderItem>();
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


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}

	private SessionMap<String, Object> sessionMap;
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
		

		public SessionMap<String, Object> getSessionMap() {
			return sessionMap;
		}

		public void setSessionMap(SessionMap<String, Object> sessionMap) {
			this.sessionMap = sessionMap;
		}
		
	 ArrayList<String> bank = new ArrayList<String>();
	ArrayList<DeliveryAddress> addr = new ArrayList<DeliveryAddress>();
	
	
	public ArrayList<DeliveryAddress> getAddr() {
		return addr;
	}

	public void setAddr(ArrayList<DeliveryAddress> addr) {
		this.addr = addr;
	}

	public ArrayList<String> getBank() {
		return bank;
	}

	public void setBank(ArrayList<String> bank) {
		this.bank = bank;
	}
	public String execute() throws Exception {
		 sessionMap = (SessionMap<String, Object>) ActionContext.getContext().getSession();
		 userid=Integer.parseInt(sessionMap.get("userID").toString());
			if(userid!=-1)
				
			{ 
				//if(sessionMap.containsKey("login"))
				{
				//isLogin="true";
			  
			 //if(isLogin.equals("true"))
			 {
				// userid=Integer.parseInt(sessionMap.get("userID").toString());
				emailid=(String) sessionMap.get("email");
				System.out.println("user id="+sessionMap.get("userID"));
				DB.getAddress(addr,userid);
				cartid=(int)sessionMap.get("cartid");
				DB.getProducts(orditm,cartid);
			 }
				}
			}
			System.out.println("I m here");
			DB.getBankName(bank);
			
				
		
			return "success";
		//} else
		//	return "error";
	}
	

}
