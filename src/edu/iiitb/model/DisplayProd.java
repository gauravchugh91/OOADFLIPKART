package edu.iiitb.model;

public class DisplayProd
{
	String attname;
	String attvalue;
	@Override
	public String toString() {
		return "DisplayProd [attname=" + attname + ", attvalue=" + attvalue
				+ "]";
	}
	public String getAttname() {
		return attname;
	}
	public void setAttname(String attname) {
		this.attname = attname;
	}
	public String getAttvalue() {
		return attvalue;
	}
	public void setAttvalue(String attvalue) {
		this.attvalue = attvalue;
	}
	
}