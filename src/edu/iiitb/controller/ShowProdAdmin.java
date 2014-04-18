package edu.iiitb.controller;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;
import edu.iiitb.model.DisplayProd;
import edu.iiitb.model.NavigationItem;

public class ShowProdAdmin extends ActionSupport {

	
	String prod_name;
	ArrayList<NavigationItem> navigation ;
	int catid;
	ArrayList<DisplayProd> dispProd;
	
	public ArrayList<NavigationItem> getNavigation() {
		return navigation;
	}
	public void setNavigation(ArrayList<NavigationItem> navigation) {
		this.navigation = navigation;
	}
	public ArrayList<DisplayProd> getDispProd() {
		return dispProd;
	}
	public void setDispProd(ArrayList<DisplayProd> dispProd) {
		this.dispProd = dispProd;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	
	public int getCatid() {
		return catid;
	}
	public void setCatid(int catid) {
		this.catid = catid;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("For Prod:"+prod_name);
		dispProd = new ArrayList<DisplayProd>();
		dispProd = DB.getThisProd(prod_name,catid);
		System.out.println(dispProd);
		navigation =  new ArrayList<NavigationItem>();
		DB.setNavigation(navigation,catid);
		System.out.println("my category is :"+catid);
		return "success";
	}

}
