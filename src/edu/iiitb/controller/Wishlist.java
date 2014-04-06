package edu.iiitb.controller;

import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DB;
import edu.iiitb.model.Product;
import edu.iiitb.model.WishlistProd;

public class Wishlist extends ActionSupport {

	String message;

	int userid;
	int count_prod = 0;
	int Productid;

	public int getProductid() {
		return Productid;
	}

	public void setProductid(int productid) {
		Productid = productid;
	}

	int prodId;
	ArrayList<Product> prodlist = new<Product> ArrayList();
	ArrayList<WishlistProd> Wishlist = new<WishlistProd> ArrayList();

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public int getCount_prod() {
		return count_prod;
	}

	public void setCount_prod(int count_prod) {
		this.count_prod = count_prod;
	}

	public ArrayList<WishlistProd> getWishlist() {
		return Wishlist;
	}

	public void setWishlist(ArrayList<WishlistProd> wishlist) {
		Wishlist = wishlist;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public ArrayList<Product> getProdlist() {
		return prodlist;
	}

	public void setProdlist(ArrayList<Product> prodlist) {
		this.prodlist = prodlist;
	}

	public String WishlistInsert() {
		DB connect = new DB();
		connect.WishlistInsert(1);
		return "success";

	}

	public String execute() {
		System.out.println("Able to reach this piece of code");
		return "success";

	}

	public String WishlistItemInsert() {

		DB connect = new DB();
		try {
			System.out.println("Ankesh_product_id:" + prodId);
			connect.WishlistItemInsert(prodId, 1);
			message = "<a class='change1' id='redirect' href='displaywishlist'>Added to Wishlist</a>";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";

	}

	public String DisplayWishlist() {
		DB connect = new DB();
		prodlist = connect.DisplayWishlist(1);
		Iterator itr1 = prodlist.iterator();
		while (itr1.hasNext()) {
			WishlistProd wish_prod = new WishlistProd();
			Product prod = (Product) itr1.next();
			wish_prod.setProductid(prod.getProductId());
			wish_prod.setProductname(prod.getProductName());
			Set<Map.Entry<String, String>> set = prod.getProdinfo().entrySet();
			String attrvalue;
			Iterator itr2 = set.iterator();
			while (itr2.hasNext()) {
				Entry<String, String> me = (Entry<String, String>) itr2.next();
				if (me.getKey().equalsIgnoreCase("Price"))
					wish_prod.setPrice(me.getValue());
				else if (me.getKey().equalsIgnoreCase("General_Desc"))
					wish_prod.setGen_desc(me.getValue());
				else if (me.getKey().equalsIgnoreCase("Image_Path"))
					wish_prod.setImage_path(me.getValue());

			}
			count_prod++;
			System.out.print("count:" + count_prod);
			System.out.println(wish_prod.getProductname());
			System.out.println(wish_prod.getPrice());
			System.out.println(wish_prod.getGen_desc());
			System.out.println("*********************");
			Wishlist.add(wish_prod);

		}

		return "success";
	}

	public String removeWishlistProd() {

		DB connect = new DB();

		connect.RemovefromWishlist(Productid, 1);
		System.out.println("check prod" + Productid);
		String str = DisplayWishlist();

		return str;

	}

}
