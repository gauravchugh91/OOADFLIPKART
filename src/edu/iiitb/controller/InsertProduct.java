package edu.iiitb.controller;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;
import edu.iiitb.model.Attribute;



public class InsertProduct extends ActionSupport {

	ArrayList<Attribute> Attributes;
	int Price;
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Attributes = new ArrayList<Attribute>();
		Attributes = DB.getAttributes();
		//System.out.println("All the attributes got"+Attributes);
		System.out.println("Came here after inserting with price "+Price);
		return "success";
	}
	public ArrayList<Attribute> getAttributes() {
		return Attributes;
	}
	public void setAttributes(ArrayList<Attribute> attributes) {
		Attributes = attributes;
	}

}
