package edu.iiitb.model;

public class PersonalInfoModel {

	private String lname;
	private String fname;
	private String gender;
	int phone;
	
	
	
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getPhone() {
		return phone;
	}
//	public void setPhone(int phone) {
//		this.phone = phone;
//	}
	
	
	
}
