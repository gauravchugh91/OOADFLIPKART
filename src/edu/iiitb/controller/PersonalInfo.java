package edu.iiitb.controller;

import java.util.ArrayList;
import java.util.Map;

import edu.iiitb.database.DB;
import edu.iiitb.model.CategoryDetails;
import edu.iiitb.model.PersonalInfoModel;

import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;

public class PersonalInfo {
	
	private int userid;
	private String fname;
	private String lname;
	public int  phone;
	
	// Common Code Start's
		ArrayList<CategoryDetails> rootCategoryList = new ArrayList<CategoryDetails>();

		public ArrayList<CategoryDetails> getRootCategoryList() {
			return rootCategoryList;
		}

		public void setRootCategoryList(ArrayList<CategoryDetails> rootCategoryList) {
			this.rootCategoryList = rootCategoryList;
		}

		// Common Code End's
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}

	private String gender;
	
	int status;
	private PersonalInfoModel pi; 
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public PersonalInfoModel getPi() {
		return pi;
	}
	public void setPi(PersonalInfoModel pi) {
		this.pi = pi;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	/*public int getPhone() {
		return phone;
	}
	public void setPhone(int i) {
		this.phone = i;
	}*/
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String select() throws Exception {
		
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
		
		if (sessionMap.containsKey("userID")) {
			pi = DB.getPersonalInfo(Integer.parseInt(sessionMap.get(
					"userID").toString()));
		    setFname(pi.getFname());
		    setLname(pi.getLname());
		    setGender(pi.getGender());
		    setPhone(pi.getPhone());
		    System.out.println("phone number is " + getPhone());
      }
		return "success";
		
	}
	
	public String update() {
		
		Map<String, Object> sessionMap = ActionContext.getContext()
				.getSession();
		setStatus(0);
		if (sessionMap.containsKey("userID")) {
						
			userid = Integer.parseInt(sessionMap.get(
					"userID").toString());
			System.out.println(getPhone());
			//Integer str=Integer.valueOf(getPhone());
			//System.out.println("phone no to integer"+str);
			String query = "update customer set fname = '"+getFname()+"',lname = '"+getLname()+"',phone="+
	               phone+", gender = '"+getGender()+"' where userid = "+userid;
			System.out.println("updating customer:"+query);
			setStatus(DB.updateAccount(query));
			return "success"; 
		}
		return "error";
	}

}
