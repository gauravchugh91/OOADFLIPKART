package edu.iiitb.controller;

import com.opensymphony.xwork2.ActionSupport;
import edu.iiitb.*;
import edu.iiitb.database.DB;
@SuppressWarnings("unused")
public class InsertCategory extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String category;
	public int clicked_id;
	public String isLeaf;

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public int getClicked_id() {
		return clicked_id;
	}

	public void setClicked_id(int clicked_id) {
		this.clicked_id = clicked_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("entered category is "+category);
		System.out.println("and is leaf"+isLeaf);
		int result = DB.insertCategory(category,clicked_id);
		System.out.println("rows updated="+result);
		return "success";
	}

}
