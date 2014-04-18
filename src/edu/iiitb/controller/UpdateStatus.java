package edu.iiitb.controller;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;

public class UpdateStatus {

	int orderitemid;
	String got_orderstatus;
	
	public int getOrderitemid() {
		return orderitemid;
	}

	public void setOrderitemid(int orderitemid) {
		this.orderitemid = orderitemid;
	}

	public String getGot_orderstatus() {
		return got_orderstatus;
	}

	public void setGot_orderstatus(String got_orderstatus) {
		this.got_orderstatus = got_orderstatus;
	}

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Came to edit status");
		System.out.println("Order item id="+orderitemid+" and status to be changed to= "+got_orderstatus);
		DB.setOrderItemStatus(orderitemid,got_orderstatus);
		return "success";
	}

}
