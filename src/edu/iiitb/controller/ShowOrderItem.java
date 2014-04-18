package edu.iiitb.controller;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;
import edu.iiitb.model.OrderItem;

public class ShowOrderItem extends ActionSupport{

int orderid;
ArrayList<OrderItem> orderItems;
	
	public ArrayList<OrderItem> getOrderItems() {
	return orderItems;
}

public void setOrderItems(ArrayList<OrderItem> orderItems) {
	this.orderItems = orderItems;
}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
	@Override


	public String execute() throws Exception {
		// TODO Auto-generated method stub/
		orderItems =  new ArrayList<OrderItem>();
		
		
		DB.setOrderItems(orderItems,orderid);
	    
		
		for(OrderItem o:orderItems)
	    	;
		System.out.println("Came with order id"+orderid);
		return "success";
	}
	

}
