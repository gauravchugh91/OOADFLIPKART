package edu.iiitb.controller;

import edu.iiitb.database.DB;

//chirag 

//Use for Credit card and Debit Card both
public class CardCredentialsAction {
	int cardNumber;	
	int expireMonth;
	int expireYear;
	int cvcCode;
	String cardName;
	
	

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

public String execute() throws Exception {
	//Map<String, Object> sessionMap = ActionContext.getContext()
		//	.getSession();
	//if (sessionMap.containsKey("userid")) {
		
		String result=DB.checkUserCardCredential(cardNumber,expireMonth,expireYear,cvcCode,cardName);
		System.out.println(result);
		return result;
	//} else
	//	return "error";
}

/*public void validate()
{
   if (cardNumber)
   {
      addFieldError("cardNumber","The name is required");
   }
   if (age < 28 || age > 65)
   {
      addFieldError("age","Age must be in between 28 and 65");
   }
}*/




}
