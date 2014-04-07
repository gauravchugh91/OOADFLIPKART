package edu.iiitb.controller;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;
import edu.iiitb.model.DisplayProd;

public class ShowProdAdmin extends ActionSupport {

	
	String prod_name;
	ArrayList<DisplayProd> dispProd;
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("For Prod:"+prod_name);
		dispProd = new ArrayList<DisplayProd>();
		dispProd = DB.getThisProd(prod_name);
		System.out.println(dispProd);
		return "success";
	}

}
