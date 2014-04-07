package edu.iiitb.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




import edu.iiitb.dbconfig.DBConnection;
import edu.iiitb.model.Attribute;
import edu.iiitb.model.CardCredentials;
import edu.iiitb.model.Cart;
import edu.iiitb.model.CartItem;
import edu.iiitb.model.DeliveryAddress;
import edu.iiitb.model.DisplayProd;
import edu.iiitb.model.Order;
import edu.iiitb.model.Product;
import edu.iiitb.model.ProductEAV;
import edu.iiitb.model.UserWho;
import edu.iiitb.model.Product;
import edu.iiitb.model.ProductEAV;
import edu.iiitb.model.category;
import edu.iiitb.model.CategoryDetails;
import edu.iiitb.controller.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * 
 * @author Gaurav The following class contains set of functions that deals with
 *         fetching data from database and writing to it.
 */
public class DB {

	/**
	 * @author Gaurav The following function get the list of all the products
	 *         from the database their values as per Product Table in Database
	 * 
	 * @return
	 */

	ArrayList<Product> prodlist;

	public DB() {
		prodlist = new<Product> ArrayList();
		Product prod = new Product();

	}

	public static ArrayList<Product> getProducts() {
		Connection con;
		try {
			con = DBConnection.getDBConnection();
			String query = "select * from product";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			ArrayList<Product> products = new ArrayList<Product>();
			while (resultSet.next()) {
				Product product = new Product();
				product.setProductId(resultSet.getInt("productid"));
				product.setCategoryId(resultSet.getInt("categoryid"));
				product.setProductName(resultSet.getString("productname"));
				products.add(product);
			}
			con.close();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author Gaurav The following function receives a product ID as input and
	 *         fetches all the attributes for that product and returns a product
	 *         attribute collection as output
	 * 
	 * @param productId
	 * @return
	 */
	public static ArrayList<ProductEAV> getProductAttributes(int productId) {
		Connection con;
		try {
			con = DBConnection.getDBConnection();
			String query = "select * from producteav,attributes where productid=? and "
					+ "producteav.attributeid=attributes.attributeid";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ps.setInt(1, productId);
			ResultSet resultSet = ps.executeQuery();
			ArrayList<ProductEAV> productAttributes = new ArrayList<ProductEAV>();
			while (resultSet.next()) {
				ProductEAV producteav = new ProductEAV();
				producteav.setAttributeName(resultSet
						.getString("attributename"));
				producteav.setAttributeValue(resultSet
						.getString("attributevalue"));
				productAttributes.add(producteav);
			}
			con.close();
			return productAttributes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * @author Gaurav The following function receives a product ID as input and
	 *         the general details of that product.
	 * 
	 * @param productId
	 * @return
	 */
	public static Product getProduct(int productId) {
		Connection con;
		try {
			con = DBConnection.getDBConnection();
			String query = "select * from product where productid=?";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ps.setInt(1, productId);
			ResultSet resultSet = ps.executeQuery();
			Product product = null;
			if (resultSet.next()) {
				product = new Product();
				product.setProductId(resultSet.getInt("productid"));
				product.setCategoryId(resultSet.getInt("categoryid"));
				product.setProductName(resultSet.getString("productname"));
			}
			con.close();
			return product;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int createCart(int userId) {
		Connection con;
		int cartId = 0;
		try {
			con = DBConnection.getDBConnection();
			String query = "insert into cart(userid) values(?)";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(
					query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, userId);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				cartId = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartId;
	}

	public static void setCartItem(Product product, int cartId) {
		System.out.println(" cart id is : " + cartId);
		System.out.println(" product id is : " + product.getProductId());
		Connection con;
		int price = 0;
		int quantity = 1;
		int subtotal;
		String deliverydetails = "Will be delivered within next 4 days";
		// System.out.println(deliverydetails);
		for (int i = 0; i < product.getProductEAV().size(); i++) {
			if ((product.getProductEAV().get(i).getAttributeName())
					.equals("Price")) {
				price = Integer.parseInt(product.getProductEAV().get(i)
						.getAttributeValue());
			}
		}
		subtotal = price * quantity;

		try {
			con = DBConnection.getDBConnection();
			String query = "insert into cartitem(cartid,productid,quantity,price,subtotal,deliverydetails) values(?,?,?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ps.setInt(1, cartId);
			ps.setInt(2, product.getProductId());
			ps.setInt(3, quantity);
			ps.setInt(4, price);
			ps.setInt(5, subtotal);
			ps.setString(6, deliverydetails);
			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteCartItem(int productId, int cartId) {
		Connection con;
		System.out.println(productId);
		try {
			con = DBConnection.getDBConnection();
			String query = "delete from cartitem where cartid=? and productid=?";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ps.setInt(1, cartId);
			ps.setInt(2, productId);
			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<CartItem> getCartItems(int cartId) {
		Connection con;
		ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
		try {
			con = DBConnection.getDBConnection();
			String query = "select * from cartitem where cartid=?";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ps.setInt(1, cartId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				CartItem cartItem = new CartItem();
				cartItem.setPrice(resultSet.getInt("price"));
				cartItem.setProductId(resultSet.getInt("productid"));
				cartItem.setQuantity(resultSet.getInt("quantity"));
				cartItem.setSubtotal(resultSet.getInt("subtotal"));
				cartItems.add(cartItem);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartItems;
	}

	public static void setCart(int cartId, Cart currentCart) {
		Connection con;
		try {
			con = DBConnection.getDBConnection();
			String query = "update cart set userid= ? , totalamount=? , shipmentcharges=? where cartid=?";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ps.setInt(1, currentCart.getUserId());
			ps.setInt(2, currentCart.getTotalAmount());
			ps.setInt(3, currentCart.getShipmentCharges());
			ps.setInt(4, cartId);
			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean checkProduct(int productId, int cartId) {
		System.out.println(productId + "\t" + cartId);
		Connection con;
		int count = 0;
		try {
			con = DBConnection.getDBConnection();
			String query = "select count(*) as cnt from cartitem where productId=? and cartid=?";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ps.setInt(1, productId);
			ps.setInt(2, cartId);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getInt("cnt");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (count == 0)
			return false;
		else
			return true;
	}

	// ruchita's code starts

	public static ArrayList<CategoryDetails> RootCategoryList()
			throws Exception {
		Connection con;
		con = DBConnection.getDBConnection();
		String query = "select * from category where parentid=0";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
		ResultSet resultSet = ps.executeQuery();

		ArrayList<CategoryDetails> rootCategoryList = new ArrayList<CategoryDetails>();
		while (resultSet.next()) {
			CategoryDetails rootCategory = new CategoryDetails();
			rootCategory.setCategoryName(resultSet.getString("categoryname"));
			rootCategory.setCategoryid(resultSet.getInt("categoryid"));
			rootCategoryList.add(rootCategory);
		}
		return rootCategoryList;
	}

	public static ArrayList<CategoryDetails> SubCategoryList(int categoryid)
			throws Exception {
		Connection con;
		con = DBConnection.getDBConnection();
		// String query =
		// "select c2.categoryname from category c1, category c2 where c1.categoryid=c2.parentid and c1.categoryid="
		// + categoryid;
		String query = "select * from category where parentid=" + categoryid;
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
		ResultSet resultSet = ps.executeQuery();

		ArrayList<CategoryDetails> subCategoryList = new ArrayList<CategoryDetails>();
		while (resultSet.next()) {
			CategoryDetails subCategory = new CategoryDetails();
			subCategory.setCategoryid(resultSet.getInt("categoryid"));
			subCategory.setCategoryName(resultSet.getString("categoryname"));

			subCategoryList.add(subCategory);
		}
		return subCategoryList;
	}

	public static ArrayList<Product> Search(String searchName) throws Exception {
		Connection con;
		con = DBConnection.getDBConnection();
		ArrayList<Product> productList = new ArrayList<Product>();
		String query = "select * from product p where p.productname = '"
				+ searchName + "'";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
		ResultSet resultSet = ps.executeQuery();
		if (resultSet.next()) {
			Product product = null;
			do {
				product = new Product();
				product.setProductId(resultSet.getInt("productid"));
				product.setCategoryId(resultSet.getInt("categoryid"));
				product.setProductName(resultSet.getString("productname"));

				productList.add(product);
			} while (resultSet.next());
			con.close();
			return productList;
		} else {
			String query1 = "select * from product p, category c where c.categoryid = p.categoryid and c.categoryname = '"
					+ searchName + "'";
			PreparedStatement ps1 = (PreparedStatement) con
					.prepareStatement(query1);
			ResultSet resultSet1 = ps1.executeQuery();
			if (resultSet1.next()) {
				// Product product = null;
				do {
					Product product = new Product();
					product.setProductId(resultSet1.getInt("productid"));
					product.setCategoryId(resultSet1.getInt("categoryid"));
					product.setProductName(resultSet1.getString("productname"));

					productList.add(product);
				} while (resultSet1.next());
				con.close();
				return productList;
			} else {
				con.close();
				return productList;
			}
		}
	}

	public static ArrayList<Product> getProductsList(int category) {
		Connection con;
		try {
			con = DBConnection.getDBConnection();
			String query = "select * from product p, category c where c.categoryid = p.categoryid and c.categoryid =  "
					+ category;
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			ArrayList<Product> products = new ArrayList<Product>();
			while (resultSet.next()) {
				Product product = new Product();
				product.setProductId(resultSet.getInt("productid"));
				product.setCategoryId(resultSet.getInt("categoryid"));
				product.setProductName(resultSet.getString("productname"));
				products.add(product);
			}
			con.close();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ruchita's code end

	public static UserWho whoIsLogin(String selectionModifier) {
		Connection con;
		try {
			con = DBConnection.getDBConnection();
			String query = "select * from usercredentials " + selectionModifier;
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);

			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				UserWho user = new UserWho();
				user.setUserID(resultSet.getInt(1));
				user.setEmail(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setIsAdmin(resultSet.getInt(4));
				if (resultSet.getInt(5) == 0) {
					String sql = "UPDATE usercredentials "
							+ "SET isactivated = 1 WHERE userid = "
							+ user.getUserID();
					PreparedStatement ps1 = (PreparedStatement) con
							.prepareStatement(sql);
					ps1.executeUpdate();
					user.setIsActive(1);
				}

				else
					user.setIsActive((resultSet.getInt(4)));
				System.out.println(user);
				con.close();
				return user;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static String isEmailValid(String email, String password) {

		if (email == " " || email == null
				|| email.indexOf('@') < email.indexOf('.')) {
			return "Please specify a VALID Email Address";
		} else if (password == null || password == " ") {
			return "Please specify a password";
		} else {
			return "Email/Password combination is wrong.";
		}
	}

	public static ArrayList<category> getCategories(int came) {
		Connection con;
		ArrayList<category> cats = new ArrayList<category>();
		try {
			con = DBConnection.getDBConnection();
			String query = "select * from category where parentid=" + came;
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				category cat = new category();
				cat.setName(resultSet.getString("categoryname"));
				cat.setId(resultSet.getInt("categoryid"));
				cat.setParentid(resultSet.getInt("parentid"));
				cat.setLevel(resultSet.getInt("level"));
				cats.add(cat);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cats;

	}
	public static ArrayList<Order> getOrderList() {
		Connection con;
		ArrayList<Order> orderList = new ArrayList<Order>();
		try {
			con = DBConnection.getDBConnection();
			String query = "select * from flipkart.order";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Order o  =  new Order();
				o.setOrderid(resultSet.getInt("orderid"));
				o.setAddressid(resultSet.getInt("addressid"));
				o.setNoofItems(resultSet.getInt("numberofitems"));
				o.setShipmentcharges(resultSet.getInt("shipmentcharges"));
				o.setTotalamount(resultSet.getInt("totalamount"));
				o.setUserid(resultSet.getInt("userid"));
				o.setOrderdate(resultSet.getDate("orderdate"));
				orderList.add(o);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;

	}

	public static ArrayList<String> getProducts(int came) {
		Connection con;
		ArrayList<String> products = new ArrayList<String>();
		try {
			con = DBConnection.getDBConnection();
			String query = "select productname from product where categoryid="
					+ came;
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				products.add(resultSet.getString("productname"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("array list " + products);
		return products;

	}

	public static ArrayList<Attribute> getAttributes() {
		Connection con;
		ArrayList<Attribute> atts = new ArrayList<Attribute>();
		try {
			con = DBConnection.getDBConnection();
			String query = "select * from attributes";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Attribute att = new Attribute();
				att.setAttrName(resultSet.getString("attributename"));
				att.setAttrId(resultSet.getInt("attributeid"));
				atts.add(att);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return atts;

	}

	public static int alreadyExists(String tablename,String field,String value) {
		Connection con;
		int exists=0;
		try {
			con = DBConnection.getDBConnection();
			String query = "select 1 from "+tablename+ " where "+field+ " ='"+value+"'";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				exists = resultSet.getInt("1");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return exists;

	}
	public static int insertCategory(String cat, int parent) {
		Connection con;
		int resultSet = 0;
		try {
			con = DBConnection.getDBConnection();
			int level = DB.CheckLevel(parent);
			int exists = alreadyExists("category","categoryname",cat);
			if(exists==0) {
			if (level == 1) {
				int inslevel = 2;
				
				
				String query = "insert into category(categoryname,parentid,level) values('"
						+ cat + "'," + parent + "," + inslevel + ")";
				PreparedStatement ps = (PreparedStatement) con
						.prepareStatement(query);
				resultSet = ps.executeUpdate();
				
			} else {
				
				String query = "insert into category(categoryname,parentid,level) values('"
						+ cat + "'," + parent + ",1)";
				PreparedStatement ps = (PreparedStatement) con
						.prepareStatement(query);
				resultSet = ps.executeUpdate();
			}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	public static int insertProduct(String prodname,String catid,ArrayList<String> atts,ArrayList<String> attnames) {
		Connection con;
		int resultSet = 0;
		int prodid=0;
		int exists = DB.alreadyExists("product", "productname", prodname);
		if(exists==0) {
		try {
			con = DBConnection.getDBConnection();
			String insertProduct = "insert into product(productname,categoryid) values('"+prodname+"',"+catid+")";
			//System.out.println("inserting prod:"+insertProduct);
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(insertProduct);
		    resultSet = ps.executeUpdate();
		    String getid = "select productid from product where productname = '"+prodname+"' and categoryid="+catid;
		   // System.out.println("getting product id"+ getid);
		    PreparedStatement ps2 = (PreparedStatement) con
					.prepareStatement(getid);
		    ResultSet get = ps2.executeQuery();
			while (get.next()) {

				prodid = get.getInt("productid");

			}
			int i=0;
			for(String attvalue:atts) 
			{
				String name = attnames.get(++i);
				int id = getAttributeId(name);
				
				String query = "insert into producteav(productid,attributeid,attributevalue) values("+prodid+","+id+","+attvalue+")";
				//System.out.println("inserting into eav"+query);
				PreparedStatement ps3 = (PreparedStatement) con
						.prepareStatement(query);
			    resultSet = ps3.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		return resultSet;
	}

	public static int getAttributeId(String came) {
		Connection con;
		int returnval = 0;

		try {
			con = DBConnection.getDBConnection();
			String query = "select attributeid from attributes where attributename='"+came+"'";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {

				returnval = (resultSet.getInt("attributeid"));

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnval;

	}

	public static int CheckLevel(int came) {
		Connection con;
		int returnval = 0;

		try {
			con = DBConnection.getDBConnection();
			String query = "select level from category where categoryid="
					+ came;
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {

				returnval = (resultSet.getInt("level"));

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (came == 0)
			returnval = 1;
		return returnval;

	}
	public static ArrayList<DisplayProd> getThisProd(String prodname) {
		Connection con;
	    ArrayList<DisplayProd> sendingThis = new ArrayList<DisplayProd>();
		int attid=0;
		String attname;
		try {
			con = DBConnection.getDBConnection();
			String query = "select attributeid,attributename from attributes";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				 DisplayProd p= new DisplayProd();
				 attid= (resultSet.getInt("attributeid"));
				 attname = resultSet.getString("attributename");
				 String getvalue = "select attributevalue from producteav where productid=(select productid from product where productname='"+prodname+"') and attributeid="+attid;
				 ps = (PreparedStatement) con
							.prepareStatement(getvalue);
				 ResultSet rs = ps.executeQuery();
				 String attval;
				 while(rs.next()) {
					 attval = rs.getString("attributevalue");
				     p.setAttname(attname);
				     p.setAttvalue(attval);
				     sendingThis.add(p);
				 }
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sendingThis;

	}
/******************** Chirag Saraiya *************************************/
	public static void AddDeliveryAddress(int userid, String name,
			String address, String city, String state, String country,
			int pincode, String email, int phone) {
		userid = 11;
		email = "test@gmail.com";
		Connection con;
		try {
			con = DBConnection.getDBConnection();
			/*
			 * since user id is a foreign key which refers to customer there for
			 * we have to fill customer table first
			 */
			String query1 = "insert into customer (userid,email) values (?,?)";
			PreparedStatement ps1 = (PreparedStatement) con
					.prepareStatement(query1);
			ps1.setInt(1, userid);
			ps1.setString(2, email);

			ps1.executeUpdate();

			String query2 = "insert into address (userid,name,address,city,state,country,pincode,email,phone) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query2);
			ps.setInt(1, userid);
			ps.setString(2, name);
			ps.setString(3, address);
			ps.setString(4, city);
			ps.setString(5, state);
			ps.setString(6, country);
			ps.setInt(7, pincode);
			ps.setString(8, email);
			ps.setInt(9, phone);

			ps.executeUpdate();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static String checkUserCredential(int cardNumber, int expireMonth,
			int expireYear, int cvcCode, String cardName) {
		Connection con;
		try {
			CardCredentials cc = new CardCredentials();
			con = DBConnection.getDBConnection();
			/*
			 * since user id is a foreign key which refers to customer there for
			 * we have to fill customer table first
			 */
			String query1 = "select ownername,cardno,cardcvv,cardexpyear,cardexpmonth,cardtype,debitbalance from bankcards where bankcards.cardno=?";
			PreparedStatement ps1 = (PreparedStatement) con
					.prepareStatement(query1);
			ps1.setInt(1, cardNumber);
			ResultSet resultSet = ps1.executeQuery();
			if (resultSet.next()) {
				cc.setCardName(resultSet.getString("ownername"));
				cc.setCardNumber(resultSet.getInt("cardno"));
				cc.setCvcCode(resultSet.getInt("cardcvv"));
				cc.setExpireYear(resultSet.getInt("cardexpyear"));
				cc.setExpireMonth(resultSet.getInt("cardexpmonth"));
				cc.setCardType(resultSet.getString("cardtype"));
				cc.setDebitBalance(resultSet.getInt("debitbalance"));

			} else
				return "failure";

			System.out.println(cc.cardName + " " + cc.cardNumber + " "
					+ cc.cvcCode + " " + cc.expireMonth + " " + cc.expireYear);
			System.out.println(cardName + " " + cardNumber + " " + cvcCode
					+ " " + expireMonth + " " + expireYear);
			con.close();

			if ((cc.cardName).equals(cardName) && (cc.cardNumber == cardNumber)
					&& (cc.expireMonth == expireMonth)
					&& (cc.expireYear == expireYear) && (cc.cvcCode == cvcCode))
				return "success";
			else
				return "failure";

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}

		return "success";
	}

	public static void getBankName(ArrayList<String> bank) {

		Connection con;
		try {
			con = DBConnection.getDBConnection();

			String query1 = "select bankname from bank ";
			PreparedStatement ps1 = (PreparedStatement) con
					.prepareStatement(query1);

			ResultSet resultSet = ps1.executeQuery();
			if (resultSet.next()) {
				String temp;
				temp = resultSet.getString(1);
				bank.add(temp);
				System.out.println(temp);

			}

			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	public static void getAddress(ArrayList<DeliveryAddress> addr)
	{
		System.out.println("Addresss.....");
		Connection con;
		try {
			con = DBConnection.getDBConnection();
		int userid=1; //temp variable..need to get it from session
			String query1="select name,address,city,state,country,pincode,email,phone from address where address.userid=? ";
			PreparedStatement ps1 = (PreparedStatement) con.prepareStatement(query1);
			ps1.setInt(1, userid);
	
			
			ResultSet resultSet = ps1.executeQuery();
			if (resultSet.next()) {
				DeliveryAddress d=new DeliveryAddress();
				
				d.setName(resultSet.getString("name"));
				d.setAddress(resultSet.getString("address"));
				d.setCity(resultSet.getString("city"));
				d.setState(resultSet.getString("state"));
				d.setCountry(resultSet.getString("country"));
				d.setPincode(resultSet.getInt("pincode"));
				d.setEmail(resultSet.getString("email"));
				d.setPhone(resultSet.getInt("phone"));
				
				
				addr.add(d);
				
			}
		
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		
	}
		}
	
/**********************************************************************************/	
	public static UserWho whoIsLogin(String email, String password) {
		Connection con;
		UserWho user = new UserWho();
		try {
			con = DBConnection.getDBConnection();
			if (password != null) {
				String query = "select * from usercredentials " + " where "
						+ "email = '" + email + "'" + " and " + "password = '"
						+ password + "'";
				PreparedStatement ps = (PreparedStatement) con
						.prepareStatement(query);

				ResultSet resultSet = ps.executeQuery();

				if (resultSet.next()) {
					System.out.print("*******here****");

					user.setUserID(resultSet.getInt(1));
					user.setEmail(resultSet.getString(2));
					user.setPassword(resultSet.getString(3));
					user.setIsAdmin(resultSet.getInt(4));
					if (resultSet.getInt(5) == 0) {
						String sql = "UPDATE usercredentials "
								+ "SET isactivated = 1 WHERE userid = "
								+ user.getUserID();
						PreparedStatement ps1 = (PreparedStatement) con
								.prepareStatement(sql);
						ps1.executeUpdate();
						user.setIsActive(1);
					}

					// else

					// user.setIsActive((resultSet.getInt(4)));
					System.out.println(user);
					con.close();
					return user;
				} else
					return null;
			}/*
			 * else { String query="Insert into customer (email) values ("
			 * +email+");"; PreparedStatement ps = (PreparedStatement) con
			 * .prepareStatement(query); ps.executeUpdate(); }
			 */
			return user;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void WishlistInsert(int userid) {
		String str;
		DBConnection connect = new DBConnection();
		Connection con;
		try {
			con = connect.getDBConnection();

			str = "insert into wishlist (userid) values(" + userid + ")";
			PreparedStatement pr1 = (PreparedStatement) con
					.prepareStatement(str);
			pr1.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void WishlistItemInsert(int productid, int userid) throws Exception {
		String str1;
		String str2;
		String str3;
		int count = 0;

		int wishlistid = 0;
		DBConnection connect = new DBConnection();
		Connection con = connect.getDBConnection();
		System.out.println("test1");
		str1 = "select wishlistid from wishlist where userid=" + userid;
		PreparedStatement pr2 = (PreparedStatement) con.prepareStatement(str1);
		ResultSet result = pr2.executeQuery();
		System.out.println("test2");
		while (result.next()) {
			wishlistid = result.getInt("wishlistid");
		}

		str2 = "select count(*) from wishlistitem where productid=" + productid
				+ " and wishlistid=" + wishlistid;
		PreparedStatement pr4 = (PreparedStatement) con.prepareStatement(str2);
		ResultSet result1 = pr4.executeQuery();
		System.out.println("before selection");
		while (result1.next()) {
			count = result1.getInt("count(*)");
		}
		System.out.println("after selection::::" + count);
		if (count == 0) {
			str3 = "insert into wishlistitem (wishlistid,productid) values("
					+ wishlistid + "," + productid + ")";
			PreparedStatement pr3 = (PreparedStatement) con
					.prepareStatement(str3);
			pr3.executeUpdate();
			System.out.println("test3");
		}

	}

	public ArrayList DisplayWishlist(int userid) {
		String str;
		String str1 = null;
		int i = 1;
		int count = 0;
		ArrayList<String> attributename = new<String> ArrayList();
		ArrayList<String> attributevalues = new<String> ArrayList();
		Product prod = new Product();
		try {
			DBConnection connect = new DBConnection();
			Connection con = connect.getDBConnection();
			str = "select product.productid,productname,attributename,attributevalue from attributes,producteav,product "
					+ "where product.productid=producteav.productid and producteav.attributeid=attributes.attributeid and "
					+ "product.productid in (select productid from wishlistitem,wishlist where wishlist.wishlistid=wishlistitem.wishlistid and wishlist.userid="
					+ userid + ")order by productname";
			PreparedStatement pr = (PreparedStatement) con
					.prepareStatement(str);
			ResultSet result = pr.executeQuery();
			while (result.next()) {
				count++;

			}
			result.beforeFirst();
			while (result.next()) {

				if (i == 1) {
					str1 = result.getString("productname");
					i = 0;
				}
				if (str1.equalsIgnoreCase(result.getString("productname"))) {

					prod.setProductId(result.getInt("productid"));
					prod.setProductName(result.getString("productname"));
					prod.getProdinfo().put(result.getString("attributename"),
							result.getString("attributevalue"));

				} else {
					prodlist.add(prod);
					prod = new Product();
					str1 = result.getString("productname");
					prod.setProductName(result.getString("productname"));
					prod.getProdinfo().put(result.getString("attributename"),
							result.getString("attributevalue"));
				}

			}
			if (count != 0) {
				prodlist.add(prod);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prodlist;

	}

	public void RemovefromWishlist(int productid, int userid) {

		String str;
		DBConnection connect = new DBConnection();
		try {
			Connection con = connect.getDBConnection();
			System.out.println("before deletion");
			str = "delete from wishlistitem where productid="
					+ productid
					+ " and wishlistid=(select wishlistid from wishlist where userid="
					+ userid + ")";
			PreparedStatement pr = (PreparedStatement) con
					.prepareStatement(str);
			pr.executeUpdate();
			System.out.println("hello deleted");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
