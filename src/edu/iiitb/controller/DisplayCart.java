package edu.iiitb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import edu.iiitb.database.DB;
import edu.iiitb.model.Cart;
import edu.iiitb.model.CartItem;
import edu.iiitb.model.Product;

public class DisplayCart {

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public ArrayList<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(ArrayList<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public String getProds() {
		return prods;
	}

	public void setProds(String prods) {
		this.prods = prods;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	public Cart getCurrentCart() {
		return currentCart;
	}

	public void setCurrentCart(Cart currentCart) {
		this.currentCart = currentCart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	Product product;
	ArrayList<Product> productList;
	String prods;
	int numberOfItems;
	int productId;
	ArrayList<CartItem> cartItems;
	Cart currentCart;
	int totalAmount = 0;
	int price=0;
	int quantity;

	/**
	 * @return
	 * @throws Exception
	 */

	public String displayCart() throws Exception {
		Map<String, Object> sessionMap = ActionContext.getContext()
				.getSession();
		int cartId = (int) sessionMap.get("cartid");
		productList = new ArrayList<Product>();
		cartItems = new ArrayList<CartItem>();
		currentCart = new Cart();

		cartItems = DB.getCartItems(cartId);
		for (int i = 0; i < cartItems.size(); i++) {
			Product prod = new Product();
			prod = DB.getProduct(cartItems.get(i).getProductId());
			totalAmount += cartItems.get(i).getSubtotal();
			productList.add(prod);
		}
		System.out.println("main yaha pe hu");
		for(int i=0;i<productList.size();i++)
		{
			Product prd= new Product();
			prd= productList.get(i);
			prd.setProductEAV(DB.getProductAttributes(prd.getProductId()));
			//System.out.println("main isko dekhunga" + prd.getProductEAV().size());
			for(int j=0;j<prd.getProductEAV().size();j++)
			{
				
				if((prd.getProductEAV().get(j).getAttributeName()).equals("Image_Path")){
					prd.setImagePath((prd.getProductEAV().get(j).getAttributeValue()));
					System.out.println("image path is " + prd.getImagePath());
				}
				//System.out.println(prd.getProductEAV().get(j).getAttributeName());
			}
		}
		currentCart.setCartId(cartId);
		currentCart.setUserId((int) sessionMap.get("userID"));
		currentCart.setShipmentCharges(200);
		currentCart.setTotalAmount(totalAmount);
		DB.setCart(cartId, currentCart);
		numberOfItems = cartItems.size();
		sessionMap.put("noOfItems", numberOfItems);

		// System.out.println("number of elements are : " + cartItems.size());
		return "success";
	}

	public String addDisplayCart() throws Exception {
		Map<String, Object> sessionMap = ActionContext.getContext()
				.getSession();
		System.out.println("lalalalalal");
		int cartId = (int) sessionMap.get("cartid");
		product = new Product();
		productList = new ArrayList<Product>();
		cartItems = new ArrayList<CartItem>();
		currentCart = new Cart();

		product = DB.getProduct(productId);
		product.setProductEAV(DB.getProductAttributes(productId));

		System.out.println("I'm here also in cart");
		if (!(DB.checkProduct(productId, cartId))) {
			System.out.println("Inside it");
			DB.setCartItem(product, cartId);
		}

		cartItems = DB.getCartItems(cartId);
		for (int i = 0; i < cartItems.size(); i++) {
			Product prod = new Product();
			prod = DB.getProduct(cartItems.get(i).getProductId());
			totalAmount += cartItems.get(i).getSubtotal();
			productList.add(prod);
		}

		
System.out.println("main yaha pe hu");
		for(int i=0;i<productList.size();i++)
		{
			Product prd= new Product();
			prd= productList.get(i);
			prd.setProductEAV(DB.getProductAttributes(prd.getProductId()));
			//System.out.println("main isko dekhunga" + prd.getProductEAV().size());
			for(int j=0;j<prd.getProductEAV().size();j++)
			{
				
				if((prd.getProductEAV().get(j).getAttributeName()).equals("Image_Path")){
					prd.setImagePath((prd.getProductEAV().get(j).getAttributeValue()));
					System.out.println("image path is " + prd.getImagePath());
				}
				//System.out.println(prd.getProductEAV().get(j).getAttributeName());
			}
		}
		System.out.println("current In");
		currentCart.setCartId(cartId);
		currentCart.setUserId((int) sessionMap.get("userID"));
		currentCart.setShipmentCharges(200);
		currentCart.setTotalAmount(totalAmount);
		System.out.println("before set cart");
		DB.setCart(cartId, currentCart);
		numberOfItems = cartItems.size();
		System.out.println("current out");
		sessionMap.put("noOfItems", numberOfItems);
		System.out.println("length is " + numberOfItems);
		System.out.println("ab main yaha se nikal raha hu");
		return "success";
	}

	public String deleteDisplayCart() throws Exception {
		Map<String, Object> sessionMap = ActionContext.getContext()
				.getSession();
		int cartId = (int) sessionMap.get("cartid");
		cartItems = new ArrayList<CartItem>();
		productList = new ArrayList<Product>();
		currentCart = new Cart();

		DB.deleteCartItem(productId, cartId);

		cartItems = DB.getCartItems(cartId);

		for (int i = 0; i < cartItems.size(); i++) {
			Product prod = new Product();
			prod = DB.getProduct(cartItems.get(i).getProductId());
			totalAmount += cartItems.get(i).getSubtotal();
			productList.add(prod);
		}
		
		System.out.println("main yaha pe hu");
		for(int i=0;i<productList.size();i++)
		{
			Product prd= new Product();
			prd= productList.get(i);
			prd.setProductEAV(DB.getProductAttributes(prd.getProductId()));
			//System.out.println("main isko dekhunga" + prd.getProductEAV().size());
			for(int j=0;j<prd.getProductEAV().size();j++)
			{
				
				if((prd.getProductEAV().get(j).getAttributeName()).equals("Image_Path")){
					prd.setImagePath((prd.getProductEAV().get(j).getAttributeValue()));
					System.out.println("image path is " + prd.getImagePath());
				}
				//System.out.println(prd.getProductEAV().get(j).getAttributeName());
			}
		}
		
		currentCart.setCartId(cartId);
		currentCart.setUserId((int) sessionMap.get("userID"));
		currentCart.setShipmentCharges(200);
		currentCart.setTotalAmount(totalAmount);
		DB.setCart(cartId, currentCart);
		numberOfItems = cartItems.size();
		sessionMap.put("noOfItems", numberOfItems);
		System.out.println("number of items after deletion " + numberOfItems);
		return "success";
	}

	public String editDisplayCart() throws Exception {
		Map<String, Object> sessionMap = ActionContext.getContext()
				.getSession();
		String [] str;
		int minFree;
		int minQuantity;
		int count=1;
		int price = 0;
		int temp;
		int tempQuantity=0;
		int cartId = (int) sessionMap.get("cartid");
		cartItems = new ArrayList<CartItem>();
		productList = new ArrayList<Product>();
		currentCart = new Cart();	
     
        System.out.println(" Ankesh Test 1 quantity is " + quantity);
        System.out.println("hello ankesh sharma");
        product = new Product(); 
        System.out.println("ankesh product id"+productId);
        product = DB.getProduct(productId);
		product.setProductEAV(DB.getProductAttributes(productId));
		System.out.println("ankesh Eav size:"+product.getProductEAV().size());
	
        for (int i = 0; i < product.getProductEAV().size(); i++) {
		if(product.getProductEAV().get(i).getAttributeName().equals("Offer"))
		{
			str = product.getProductEAV().get(i).getAttributeValue().split(" ");
			minQuantity= Integer.parseInt(str[1]);
			minFree=Integer.parseInt(str[3]);
			temp=minQuantity;
			System.out.println("Ankesh test2");
			while(temp < quantity)
			{
				temp=temp+minQuantity;
				count++;
				System.out.println("Ankesh test3 count"+count);
				
			}
			if(temp==quantity)
			{
				tempQuantity=quantity;
				System.out.println("ankesh check tempQuantity:"+tempQuantity);
				quantity= quantity+(count*minFree);
			}
			else
			{
				count--;
				System.out.println("ankesh check tempQuantity:"+tempQuantity);
				tempQuantity=quantity;
				quantity = quantity+(count*minFree);
			}
		
		}
		
		}
		cartItems = DB.getCartItems(cartId);
		Iterator itr = cartItems.iterator();
		while(itr.hasNext())
		{
			CartItem item = (CartItem) itr.next();
			if(item.getProductId()==productId)
			{
				price= item.getPrice();
			}
		}
		

		
		DB.editCartItem(productId,price, quantity,tempQuantity, cartId);
		System.out.println("ankesh test 4");
		cartItems = DB.getCartItems(cartId);

		for (int i = 0; i < cartItems.size(); i++) {
			Product prod = new Product();
			System.out.println("ankesh test 5");
			prod = DB.getProduct(cartItems.get(i).getProductId());
			totalAmount += cartItems.get(i).getSubtotal();
			productList.add(prod);
		}
		
		System.out.println("main yaha pe hu");
		for(int i=0;i<productList.size();i++)
		{
			Product prd= new Product();
			prd= productList.get(i);
			prd.setProductEAV(DB.getProductAttributes(prd.getProductId()));
			//System.out.println("main isko dekhunga" + prd.getProductEAV().size());
			for(int j=0;j<prd.getProductEAV().size();j++)
			{
				
				if((prd.getProductEAV().get(j).getAttributeName()).equals("Image_Path")){
					prd.setImagePath((prd.getProductEAV().get(j).getAttributeValue()));
					System.out.println("image path is " + prd.getImagePath());
				}
				//System.out.println(prd.getProductEAV().get(j).getAttributeName());
			}
		}
		
		
		currentCart.setCartId(cartId);
		currentCart.setUserId((int) sessionMap.get("userID"));
		currentCart.setShipmentCharges(200);
		currentCart.setTotalAmount(totalAmount);
		DB.setCart(cartId, currentCart);
		numberOfItems = cartItems.size();
		sessionMap.put("noOfItems", numberOfItems);
		return "success";
	}
	}
