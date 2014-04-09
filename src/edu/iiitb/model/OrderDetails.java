package edu.iiitb.model;

public class OrderDetails {

	int orderid;
	int userid;
	int totalamount;
	int shipmentcharges;
	int numberofitems;
	String orderdate;
	int addressid;
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(int totalamount) {
		this.totalamount = totalamount;
	}
	public int getShipmentcharges() {
		return shipmentcharges;
	}
	public void setShipmentcharges(int shipmentcharges) {
		this.shipmentcharges = shipmentcharges;
	}
	public int getNumberofitems() {
		return numberofitems;
	}
	public void setNumberofitems(int numberofitems) {
		this.numberofitems = numberofitems;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public int getAddressid() {
		return addressid;
	}
	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}
	
	
}
