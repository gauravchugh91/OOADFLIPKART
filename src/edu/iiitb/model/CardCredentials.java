package edu.iiitb.model;

public class CardCredentials {
	
	public int cardNumber;	
	public int expireMonth;
	public int expireYear;
	public int cvcCode;
	public String cardName;
	public int debitBalance;
	public String cardType;
	

public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
public int getCardNumber() {
	return cardNumber;
}
public void setCardNumber(int cardNumber) {
	this.cardNumber = cardNumber;
}
public int getExpireMonth() {
	return expireMonth;
}
public void setExpireMonth(int expireMonth) {
	this.expireMonth = expireMonth;
}
public int getExpireYear() {
	return expireYear;
}
public void setExpireYear(int expireYear) {
	this.expireYear = expireYear;
}
public int getCvcCode() {
	return cvcCode;
}
public void setCvcCode(int cvcCode) {
	this.cvcCode = cvcCode;
}
public String getCardName() {
	return cardName;
}
public void setCardName(String cardName) {
	this.cardName = cardName;
}
public int getDebitBalance() {
	return debitBalance;
}
public void setDebitBalance(int debitBalance) {
	this.debitBalance = debitBalance;
}

}
