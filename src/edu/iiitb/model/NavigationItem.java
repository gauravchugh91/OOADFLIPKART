package edu.iiitb.model;

public class NavigationItem {

int category_id;
String catNavName;



@Override
public String toString() {
	return "NavigationItem [category_id=" + category_id + ", catNavName="
			+ catNavName + "]";
}
public int getCategory_id() {
	return category_id;
}
public void setCategory_id(int category_id) {
	this.category_id = category_id;
}
public String getCatNavName() {
	return catNavName;
}
public void setCatNavName(String catNavName) {
	this.catNavName = catNavName;
}
}
