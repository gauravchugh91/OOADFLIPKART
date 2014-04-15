package edu.iiitb.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Gaurav
 * The following is a Model/Bean file for the Product Table
 * Contains an member productEAV which contains all attribute names and values for a particular product
 *
 */

public class Product {
	
	
	int productId;
	int categoryId;
	String productName;
	ArrayList<ProductEAV> productEAV;

	String imagePath;

	HashMap<String,String> prodinfo= new HashMap<String,String>();


	public HashMap<String, String> getProdinfo() {
		return prodinfo;
	}
	public void setProdinfo(HashMap<String, String> prodinfo) {
		this.prodinfo = prodinfo;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public ArrayList<ProductEAV> getProductEAV() {
		return productEAV;
	}
	public void setProductEAV(ArrayList<ProductEAV> productEAV) {
		this.productEAV = productEAV;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
