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
	 int walletamount=0;
	 int walletflag=0;
	
	 
	 public int getWalletflag() {
		return walletflag;
	}


	public void setWalletflag(int walletflag) {
		this.walletflag = walletflag;
	}


	public int getWalletamount() {
		return walletamount;
	}


	public void setWalletamount(int walletamount) {
		this.walletamount = walletamount;
	}


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
		 System.out.println("user details.......");
			if(userid!=-1) 	
				{
				//isLogin="true";
			  
			 //if(isLogin.equals("true"))
			 
				// userid=Integer.parseInt(sessionMap.get("userID").toString());
				emailid=(String) sessionMap.get("email");
				System.out.println("user id-----------="+sessionMap.get("userID"));
				DB.getAddress(addr,userid);
				cartid=(int)sessionMap.get("cartid");
			int totalamount=	DB.getProducts(orditm,cartid);
			System.out.println("Total::"+totalamount);
				walletamount = DB.getamount(Integer.parseInt(sessionMap.get("userID").toString()));
				System.out.println("Wallet::::"+walletamount);
				if(walletamount>totalamount)
					walletflag=1;
			
				}
			System.out.println("wallet f::"+walletflag);
			 
			System.out.println("I m here");
			DB.getBankName(bank);
			
				
		
			return "success";
		//} else
		//	return "error";
	}
	

}
