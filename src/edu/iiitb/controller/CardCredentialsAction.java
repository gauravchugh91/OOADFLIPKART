package edu.iiitb.controller;

import java.math.BigDecimal;

import edu.iiitb.database.DB;

//chirag 

//Use for Credit card and Debit Card both
public class CardCredentialsAction {
    BigDecimal cardNumber;
	int expireMonth;
	int expireYear;
	int cvcCode;
	String cardName;
	int bankcardid;
String cardType;
	
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


	public int getBankcardid() {
		return bankcardid;
	}
	public void setBankcardid(int bankcardid) {
		this.bankcardid = bankcardid;
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

	public String execute() throws Exception {
		//Map<String, Object> sessionMap = ActionContext.getContext()
		//	.getSession();
		//if (sessionMap.containsKey("userid")) {
		System.out.println("Debit card:::");
		cardType="debit";
		bankcardid=DB.checkUserCardCredential(cardNumber,expireMonth,expireYear,cvcCode,cardName,cardType);
		System.out.println(bankcardid);
		if(bankcardid==0)
			return "failure";
		else
			return "success";
	}
	
	public String creditCard() throws Exception {
		//Map<String, Object> sessionMap = ActionContext.getContext()
		//	.getSession();
		//if (sessionMap.containsKey("userid")) {
	cardType="credit";
		System.out.println("Credit card:::");
		bankcardid=DB.checkUserCardCredential(cardNumber,expireMonth,expireYear,cvcCode,cardName,cardType);
		if(bankcardid==0)
			return "failure";
		else
			return "success";
	}

}
