package edu.iiitb.controller;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;
import edu.iiitb.model.Order;

public class ShowOrdersAdmin extends ActionSupport {

	ArrayList<Order> orderList ;
	
	public ArrayList<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		orderList = DB.getOrderList();
		System.out.println("orders got as"+orderList);
		return "success";
	}


}
