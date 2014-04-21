package edu.iiitb.controller;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.database.DB;
import edu.iiitb.model.CategoryDetails;

public class UpdateEmail {

	private String new_email;
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


	public String getNew_email() {
		return new_email;
	}

	public void setNew_email(String new_email) {
		this.new_email = new_email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String navigate() {
		System.out.println("Update email now.");
		status=2;
		return "success";
	}

	public String update() throws Exception {
		
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
			if (LoginAction.isEmailNotValid(getNew_email(), "abc")==null) {
				
				String query = "update usercredentials set email = '"
						+ getNew_email() + "' where userid="
						+ Integer.parseInt(sessionMap.get("userID").toString());
				
				setStatus(DB.updateAccount(query));
			 
				sessionMap.put("email", getNew_email());
				return "success";
			}
		}
		return "error";

	}
}
