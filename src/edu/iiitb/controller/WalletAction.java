package edu.iiitb.controller;

import java.util.ArrayList;
import java.util.Map;

import edu.iiitb.database.DB;
import edu.iiitb.model.CategoryDetails;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class WalletAction extends ActionSupport {

	public int userid;
	public int walletamount;
	
	public int getWalletamount() {
		return walletamount;
	}
	public void setWalletamount(int walletamount) {
		this.walletamount = walletamount;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	// Common Code Start's
		ArrayList<CategoryDetails> rootCategoryList = new ArrayList<CategoryDetails>();

		public ArrayList<CategoryDetails> getRootCategoryList() {
			return rootCategoryList;
		}

		public void setRootCategoryList(ArrayList<CategoryDetails> rootCategoryList) {
			this.rootCategoryList = rootCategoryList;
		}

		// Common Code End's

	
	public String execute() throws Exception{
		
		// common code start's
		ArrayList<CategoryDetails> rootCategoryList2 = DB.RootCategoryList();
		rootCategoryList = rootCategoryList2;
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
		if(sessionMap.containsKey("userID")) {
		
		System.out.println("****************************************herre*********");
		walletamount = DB.getamount(Integer.parseInt(sessionMap.get("userID").toString()));
		System.out.print(getUserid());
		
		System.out.println(walletamount+"in action****");
		return "success";
		}
		return "success";
		
	}
	
	
}
