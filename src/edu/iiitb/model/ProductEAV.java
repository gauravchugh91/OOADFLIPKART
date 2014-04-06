package edu.iiitb.model;

/**
 * 
 * @author Gaurav
 * The following is a Model/Bean file for Each of the Product attribute
 *
 */

public class ProductEAV {
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeId) {
		this.attributeValue = attributeId;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	String attributeValue;
	String attributeName;
}
