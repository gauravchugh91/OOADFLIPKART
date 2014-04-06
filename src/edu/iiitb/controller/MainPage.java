package edu.iiitb.controller;

import java.util.ArrayList;

import edu.iiitb.database.DB;
import edu.iiitb.model.CategoryDetails;

public class MainPage {

	ArrayList<CategoryDetails> rootCategoryList =new ArrayList<CategoryDetails>();
	
	public ArrayList<CategoryDetails> getRootCategoryList() {
		return rootCategoryList;
	}

	public void setRootCategoryList(ArrayList<CategoryDetails> rootCategoryList) {
		this.rootCategoryList = rootCategoryList;
	}

	public String execute() throws Exception {
		
		//common code start's
		rootCategoryList = DB.RootCategoryList();	
		for(int i=0; i<rootCategoryList.size(); i++)
		{
			rootCategoryList.get(i).setSubCategoryList(DB.SubCategoryList(rootCategoryList.get(i).getCategoryid()));
		}
		//common code end's
		return "success";
	}
}
