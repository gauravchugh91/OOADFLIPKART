package edu.iiitb.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.database.DB;
import edu.iiitb.model.CategoryDetails;
import edu.iiitb.model.NavigationItem;
import edu.iiitb.model.Product;
import edu.iiitb.model.ProductEAV;

public class ShowProduct {
	
	int productId;
	Product product;
	String islogin="false";
	int check=1; // it is used to check the whether stock is 0 or not in Product.jsp page
	
	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public String getIslogin() {
		return islogin;
	}

	public void setIslogin(String islogin) {
		this.islogin = islogin;
	}

	//Common Code Start's
	ArrayList<CategoryDetails> rootCategoryList =new ArrayList<CategoryDetails>();
		
		public ArrayList<CategoryDetails> getRootCategoryList() {
			return rootCategoryList;
		}

		public void setRootCategoryList(ArrayList<CategoryDetails> rootCategoryList) {
			this.rootCategoryList = rootCategoryList;
		}
		
		//Common Code End's 
	


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	ArrayList<CategoryDetails> subCategoryList;
	public ArrayList<CategoryDetails> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(ArrayList<CategoryDetails> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}
	
	//product path start's
		ArrayList<NavigationItem> productPath = new ArrayList<NavigationItem>();
		public ArrayList<NavigationItem> getProductPath() {
			return productPath;
		}

		public void setProductPath(ArrayList<NavigationItem> productPath) {
			this.productPath = productPath;
		}
		//product path end's


	/**
	 * Author : Gaurav The following function gets a particular product from
	 * database based on Product it along with all its attributes
	 * 
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		
		Map<String,Object> session = ActionContext.getContext().getSession(); 
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
		
		System.out.println("Product id is : " + productId);
		if(session.get("userID")==null)
			islogin="false";
		else
			islogin="true";
		product = DB.getProduct(productId);
		product.setProductEAV(DB.getProductAttributes(productId));
		Iterator itr= product.getProductEAV().iterator();
		while(itr.hasNext())
		{
			ProductEAV producteav= (ProductEAV)itr.next();
			
			if(producteav.getAttributeName().equals("Discount"))
			{
				
				int discount=Integer.parseInt(producteav.getAttributeValue());
				Iterator itr1= product.getProductEAV().iterator();
				while(itr1.hasNext())
				{
					ProductEAV producteav1= (ProductEAV)itr1.next();
					
					if(producteav1.getAttributeName().equals("Price"))
					{
						
						int price=Integer.parseInt(producteav1.getAttributeValue());
						System.out.println("disount:"+discount);
						System.out.println("Price:"+price);
						int discountPrice=price-((price*discount)/100);
						System.out.println("disount Price:"+discountPrice);
						product.setDiscountPrice(discountPrice);
						
					}
				}
			}
			else if(producteav.getAttributeName().equals("Threshold"))
			{
							
							int thershold= Integer.parseInt(producteav.getAttributeValue());
							System.out.println("threshold"+thershold);
							Iterator itr1= product.getProductEAV().iterator();
							while(itr1.hasNext())
							{
								ProductEAV producteav1= (ProductEAV)itr1.next();
								
								if(producteav1.getAttributeName().equals("Stock"))
								{
									if(Integer.parseInt(producteav1.getAttributeValue())<thershold)
									{
										check=0;	
									}
								}
							}
						}

			
		}
		
		
		DB.setNavigation(productPath, product.getCategoryId());
		
		//System.out.println("Number of attributes are : " +  product.getProductEAV().size());
		subCategoryList = DB.SubCategoryList(0);
		return "success";
	}
}
