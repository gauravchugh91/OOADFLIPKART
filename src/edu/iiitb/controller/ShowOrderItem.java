package edu.iiitb.controller;

import com.opensymphony.xwork2.ActionSupport;

public class ShowOrderItem extends ActionSupport{

int orderid;
	
	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
	@Override


	public String execute() throws Exception {
		// TODO Auto-generated method stub/
		
		
		return "success";
	}
	

}
