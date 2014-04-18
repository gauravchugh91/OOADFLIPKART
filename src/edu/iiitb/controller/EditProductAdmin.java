package edu.iiitb.controller;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;

public class EditProductAdmin extends ActionSupport {

	ArrayList<String> attvalues;
	String prodname;
	
	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public ArrayList<String> getAttvalues() {
		return attvalues;
	}

	public void setAttvalues(ArrayList<String> attvalues) {
		this.attvalues = attvalues;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Got new values as "+attvalues);
		int result =DB.editProduct(attvalues,prodname);
		return "success";
		
	}

}
