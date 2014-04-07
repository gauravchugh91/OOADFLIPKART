package edu.iiitb.controller;
import java.util.ArrayList;


import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;
import edu.iiitb.model.category;


public class ShowCategories extends ActionSupport {

	/**
	 * 
	 */
	public ArrayList<String> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<String> products) {
		this.products = products;
	}
	ArrayList<String> products;
	ArrayList<category> cats;
    int clicked_id;
    int isleaf;
    int level;
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}

	public int getClicked_id() {
		return clicked_id;
	}

	public void setClicked_id(int clicked_id) {
		this.clicked_id = clicked_id;
	}

	public ArrayList<category> getCats() {
		return cats;
	}

	public void setCats(ArrayList<category> cats) {
		this.cats = cats;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("clicked is"+clicked_id);
		
		level = DB.CheckLevel(clicked_id);
		if(level==1)
		cats = DB.getCategories(clicked_id);
		else
		products = DB.getProducts(clicked_id);
		return "success";
	}

}
