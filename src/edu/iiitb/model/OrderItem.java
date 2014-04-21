package edu.iiitb.model;

// bean class for orderitem table
//Author:Chirag Saraiya

//totalamount is included in this for Wallet

import java.util.ArrayList;
import java.util.Date;

public class OrderItem {
	
 int orderitemid;
 int orderid;
 int productid;
 int quantity;
 int price;
 String orderstatus;
 Date deliverydate;
 Date returndate;
 int subtotal;
 String imagepath;
 String productname;
 DeliveryAddress da;
 String deliverydetails;
 int totalamount;
 
 public int getTotalamount() {
	return totalamount;
}
public void setTotalamount(int totalamount) {
	this.totalamount = totalamount;
}
ArrayList<String> validStatuses; 
 
 public ArrayList<String> getValidStatuses() {
	return validStatuses;
}
public void setValidStatuses(ArrayList<String> validStatuses) {
	this.validStatuses = validStatuses;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return super.toString();
}
public String getDeliverydetails() {
	return deliverydetails;
}
public void setDeliverydetails(String deliverydetails) {
	this.deliverydetails = deliverydetails;
}
public String getProductname() {
	return productname;
}
public void setProductname(String productname) {
	this.productname = productname;
}

 public String getImagepath() {
	return imagepath;
}
public void setImagepath(String imagepath) {
	this.imagepath = imagepath;
}

 
 public int getOrderitemid() {
	return orderitemid;
}
public void setOrderitemid(int orderitemid) {
	this.orderitemid = orderitemid;
}
public int getOrderid() {
	return orderid;
}
public void setOrderid(int orderid) {
	this.orderid = orderid;
}
public int getProductid() {
	return productid;
}
public void setProductid(int productid) {
	this.productid = productid;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getOrderstatus() {
	return orderstatus;
}
public void setOrderstatus(String orderstatus) {
	this.orderstatus = orderstatus;
}
public Date getDeliverydate() {
	return deliverydate;
}
public void setDeliverydate(Date deliverydate) {
	this.deliverydate = deliverydate;
}
public Date getReturndate() {
	return returndate;
}
public void setReturndate(Date returndate) {
	this.returndate = returndate;
}
public int getSubtotal() {
	return subtotal;
}
public void setSubtotal(int subtotal) {
	this.subtotal = subtotal;
}
public DeliveryAddress getDa() {
	return da;
}
public void setDa(DeliveryAddress da) {
	this.da = da;
}

 
 
}
