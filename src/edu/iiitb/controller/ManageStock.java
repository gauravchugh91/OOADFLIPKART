package edu.iiitb.controller;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;
import edu.iiitb.model.StockManagement;

public class ManageStock extends ActionSupport {

	ArrayList<StockManagement> products;
	
	public ArrayList<StockManagement> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<StockManagement> products) {
		this.products = products;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		products =  new ArrayList<StockManagement>();
		DB.setStockManagement(products);
		System.out.println("products got as "+products);
		return "success";
	}
	

}
