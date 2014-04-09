package edu.iiitb.controller;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;
import edu.iiitb.model.Attribute;



public class InsertProduct extends ActionSupport {

	ArrayList<Attribute> Attributes;
	int Price;
	String clicked_id;
	String productname;
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getClicked_id() {
		return clicked_id;
	}
	public void setClicked_id(String clicked_id) {
		this.clicked_id = clicked_id;
	}
	ArrayList<String> AttValues;
	
	public ArrayList<String> getAttValues() {
		return AttValues;
	}
	public void setAttValues(ArrayList<String> attValues) {
		AttValues = attValues;
	}
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
		int attIds[] = {} ;
		int i=0;
		ArrayList<String> attnames = new ArrayList<String>();
	    for(Attribute a:Attributes){
		System.out.println("All the attribute names"+a.AttrName);
		attnames.add(a.AttrName);
		}
	    System.out.println("All the attributes got"+AttValues);
	    System.out.println("adding to category"+clicked_id);
		int success = DB.insertProduct(productname,clicked_id,AttValues,attnames);
		return "success";
	}
	public ArrayList<Attribute> getAttributes() {
		return Attributes;
	}
	public void setAttributes(ArrayList<Attribute> attributes) {
		Attributes = attributes;
	}

}
