package edu.iiitb.model;

import java.util.ArrayList;

public class CategoryDetails {

	int categoryid;
	String categoryName;
	ArrayList<CategoryDetails> subCategoryList;
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public ArrayList<CategoryDetails> getSubCategoryList() {
		return subCategoryList;
	}
	public void setSubCategoryList(ArrayList<CategoryDetails> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}
}
