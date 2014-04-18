package edu.iiitb.model;

import java.math.BigDecimal;

public class CardCredentials {
	
	BigDecimal cardNumber;	
	public int expireMonth;
	public int expireYear;
	public int cvcCode;
	public String cardName;
	public int debitBalance;
	public int creditLimit;
	
	public String cardType;
	
	public String password;

public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

public BigDecimal getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(BigDecimal cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
