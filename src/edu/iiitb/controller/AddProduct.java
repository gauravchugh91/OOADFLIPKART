package edu.iiitb.controller;

import java.util.ArrayList;



import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;
import edu.iiitb.model.Attribute;


public class AddProduct extends ActionSupport {

	int clicked_id;
	int Price;
	ArrayList<String> attnames;
	public ArrayList<String> getAttnames() {
		return attnames;
	}
	public void setAttnames(ArrayList<String> attnames) {
		this.attnames = attnames;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public ArrayList<String> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<String> products) {
		this.products = products;
	}
	ArrayList<String> products;
	ArrayList<Attribute> Attributes;
	public int getClicked_id() {
		return clicked_id;
	}
	public ArrayList<Attribute> getAttributes() {
		return Attributes;
	}
	public void setAttributes(ArrayList<Attribute> attributes) {
		Attributes = attributes;
	}
	public void setClicked_id(int clicked_id) {
		this.clicked_id = clicked_id;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		attnames =  new ArrayList<String>();
		System.out.println("product to be inserted in category with id:"+clicked_id);
		products = DB.getProducts(clicked_id);
		Attributes = DB.getAttributes();
		for(Attribute a:Attributes) {
			String name = a.AttrName;
			attnames.add(name);
			System.out.println("attribute"+a.AttrName);
		}
		//System.out.println("Price is "+ Price);
		return "success";
	}

}
