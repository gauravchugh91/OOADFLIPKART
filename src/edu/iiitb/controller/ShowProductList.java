package edu.iiitb.controller;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import edu.iiitb.database.DB;
import edu.iiitb.model.CategoryDetails;
import edu.iiitb.model.Product;

public class ShowProductList{

	ArrayList<Product> products;
	int category;
	//rishi's changing part!!!!!!!!!!!!
	private String email;
	private int isLoggedIn;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(int isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	// Common Code Start's
	ArrayList<CategoryDetails> rootCategoryList = new ArrayList<CategoryDetails>();

	public ArrayList<CategoryDetails> getRootCategoryList() {
		return rootCategoryList;
	}

	public void setRootCategoryList(ArrayList<CategoryDetails> rootCategoryList) {
		this.rootCategoryList = rootCategoryList;
	}

	// Common Code End's

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	ArrayList<CategoryDetails> subCategoryList;

	public ArrayList<CategoryDetails> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(ArrayList<CategoryDetails> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

	/**
	 * Author : Gaurav The following function gets all the products from the
	 * database along with all their attributes.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		// common code start's
				rootCategoryList = DB.RootCategoryList();
				for (int i = 0; i < rootCategoryList.size(); i++) {
					rootCategoryList.get(i)
							.setSubCategoryList(
									DB.SubCategoryList(rootCategoryList.get(i)
											.getCategoryid()));
					
					for (int j = 0; j < rootCategoryList.get(i).getSubCategoryList().size(); j++) {
							rootCategoryList.get(i).getSubCategoryList().get(j).setSubCategoryList(DB.SubCategoryList(rootCategoryList.get(i).getSubCategoryList().get(j).getCategoryid()));
					}
					
				}
				// common code end's

		products = new ArrayList<Product>();
		products = DB.getProductsList(category);
		for (int i = 0; i < products.size(); i++) {
			products.get(i).setProductEAV(
					DB.getProductAttributes(products.get(i).getProductId()));
		}

		subCategoryList = DB.SubCategoryList(0);
		return "success";
	}

}
