package edu.iiitb.controller;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import edu.iiitb.database.DB;
import edu.iiitb.model.CategoryDetails;
import edu.iiitb.model.Product;

public class ShowProductList implements SessionAware {

	private SessionMap<String, Object> sessionMap;
	ArrayList<Product> products;
	int category;

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	// static int count;

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
		}
		// common code end's

		// User not logged in
		//System.out.println("User logged in"+sessionMap.get("userid").toString());
	    sessionMap.put("userid", -1);
         
		// User Logged in
		// sessionMap.put("userid", 1);
		if (!(sessionMap.get("userid").equals(-1))) {
			if (!(sessionMap.containsKey("cartid"))) {
				// insert cart row into the cart table, get cart id and then put
				// it in session.
				int cartid = DB.createCart((int) (sessionMap.get("userid")));
				sessionMap.put("cartid", cartid);
			}
			// fetch the cart id corresponding to the user id on login or
			// remember me
			// if cart id corresponding to the user doesn't exist then create a
			// new one(only for signup this will happen)
		}

		// Not a logged in user, then first check cookies or cart id
		// If cookies hold cart id use it!!
		// If not then create a new cart id and store it in cookies
		else {
			if (!(sessionMap.containsKey("cartid"))) {
				// insert cart row into the cart table
				int cartid = DB.createCart((int) (sessionMap.get("userid")));
				sessionMap.put("cartid", cartid);
			}
		}

		products = new ArrayList<Product>();
		products = DB.getProducts();
		for (int i = 0; i < products.size(); i++) {
			products.get(i).setProductEAV(
					DB.getProductAttributes(products.get(i).getProductId()));
		}

		subCategoryList = DB.SubCategoryList(0);
		return "success";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionMap = (SessionMap) arg0;

	}
}
