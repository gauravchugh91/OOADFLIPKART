package edu.iiitb.controller;

import java.util.ArrayList;
import java.util.Map;

import edu.iiitb.database.DB;
import edu.iiitb.model.CategoryDetails;
import edu.iiitb.model.DeliveryAddress;

import com.opensymphony.xwork2.ActionContext;

public class AddAccountAddress {

	private String address;
	private String city;
	private String state;
	private String country;
	private DeliveryAddress accountAddress;
	private String name;
	private int pincode;
	int phone;
	
	int status;
	
	// Common Code Start's
	 	ArrayList<CategoryDetails> rootCategoryList = new ArrayList<CategoryDetails>();

	 	public ArrayList<CategoryDetails> getRootCategoryList() {
	 		return rootCategoryList;
	 	}

	 	public void setRootCategoryList(ArrayList<CategoryDetails> rootCategoryList) {
	 		this.rootCategoryList = rootCategoryList;
	 	}

	 	// Common Code End's

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}


	public DeliveryAddress getAccountAddress() {
		return accountAddress;
	}

	public void setAccountAddress(DeliveryAddress accountAddress) {
		this.accountAddress = accountAddress;
	}

	public String showAccAddress() {

		Map<String, Object> sessionMap = ActionContext.getContext()
				.getSession();

		if (sessionMap.containsKey("userID")) {
			
			name = DB.selectAccountHolder(Integer.parseInt(sessionMap.get(
					"userID").toString()));

			//accountAddress.setEmail((String) sessionMap.get("email"));
			accountAddress = DB.getAccountAddress(Integer.parseInt(sessionMap
					.get("userID").toString()));
			
			setAddress(accountAddress.getAddress());
			setCity(accountAddress.getCity());
			setState(accountAddress.getState());
			setCountry(accountAddress.getCountry());
			setPincode(accountAddress.getPincode());
			setPhone(accountAddress.getPhone());
			System.out.println("city: "+getCity());

			return "success";
		}
		return "error";
	}

	public String updateAccAddress() throws Exception {
		
		// common code start's
				rootCategoryList = DB.RootCategoryList();
				for (int i = 0; i < rootCategoryList.size(); i++) {
					rootCategoryList.get(i)
							.setSubCategoryList(
									DB.SubCategoryList(rootCategoryList.get(i)
											.getCategoryid()));
					
					for (int j = 0; j < rootCategoryList.get(i).getSubCategoryList().size(); j++) {
							rootCategoryList.get(i).getSubCategoryList().get(j).setSubCategoryList(DB.SubCategoryList(rootCategoryList.get(i).getSubCategoryList().get(j).getCategoryid()));
					}
					
				}
				// common code end's

		Map<String, Object> sessionMap = ActionContext.getContext()
				.getSession();
		
		setStatus(0);

		if (sessionMap.containsKey("userID")) {
			if (getAddress() != null && getCity() != null && getState() != null
					&& getCountry() != null ) {
				
				
				//accountAddress.setEmail((String) sessionMap.get("email"));
				//email = accountAddress.getEmail();

				String query = "UPDATE customer " + "SET address ='"
						+ getAddress() + "', city = '" + getCity() + "'"
						+ ", state='" + getState() + "', country='"
						+ getCountry() + "', pincode=" + getPincode()
						+ ", phone=" + getPhone() + " WHERE userid = "
						+ Integer.parseInt(sessionMap.get(
								"userID").toString());
				
				name = DB.selectAccountHolder(Integer.parseInt(sessionMap.get(
						"userID").toString()));
				setStatus(DB.updateAccount(query));
				
				DB.AddDeliveryAddress(Integer.parseInt(sessionMap.get(
						"userID").toString()), name,
						getAddress(), getCity(), getState(), getCountry(),
						getPincode(),(String) sessionMap.get("email"),phone);
				System.out.println("updating address:"+query);
				return "success";
			}
		}
		return "error";
	}

	
}
