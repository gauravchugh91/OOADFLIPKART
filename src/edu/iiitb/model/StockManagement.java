package edu.iiitb.model;

public class StockManagement {
	
	@Override
	public String toString() {
		return "StockManagement [productname=" + productname + ", stock="
				+ stock + ", threshold=" + threshold + ", categoryid="
				+ categoryid + "]";
	}
	String productname;
	int stock;
	int threshold;
	int categoryid;
	
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	

}
