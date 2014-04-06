package edu.iiitb.controller;

import java.util.ArrayList;

import edu.iiitb.database.DB;
import edu.iiitb.model.CategoryDetails;
import edu.iiitb.model.Product;

public class Search {
	
	//Common Code Start's
	ArrayList<CategoryDetails> rootCategoryList =new ArrayList<CategoryDetails>();
		
		public ArrayList<CategoryDetails> getRootCategoryList() {
			return rootCategoryList;
		}

		public void setRootCategoryList(ArrayList<CategoryDetails> rootCategoryList) {
			this.rootCategoryList = rootCategoryList;
		}
		
		//Common Code End's 
		
		ArrayList<CategoryDetails> subCategoryList;
		public ArrayList<CategoryDetails> getSubCategoryList() {
			return subCategoryList;
		}

		public void setSubCategoryList(ArrayList<CategoryDetails> subCategoryList) {
			this.subCategoryList = subCategoryList;
		}
		
		ArrayList<Product> products;

		public ArrayList<Product> getProducts() {
			return products;
		}

		public void setProducts(ArrayList<Product> products) {
			this.products = products;
		}
	
		String searchName;
		
	public String getSearchName() {
			return searchName;
		}

		public void setSearchName(String searchName) {
			this.searchName = searchName;
		}
		
		String searchBar;

	public String getSearchBar() {
			return searchBar;
		}

		public void setSearchBar(String searchBar) {
			this.searchBar = searchBar;
		}

	public String execute() throws Exception {
				
		//common code start's
		rootCategoryList = DB.RootCategoryList();	
		for(int i=0; i<rootCategoryList.size(); i++)
		{
			rootCategoryList.get(i).setSubCategoryList(DB.SubCategoryList(rootCategoryList.get(i).getCategoryid()));
		}
		//common code end's
		
		subCategoryList = DB.SubCategoryList(0);
		
		products = new ArrayList<Product>();
		//System.out.println(searchBar+searchName);
		
		products = DB.Search(searchBar);
		for (int i = 0; i < products.size(); i++) {
			products.get(i).setProductEAV(
					DB.getProductAttributes(products.get(i).getProductId()));
		}
		
		
		return "success";
	}

}
