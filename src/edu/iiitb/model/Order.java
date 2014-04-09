package edu.iiitb.model;

import java.util.Date;

public class Order {
  @Override
	public String toString() {
		return "Order [orderid=" + orderid + ", userid=" + userid
				+ ", totalamount=" + totalamount + ", shipmentcharges="
				+ shipmentcharges + ", noofItems=" + noofItems + ", orderdate="
				+ orderdate + ", addressid=" + addressid + "]";
	}
int orderid;
  int userid;
  int totalamount;
  int shipmentcharges;
  int noofItems;
  Date orderdate;
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
public int getNoofItems() {
	return noofItems;
}
public void setNoofItems(int noofItems) {
	this.noofItems = noofItems;
}
public Date getOrderdate() {
	return orderdate;
}
public void setOrderdate(Date orderdate) {
	this.orderdate = orderdate;
}
public int getAddressid() {
	return addressid;
}
public void setAddressid(int addressid) {
	this.addressid = addressid;
}
}
