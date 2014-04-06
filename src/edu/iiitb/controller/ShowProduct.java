package edu.iiitb.controller;

import java.util.ArrayList;

import edu.iiitb.database.DB;
import edu.iiitb.model.CategoryDetails;
import edu.iiitb.model.Product;

public class ShowProduct {
	
	//Common Code Start's
	ArrayList<CategoryDetails> rootCategoryList =new ArrayList<CategoryDetails>();
		
		public ArrayList<CategoryDetails> getRootCategoryList() {
			return rootCategoryList;
		}

		public void setRootCategoryList(ArrayList<CategoryDetails> rootCategoryList) {
			this.rootCategoryList = rootCategoryList;
		}
		
		//Common Code End's 
	
	int productId;
	Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	ArrayList<CategoryDetails> subCategoryList;
	public ArrayList<CategoryDetails> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(ArrayList<CategoryDetails> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}
	
	/**
	 * Author : Gaurav The following function gets a particular product from
	 * database based on Product it along with all its attributes
	 * 
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		
		//common code start's
				rootCategoryList = DB.RootCategoryList();	
				for(int i=0; i<rootCategoryList.size(); i++)
				{
					rootCategoryList.get(i).setSubCategoryList(DB.SubCategoryList(rootCategoryList.get(i).getCategoryid()));
				}
				//common code end's
		
		System.out.println("Product id is : " + productId);
		product = DB.getProduct(productId);
		product.setProductEAV(DB.getProductAttributes(productId));
		subCategoryList = DB.SubCategoryList(0);
		return "success";
	}
}
