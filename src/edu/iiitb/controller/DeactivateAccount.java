package edu.iiitb.controller;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.database.DB;
import edu.iiitb.model.CategoryDetails;

public class DeactivateAccount {

	private String password;
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

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String navigate() {
		//System.out.println("Deactivate");
		status=2;
		return "success";
	}
	
	public String deactivate() throws Exception {
		
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
		
		status=0;

		if (sessionMap.containsKey("userID")) {
			String query = "update usercredentials set isactivated = 0 where userid="
					+ Integer.parseInt(sessionMap.get("userID").toString());
			status= DB.updateAccount(query);
			if(status==1) {
				sessionMap.put("userID",-1);
				sessionMap.remove("email");
				sessionMap.remove("cartid");
			}
			return "success";
		}
		return "error";

	}
	
}
