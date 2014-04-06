package edu.iiitb.model;

/**
 * 
 * @author Gaurav
 * The following is a Model/Bean file for the Cart Table
 *
 */
public class Cart {
	int cartId;
	int userId;
	int totalAmount;
	int shipmentCharges;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getShipmentCharges() {
		return shipmentCharges;
	}
	public void setShipmentCharges(int shipmentCharges) {
		this.shipmentCharges = shipmentCharges;
	}
}
