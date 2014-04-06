package edu.iiitb.controller;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

public class AddCategory extends ActionSupport {

	/**
	 * 
	 */
	public ArrayList<String> LeafList;
	public ArrayList<String> getLeafList() {
		return LeafList;
	}
	public void setLeafList(ArrayList<String> leafList) {
		LeafList = leafList;
	}
	private static final long serialVersionUID = 1L;
	int clicked_id;
	public int getClicked_id() {
		return clicked_id;
	}
	public void setClicked_id(int clicked_id) {
		this.clicked_id = clicked_id;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		LeafList = new ArrayList<String>();
		LeafList.add("Leaf");
		LeafList.add("Not Leaf");
		return "success";
	}

}
