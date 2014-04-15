package edu.iiitb.controller;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import edu.iiitb.database.DB;
import edu.iiitb.model.CategoryDetails;
import edu.iiitb.model.Product;

public class MainPage implements SessionAware {

	ArrayList<CategoryDetails> rootCategoryList = new ArrayList<CategoryDetails>();
	private SessionMap<String, Object> sessionMap;
	ArrayList<Product> electronicsList = new ArrayList<Product>();
	ArrayList<Product> fashionList = new ArrayList<Product>();
	ArrayList<Product> booksList = new ArrayList<Product>();

	public ArrayList<CategoryDetails> getRootCategoryList() {
		return rootCategoryList;
	}

	public void setRootCategoryList(ArrayList<CategoryDetails> rootCategoryList) {
		this.rootCategoryList = rootCategoryList;
	}

	public ArrayList<Product> getElectronicsList() {
		return electronicsList;
	}

	public void setElectronicsList(ArrayList<Product> electronicsList) {
		this.electronicsList = electronicsList;
	}

	public ArrayList<Product> getFashionList() {
		return fashionList;
	}

	public void setFashionList(ArrayList<Product> fashionList) {
		this.fashionList = fashionList;
	}

	public ArrayList<Product> getBooksList() {
		return booksList;
	}

	public void setBooksList(ArrayList<Product> booksList) {
		this.booksList = booksList;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

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

		electronicsList = DB.Search("electronics");
		for (int i = 0; i < electronicsList.size(); i++) {
			electronicsList.get(i).setProductEAV(
					DB.getProductAttributes(electronicsList.get(i)
							.getProductId()));
		}

		fashionList = DB.Search("apparel");
		for (int i = 0; i < fashionList.size(); i++) {
			fashionList.get(i).setProductEAV(
					DB.getProductAttributes(fashionList.get(i).getProductId()));
		}

		booksList = DB.Search("books");
		for (int i = 0; i < booksList.size(); i++) {
			booksList.get(i).setProductEAV(
					DB.getProductAttributes(booksList.get(i).getProductId()));
		}

		// Start of Session Code

		// Check if user is logged in or not
		if (!(sessionMap.containsKey("userID"))) {
			System.out.println("\nUser id is not present\n");
			sessionMap.put("userID", -1);
		}

		// implies user is logged in
		if ((int) sessionMap.get("userID") != -1) {
			System.out.println("\nUser id is not -1\n");
			int userId = (int) sessionMap.get("userID");

			// retrieve cartid from database if it exists
			int cartId = DB.getCartId(userId);

			// if not in database then create a new one
			if (cartId == 0) {
				System.out.println("\nUser id is not in DB\n");
				cartId = DB.createCart(userId);
			}
			sessionMap.put("cartid", cartId);
		}

		// implies user is not logged in i.e userID = -1
		else {
			System.out.println("\nUser id is -1, so new cart\n");
			if (!(sessionMap.containsKey("cartid"))) {
				int cartid = DB.createCart(-1);
				sessionMap.put("cartid", cartid);
			}
		}

		// End of Session Code
		return "success";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionMap = (SessionMap) arg0;

	}
}
