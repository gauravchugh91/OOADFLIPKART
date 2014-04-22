package edu.iiitb.database;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.iiitb.dbconfig.DBConnection;
import edu.iiitb.model.Attribute;
import edu.iiitb.model.CardCredentials;
import edu.iiitb.model.Cart;
import edu.iiitb.model.CartItem;
import edu.iiitb.model.DeliveryAddress;
import edu.iiitb.model.DisplayProd;
import edu.iiitb.model.NavigationItem;
import edu.iiitb.model.Order;
import edu.iiitb.model.OrderItem;
import edu.iiitb.model.Product;
import edu.iiitb.model.ProductEAV;
import edu.iiitb.model.PersonalInfoModel;
import edu.iiitb.model.StockManagement;
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
	//pratyu start's
	
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
			System.out.println("nuumber of att " + productAttributes.size());
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

	public static int getCartId(int userId) {
		int cartId = 0;
		Connection con;
		try {
			con = DBConnection.getDBConnection();
			String query = "select cartid from cart where userid=?";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ps.setInt(1, userId);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				cartId = resultSet.getInt("cartid");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartId;
	}

	public static int createCart(int userId) {
		Connection con;
		int cartId = 0;
		int count=0;
		try {
			con = DBConnection.getDBConnection();
			/*String query1="select * from cart where userid="+userId;
			PreparedStatement pr2=(PreparedStatement) con.prepareStatement(query1);
			ResultSet result=pr2.executeQuery();
			while(result.next())
			{
				count=1;
			}
			
			if(count==0)
			{*/
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
		int discount=0;
		int discountPrice;
		String deliverydetails = "Will be delivered within next 4 days";
		// System.out.println(deliverydetails);
		for (int i = 0; i < product.getProductEAV().size(); i++) {
			if ((product.getProductEAV().get(i).getAttributeName())
					.equals("Price")) {
				price = Integer.parseInt(product.getProductEAV().get(i)
						.getAttributeValue());
			}
			
			else if(product.getProductEAV().get(i).getAttributeName().equals("Discount"))
			{
				discount = Integer.parseInt(product.getProductEAV().get(i)
						.getAttributeValue());
			}
			
		
		
		}
		if (discount==0)
		    subtotal = price * quantity;
		else
		{
			discountPrice= price-((price* discount)/100);
			subtotal = discountPrice* quantity;
			price=discountPrice;
		
		}
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

	public static void editCartItem(int productId,int price, int quantity,int tempQuantity, int cartId) {
		Connection con;
		int subtotal = 0;
		try {
			con = DBConnection.getDBConnection();
			if(tempQuantity!=0)
			{
				subtotal = price*tempQuantity;
				System.out.println("Ankesh check: subtotal"+subtotal);
			}
			else
				subtotal= price*quantity;
			String query = "update cartitem set quantity= ? , subtotal=? where cartid=? and productid=?";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ps.setInt(1, quantity);
			ps.setInt(2, subtotal);
			ps.setInt(3, cartId);
			ps.setInt(4, productId);
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
		String query = "select * from product p where p.productname like '"
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
			String query1 = "select * from product p, category c where c.categoryid = p.categoryid and c.categoryname like '"
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
				String query2 = "select * from product p, category c1, category c2 where c1.categoryid = p.categoryid and c2.categoryid = c1.parentid and c2.categoryname like '"
						+ searchName + "'";
				PreparedStatement ps2 = (PreparedStatement) con
						.prepareStatement(query2);
				ResultSet resultSet2 = ps2.executeQuery();
				if (resultSet2.next()) {

					do {
						Product product = new Product();
						product.setProductId(resultSet2.getInt("productid"));
						product.setProductName(resultSet2
								.getString("productname"));

						productList.add(product);
					} while (resultSet2.next());
					con.close();
					return productList;
				} else {
					String query3 = "select * from product p, category c1, category c2, category c3 where c1.categoryid = p.categoryid and c2.categoryid = c1.parentid and c3.categoryid = c2.parentid and c3.categoryname like '"
							+ searchName + "'";
					PreparedStatement ps3 = (PreparedStatement) con
							.prepareStatement(query3);
					ResultSet resultSet3 = ps3.executeQuery();
					if (resultSet3.next()) {

						do {
							Product product = new Product();
							product.setProductId(resultSet3.getInt("productid"));
							product.setProductName(resultSet3
									.getString("productname"));

							productList.add(product);
						} while (resultSet3.next());
						con.close();
						return productList;
					} else {
						con.close();
						return productList;
					}
				}
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
			if (resultSet.next()) {
				
				do{
				Product product = new Product();
				product.setProductId(resultSet.getInt("productid"));
				product.setCategoryId(resultSet.getInt("categoryid"));
				product.setProductName(resultSet.getString("productname"));
				products.add(product);
				}while(resultSet.next());
				
			}
			else
			{
				String query1 = "select * from product p, category c1,category c2 where c2.categoryid = c1.parentid  and c1.categoryid = p.categoryid and c2.categoryid =  "
						+ category;
				PreparedStatement ps1 = (PreparedStatement) con
						.prepareStatement(query1);
				ResultSet resultSet1 = ps1.executeQuery();
				if (resultSet1.next()) {
					
					do{
					Product product = new Product();
					product.setProductId(resultSet1.getInt("productid"));
					product.setCategoryId(resultSet1.getInt("categoryid"));
					product.setProductName(resultSet1.getString("productname"));
					products.add(product);
					}while(resultSet1.next());
					
				}
			}
			con.close();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	// ruchita's code end
	/********** RISHI **********/

	public static UserWho addNewAccount(String email, String password) {
		Connection con;
		System.out.println("email:" + email);
		try {
			int newId;

			con = DBConnection.getDBConnection();
			System.out.println("we r in addNewAccount");

			String prev_Account = "select userid from usercredentials where "
					+ "email='" + email + "'";

			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(prev_Account);
			ResultSet resultSet = ps.executeQuery();
			System.out.println("prev_Account successfully executed!");

			String prev_Customer = "select userid from customer where email='"
					+ email + "'";
			PreparedStatement cs = (PreparedStatement) con
					.prepareStatement(prev_Customer);
			ResultSet resultSet1 = cs.executeQuery();
			System.out.println("prev_Customer successfully executed!");

			// User has a flipkart account.

			if (resultSet.next()) {
				System.out.println("Email already taken by "
						+ resultSet.getInt(1));

				return null;
			}

			// user is a customer and has purchased something before but does
			// not have an account

			else if (resultSet1.next()) {
				newId = resultSet1.getInt(1);
				String loginTableInsert = "insert into usercredentials (userid,email,password)"
						+ " values ("
						+ newId
						+ ",'"
						+ email
						+ "','"
						+ password
						+ "')";
				PreparedStatement ps2 = (PreparedStatement) con
						.prepareStatement(loginTableInsert,
								Statement.RETURN_GENERATED_KEYS);

				ps2.executeUpdate();
				// ResultSet newUser = ps2.getGeneratedKeys();

				UserWho user = new UserWho();
				System.out.println("heyyyyyyyy!");
				user.setUserID(newId);
				System.out.println(user.getUserID());
				user.setEmail(email);
				System.out.println(user.getEmail());
				System.out.println("set user email:" + user.getEmail() + " "
						+ user.getUserID() + " " + user.getPassword());

				System.out.println("custTableInsert successfully executed!");
				return user;

			}

			// new user of the system-never shopped and does not have an
			// account.

			else {
				// inserting values first in customer table.
				String custTableInsert = "INSERT INTO customer (email) VALUES ('"
						+ email + "')";

				PreparedStatement ps1 = (PreparedStatement) con
						.prepareStatement(custTableInsert,
								Statement.RETURN_GENERATED_KEYS);
				ps1.executeUpdate();
				ResultSet usID = ps1.getGeneratedKeys();
				System.out.println("1st insert done! ");
				if (usID.next()) {
					newId = usID.getInt(1);
					System.out.println("value of usID: " + newId);

					String loginTableInsert = "insert into usercredentials (userid,email,password)"
							+ " values ("
							+ newId
							+ ",'"
							+ email
							+ "','"
							+ password + "')";
					PreparedStatement ps2 = (PreparedStatement) con
							.prepareStatement(loginTableInsert,
									Statement.RETURN_GENERATED_KEYS);

					ps2.executeUpdate();
					// ResultSet newUser = ps2.getGeneratedKeys();

					UserWho user = new UserWho();
					System.out.println("heyyyyyyyy!");
					user.setUserID(newId);
					System.out.println(user.getUserID());
					user.setEmail(email);
					System.out.println(user.getEmail());

					con.close();
					System.out
							.println("custTableInsert successfully executed!");
					return user;
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

		return null;
	}

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

	// forget password
	public static String seekPassword(String email) {
		Connection con;
		String password = null;
		try {
			con = DBConnection.getDBConnection();
			String query = "select password from usercredentials where email ='"
					+ email + "'";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				password = resultSet.getString(1);
				System.out.println("Password:" + password);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return password;

	}

	// .........Account->Add Address: Rishi's Part---------------

	public static DeliveryAddress getAccountAddress(int userid) {
		Connection con;
		DeliveryAddress acAddress = new DeliveryAddress();
		try {
			con = DBConnection.getDBConnection();

			String query = "select address,city,state,country,pincode,phone from customer "
					+ "where userid=" + userid;
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
           if(resultSet.next()) {
			acAddress.setAddress(resultSet.getString(1));
			acAddress.setCity(resultSet.getString(2));
			acAddress.setState(resultSet.getString(3));
			acAddress.setCountry(resultSet.getString(4));
			acAddress.setPincode(resultSet.getInt(5));
			acAddress.setPhone(resultSet.getInt(6));
			System.out.println("City in DB: "+acAddress.getCity() + acAddress.getPhone());
			
           }

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return acAddress;

	}

	public static String selectAccountHolder(int userid) {
		Connection con;
		String name = null;
		try {
			con = DBConnection.getDBConnection();
		String query = "select fname,lname from customer where userid="+userid;
		PreparedStatement ps = (PreparedStatement) con
				.prepareStatement(query);
		ResultSet resultSet = ps.executeQuery();
		if(resultSet.next()) {
		name = resultSet.getString("fname")+" "+resultSet.getString("lname");
		}		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return name;
		
	}

	public static int updateAccount(String query) {
		Connection con;
		try {
	        int rs = 0;
			con = DBConnection.getDBConnection();

			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			rs = ps.executeUpdate();
			return rs;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	// ............Account->Personal Info ------------------------------

	public static PersonalInfoModel getPersonalInfo(int userid) {
		Connection con;
		PersonalInfoModel p_info = new PersonalInfoModel();
		try {
			con = DBConnection.getDBConnection();

			String query = "select fname,lname,gender,phone from customer "
					+ "where userid=" + userid;
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				p_info.setFname(resultSet.getString(1));
				p_info.setLname(resultSet.getString(2));
				p_info.setGender(resultSet.getString(3));
				//Integer phone = resultSet.getInt(4);
				//p_info.setPhone(phone.toString());
				p_info.setPhone(resultSet.getInt(4));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p_info;

	}
	
	//................Change Password.................
	
	public static String correctPassword(String password,int userid) {
		Connection con;
		try {
			con=DBConnection.getDBConnection();
			String query="select password from usercredentials where userid ="+ userid;
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) {
			 String pass= resultSet.getString("password");
			 System.out.println("password:"+pass);
			 if(password.equals(pass)) {
				 return "correct";
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

			return "wrong";	
		
	}

	// End of Rishi's code!...................................................

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
			String query = "select * from flipkart3.order";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Order o = new Order();
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

	public static int alreadyExistsCat(String tablename, String field, String value,int parent) {
		Connection con;
		int exists = 0;
		try {
			con = DBConnection.getDBConnection();
			String query = "select 1 from " + tablename + " where " + field
					+ " ='" + value + "' and parentid="+parent;
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
	
	public static void setOrderItems(ArrayList<OrderItem> ordItms,int orderid) {
		Connection con;
		try {
			con = DBConnection.getDBConnection();
			String query = "select * from orderitem where orderid="+orderid;
				
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				OrderItem ord = new OrderItem();
				ord.setOrderid(rs.getInt("orderid"));
				ord.setOrderitemid(rs.getInt("orderitemid"));
				ord.setDeliverydate(rs.getDate("deliverydate"));
				ord.setOrderstatus(rs.getString("orderstatus"));
				ord.setPrice(rs.getInt("price"));
				ord.setProductid(rs.getInt("productid"));
				ord.setQuantity(rs.getInt("quantity"));
				ord.setSubtotal(rs.getInt("subtotal"));
				ArrayList<String> status = new ArrayList<String>();
				switch(ord.getOrderstatus())
				{
				case "Placed" :
				   status.add("Placed");
			       status.add("Packing");
			       status.add("Dispatched");
			       status.add("Delivered");
			       
			       break;
				case "Packing" :
					status.add("Packing");
					 status.add("Dispatched");
				       status.add("Delivered");
				       
				       break;
				case "Dispatched":
				status.add("Dispatched");
				 status.add("Delivered");
				 break;
				case "Delivered":
					status.add("Delivered");
					status.add("Return request");
					break;
				case "Return request" :
					status.add("Return request");
					status.add("Returned");
					break;
				case "Returned" :
					status.add("Returned");
					status.add("Order Closed");
					break;
				
					
				}
				ord.setValidStatuses(status);
				ordItms.add(ord);
			}
			con.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public static int alreadyExistsProd(String tablename, String field, String value,int cat) {
		Connection con;
		int exists = 0;
		try {
			con = DBConnection.getDBConnection();
			String query = "select 1 from " + tablename + " where " + field
					+ " ='" + value + "' and categoryid="+cat;
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
	public static void setOrderItemStatus(int id,String status) {
		Connection con;
		int exists = 0;
		try {
			con = DBConnection.getDBConnection();
			String query = "update orderitem set orderstatus='"+status+"' where orderitemid="+id;
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			int resultSet = ps.executeUpdate();
			System.out.println("updated :"+resultSet);
			if(resultSet==1 && status.equals("Returned"))
			{
				String walletQ = "select walletamount,w.userid  from wallet w,flipkart3.order o, orderitem m where w.userid=o.userid and o.orderid=m.orderid and m.orderitemid="+id;
				PreparedStatement ps2 = (PreparedStatement) con
						.prepareStatement(walletQ);
				ResultSet rs = ps2.executeQuery();
				if(rs.next())
				{
					
					System.out.println("Wallet amount is:"+rs.getInt("walletamount"));
					int walletamount = rs.getInt("walletamount");
					int userid = rs.getInt(2);
					String getSubtotal = "select subtotal from orderitem where orderitemid="+id;
					PreparedStatement ps3 = (PreparedStatement) con
							.prepareStatement(getSubtotal);
					ResultSet rs3 = ps3.executeQuery();
					if(rs3.next())
					{
						int subtotal = rs3.getInt("subtotal");
						subtotal =subtotal+walletamount;
						String updateW = "update wallet set walletamount="+subtotal+" where userid="+userid;
						System.out.println("trying to update wallet.."+updateW);
						PreparedStatement ps4 = (PreparedStatement) con
								.prepareStatement(updateW);
						int resultSet4 = ps4.executeUpdate();
						System.out.println("Update wallet as:"+resultSet4);
						
					}
					
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

		return;

	}
	public static void setStockManagement(ArrayList<StockManagement> products) {
		Connection con;
		int exists = 0;
		try {
			con = DBConnection.getDBConnection();
			String query = "select p.categoryid,p.productname,s.attributevalue as stock,t.attributevalue as threshold from product p,producteav s,producteav t where p.productid=s.productid and s.attributevalue<t.attributevalue and s.attributeid=8 and t.attributeid=9 and s.productid=t.productid";
			
			
			
			//select p.categoryid,p.productname,s.attributevalue as stock,t.attributevalue as threshold from product p,producteav s,producteav t where p.productid=s.productid and s.attributeid=8 and t.attributeid=9 and s.productid=t.productid and s.attributevalue<t.attributevalue";

			
			
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				StockManagement sm = new StockManagement();
				sm.setProductname(rs.getString(2));
				sm.setStock(rs.getInt(3));
				sm.setThreshold(rs.getInt(4));
			
				sm.setCategoryid(rs.getInt(1));
				if(sm.getStock()<sm.getThreshold()) 
				products.add(sm);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;

	}



	public static int insertCategory(String cat, int parent) {
		Connection con;
		int resultSet = 0;
		try {
			con = DBConnection.getDBConnection();
			System.out.println("parent for this is :"+parent);
			int level = DB.CheckLevel(parent);
			System.out.println("level of parent :"+level);
			int exists = alreadyExistsCat("category", "categoryname", cat,parent);
			if (exists == 0) {
				if (level == 1) {
					int inslevel = 2;

					String query = "insert into category(categoryname,parentid,level) values('"
							+ cat + "'," + parent + "," + inslevel + ")";
					PreparedStatement ps = (PreparedStatement) con
							.prepareStatement(query);
					resultSet = ps.executeUpdate();

				} else if(level==2) {
					
					
					String query = "insert into category(categoryname,parentid,level) values('"
							+ cat + "'," + parent + ",3)";
					PreparedStatement ps = (PreparedStatement) con
							.prepareStatement(query);
					resultSet = ps.executeUpdate();
				}
				else {
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


	public static int insertProduct(String prodname, String catid,
			ArrayList<String> atts, ArrayList<String> attnames) {
		Connection con;
		int resultSet = 0;
		int prodid = 0;
		int exists = DB.alreadyExistsProd("product", "productname", prodname,Integer.parseInt(catid));
		if (exists == 0) {
			
			try {
				con = DBConnection.getDBConnection();
				String insertProduct = "insert into product(productname,categoryid) values('"
						+ prodname + "'," + catid + ")";
				 System.out.println("inserting prod:"+insertProduct);
				PreparedStatement ps = (PreparedStatement) con
						.prepareStatement(insertProduct);
				resultSet = ps.executeUpdate();
				String getid = "select productid from product where productname = '"
						+ prodname + "' and categoryid=" + catid;
				 System.out.println("getting product id"+ getid);
				PreparedStatement ps2 = (PreparedStatement) con
						.prepareStatement(getid);
				ResultSet get = ps2.executeQuery();
				while (get.next()) {

					prodid = get.getInt("productid");

				}
				int i = 0;
				for (String attvalue : atts) {
					String name = attnames.get(i++);
					int id = getAttributeId(name);
                    if(!attvalue.isEmpty() && !attvalue.equals(" ")) {
					String query = "insert into producteav(productid,attributeid,attributevalue) values("
							+ prodid + "," + id + ",'" + attvalue + "')";
				   System.out.println("inserting into eav"+query);
					PreparedStatement ps3 = (PreparedStatement) con
							.prepareStatement(query);
					resultSet = ps3.executeUpdate();
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
		}

		return resultSet;
	}
	public static int editProduct(ArrayList<String> attvalues,String prod) {
		Connection con;
		int resultSet = 0;
		
		try {
				con = DBConnection.getDBConnection();
				ArrayList<Attribute> atts = DB.getAttributes();
				int i=0;
				for(Attribute a:atts) 
				{	
				String editProduct = "update producteav set attributevalue='"+attvalues.get(i++)+"' where attributeid="+a.AttrId+" and productid=(select productid from product where productname='"+prod+"')";
				
				System.out.println("inserting prod:"+editProduct);
			    PreparedStatement ps = (PreparedStatement) con
						.prepareStatement(editProduct);
				resultSet = ps.executeUpdate();
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


	public static int getAttributeId(String came) {
		Connection con;
		int returnval = 0;

		try {
			con = DBConnection.getDBConnection();
			String query = "select attributeid from attributes where attributename='"
					+ came + "'";
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
	public static void setNavigation(ArrayList<NavigationItem> nav,int clicked_id) {
		Connection con;
		int returnval = 0;
		
        System.out.println("came with category id and checking for level:"+clicked_id);
		try {
			con = DBConnection.getDBConnection();
			String query = "select level from category where categoryid="
					+ clicked_id;
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {

				returnval = (resultSet.getInt("level"));

			}
			if(returnval==0)
			{
				NavigationItem navItem = new NavigationItem();
				navItem.setCategory_id(0);
				navItem.setCatNavName("Home");
				nav.add(navItem);
				System.out.println("in db, updated nav as"+nav);
			}
			else if(returnval==1)
			{
				String retNav = "select categoryname from category where categoryid="+clicked_id;
				System.out.println("querying nav as:"+retNav);
				PreparedStatement ps2 = (PreparedStatement) con
						.prepareStatement(retNav);
				ResultSet rs = ps2.executeQuery();
				NavigationItem navItem = new NavigationItem();
				navItem.setCategory_id(0);
				navItem.setCatNavName("Home>");
				nav.add(navItem);
				while (rs.next()) {
					NavigationItem navItem2 = new NavigationItem();
					 navItem2.setCatNavName(rs.getString("categoryname"));
					 navItem2.setCategory_id(clicked_id);
	            	 nav.add(navItem2);
				}
				System.out.println("in db, updated nav as"+nav);
			}
			else if(returnval==2)
			{
				String retNav ="select g.categoryid as grandid,g.categoryname as grand, p.categoryname as parent from category p,category g where p.parentid=g.categoryid and p.categoryid="+clicked_id;
				System.out.println("querying nav as:"+retNav);
				PreparedStatement ps2 = (PreparedStatement) con
						.prepareStatement(retNav);
				ResultSet rs = ps2.executeQuery();
				NavigationItem navItem = new NavigationItem();
				navItem.setCategory_id(0);
				navItem.setCatNavName("Home>");
				nav.add(navItem);
				while (rs.next()) {
					NavigationItem navItem2 = new NavigationItem();
					 navItem2.setCatNavName(rs.getString(2)+">");
					 navItem2.setCategory_id(rs.getInt(1));
	            	 nav.add(navItem2);
	            	 NavigationItem navItem3 = new NavigationItem();
					 navItem3.setCatNavName(rs.getString(3));
					 navItem3.setCategory_id(clicked_id);
	            	 nav.add(navItem3);
				}
				System.out.println("in db, updated nav as"+nav);
			}
			else if(returnval==3)
			{
				String retNav ="select g.categoryid as grandid,g.categoryname as grand, p.categoryid as parentid,p.categoryname as parent,c.categoryname from category p,category g,category c  where g.categoryid=p.parentid and p.categoryid=c.parentid and c.categoryid="+clicked_id;
				System.out.println("querying nav as:"+retNav);
				PreparedStatement ps2 = (PreparedStatement) con
						.prepareStatement(retNav);
				ResultSet rs = ps2.executeQuery();
				NavigationItem navItem = new NavigationItem();
				navItem.setCategory_id(0);
				navItem.setCatNavName("Home>");
				nav.add(navItem);
				while (rs.next()) {
					NavigationItem navItem2 = new NavigationItem();
					 navItem2.setCatNavName(rs.getString(2)+">");
					 navItem2.setCategory_id(rs.getInt(1));
	            	 nav.add(navItem2);
	            	 NavigationItem navItem3 = new NavigationItem();
					 navItem3.setCatNavName(rs.getString(4)+">");
					 navItem3.setCategory_id(rs.getInt(3));
	            	 nav.add(navItem3);
	            	 NavigationItem item4 = new NavigationItem();
	            	 item4.setCategory_id(clicked_id);
	            	 item4.setCatNavName(rs.getString(5));
	            	 nav.add(item4);
				}
				System.out.println("in db, updated nav as"+nav);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	public static ArrayList<DisplayProd> getThisProd(String prodname,int catid) {
		Connection con;
		ArrayList<DisplayProd> sendingThis = new ArrayList<DisplayProd>();
		int attid = 0;
		String attname;
		try {
			con = DBConnection.getDBConnection();
			String query = "select attributeid,attributename from attributes";
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				DisplayProd p = new DisplayProd();
				attid = (resultSet.getInt("attributeid"));
				attname = resultSet.getString("attributename");
				System.out.println("came till here...");
				String getvalue = "select attributevalue from producteav where productid=(select productid from product where productname='"
						+ prodname + "' and categoryid="+catid+") and attributeid=" + attid;
				ps = (PreparedStatement) con.prepareStatement(getvalue);
				ResultSet rs = ps.executeQuery();
				String attval;
				while(rs.next())
				{	attval = rs.getString("attributevalue");
					if(!attval.isEmpty())
						p.setAttvalue(attval);
				}
					p.setAttname(attname);
					sendingThis.add(p);
				
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

	// For Adding new address//
	public static int AddDeliveryAddress(int userid, String name,
			String address, String city, String state, String country,
			int pincode, String email, int phone) {
int addid=0;
		Connection con;
		try {
			con = DBConnection.getDBConnection();
			/*
			 * since user id is a foreign key which refers to customer there for
			 * we have to fill customer table first
			 */
			// userid is -1 for non loggedin user...
			/*if (userid == -1) {
				
				String query = "select userid from customer where email='"
						+ email + "'";
				PreparedStatement ps2 = (PreparedStatement) con
						.prepareStatement(query);
				ResultSet rs2 = ps2.executeQuery();
				while (rs2.next()) {
					userid = rs2.getInt("userid");
				}
			}*/
			
			String query2 = "insert into address (userid,name,address,city,state,country,pincode,email,phone) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement	 ps = (PreparedStatement) con.prepareStatement(
						query2, Statement.RETURN_GENERATED_KEYS);
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
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				addid= rs.getInt(1);
			}
			
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return addid;
	}

	// checking
	public static int checkUserCardCredential(BigDecimal cardNumber,
			int expireMonth, int expireYear, int cvcCode, String cardName,String cardType) {
		Connection con;
		int bankcardid=0;
		String ct;
		try {
			con = DBConnection.getDBConnection();
			String query1 = "select bankcardid from bankcards where cardtype='"+cardType+"' and cardno="+cardNumber+" and ownername='"+cardName+"' and cardexpmonth="+expireMonth+" and cardexpyear="+expireYear+" and cardcvv="+cvcCode+";";
			
			System.out.print(query1);
			PreparedStatement ps1 = (PreparedStatement) con
					.prepareStatement(query1);
			ResultSet resultSet = ps1.executeQuery();
			if(resultSet.next())
			{
				bankcardid=resultSet.getInt("bankcardid");
				return bankcardid;
				
			}
			else 
				
				{con.close();
				return 0;
				}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return 0;
		
	 // returns default 0 value if card details are not correct 
	}
	/* Kavya : Payment Gateway*/
	
	/*****
	 * 
	 * 
	 * 
	 * @param bank
	 */
	public static String Payfromnetbank(String username, String password, int cartid){
		Connection con;
		int accbalance=0;
		int netbankid;
		int newbalance;
		try {
			con = DBConnection.getDBConnection();
			String query1 = "select netbankid,accbalance from netbank where accountid='"+username+"' and accountpass='"+password+"';";
			//System.out.print(query1);
			int Amount = 0;
			PreparedStatement ps1 = (PreparedStatement) con
					.prepareStatement(query1);
			ResultSet resultSet = ps1.executeQuery();
			if(resultSet.next())
			{
				accbalance=resultSet.getInt("accbalance");
				netbankid=resultSet.getInt("netbankid");
			}
			else 
				return "Incorrect details Please fill the details Correctly";
			
		//	con=DBConnection.getDBConnection();
			String query="select totalamount from cart where cartid="+cartid;
			PreparedStatement ps2 = (PreparedStatement) con.prepareStatement(query);
			ResultSet resultSet2 = ps2.executeQuery();
			if (resultSet2.next()) {
				Amount=resultSet2.getInt("totalamount");
			}
			
			if(accbalance>=Amount){
				newbalance = accbalance-Amount;	
				System.out.print("updated balance is "+newbalance);
				String query2 = "UPDATE netbank SET accbalance="+newbalance+" WHERE netbankid="+netbankid;
				PreparedStatement ps3 = (PreparedStatement) con
						.prepareStatement(query2);
				ps3.executeUpdate();
			}
			else
				return "Insufficient Balance In Your Account ";
			con.close();

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		
	
		return "Transaction Successfull";
		
	}
	
	public static void walletUpdate(int userid,int cartid) throws Exception
	{
		Connection con;
		int Amount = 0;
		int walletamount=0;
		try {con = DBConnection.getDBConnection();
		String query1 = "select totalamount from cart where cartid="+cartid;
		//System.out.print(query1);
		
		PreparedStatement ps1 = (PreparedStatement) con.prepareStatement(query1);
		ResultSet resultSet = ps1.executeQuery();
		while(resultSet.next())
		{
			Amount=resultSet.getInt("totalamount");
			
		}
		String query2 = "select walletamount from wallet where userid="+userid;
		
		PreparedStatement ps2 = (PreparedStatement) con.prepareStatement(query2);
		ResultSet resultSet2 = ps2.executeQuery();
		while(resultSet2.next())
		{
			walletamount=resultSet2.getInt("walletamount");
			
		}
		walletamount=walletamount-Amount;
		
		String query3 = "UPDATE wallet SET walletamount="+walletamount+" WHERE userid="+userid;
		PreparedStatement ps3 = (PreparedStatement) con
				.prepareStatement(query3);
		ps3.executeUpdate();
		
		
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	
	public static String PaymentfromCard(int bankcardid, String Password, int cartid) {
		Connection con;
		int Amount=0;
		try {
			CardCredentials cc = new CardCredentials();
			con = DBConnection.getDBConnection();
			
			/*To get total amount from cart table*/
			String query="select totalamount from cart where cartid="+cartid;
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet resultSet1 = ps.executeQuery();
			if (resultSet1.next()) {
				Amount=resultSet1.getInt("totalamount");
				
			}
			
			
			
			
			
			String query1 = "select * from bankcards where bankcardid="+bankcardid;
			PreparedStatement ps1 = (PreparedStatement) con
					.prepareStatement(query1);
			ResultSet resultSet = ps1.executeQuery();
			if (resultSet.next()) {
				cc.setCardName(resultSet.getString("ownername"));
				cc.setCardType(resultSet.getString("cardtype"));
				cc.setDebitBalance(resultSet.getInt("debitbalance"));
				cc.setCreditLimit(resultSet.getInt("creditlimit"));
				cc.setPassword(resultSet.getString("password"));
			}
			else 
				return "failure";
			
		
			
			if ((cc.password).equals(Password)){
				if((cc.cardType).equalsIgnoreCase("debit")){
					if((cc.debitBalance)>=Amount){
						int newdbalance = cc.debitBalance-Amount;
						System.out.print("updated debit balance is "+newdbalance);
						String query2 = "UPDATE bankcards SET debitbalance="+newdbalance+" WHERE bankcardid="+bankcardid;
						PreparedStatement ps2 = (PreparedStatement) con
								.prepareStatement(query2);
						ps2.executeUpdate();
						return "success";
					}
					else
						return "failure";
				}
				else if((cc.cardType).equalsIgnoreCase("credit")){
					if((cc.creditLimit)>=Amount){
						int newcbalance = cc.creditLimit-Amount;
						System.out.print("updated credit balance is "+newcbalance);
						String query3 ="UPDATE bankcards SET creditlimit="+newcbalance+" WHERE bankcardid="+bankcardid;
						PreparedStatement ps3 = (PreparedStatement) con
								.prepareStatement(query3);
						ps3.executeUpdate();
						return "success";
					}
					else
						return "failure";
				}
			}
			else 
				return "failure";
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "failure";
	
		
	}
	
	/******************************************/
	
	
	
	
	
	
	

	// Getting entire bank
	public static void getBankName(ArrayList<String> bank) {

		Connection con;
		try {
			con = DBConnection.getDBConnection();

			String query1 = "select bankname from bank ";
			PreparedStatement ps1 = (PreparedStatement) con
					.prepareStatement(query1);

			ResultSet resultSet = ps1.executeQuery();
			while (resultSet.next()) {
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

	public static void getAddress(ArrayList<DeliveryAddress> addr, int userid) {
		System.out.println("Addresss....." + userid);
		Connection con;
		try {
			con = DBConnection.getDBConnection();

			String query1 = "select name,address,city,state,country,pincode,email,phone,addressid from address where userid="
					+ userid;

			PreparedStatement ps1 = (PreparedStatement) con
					.prepareStatement(query1);

			ResultSet resultSet = ps1.executeQuery();
			System.out.println("Query" + query1);
			while (resultSet.next()) {
				DeliveryAddress d = new DeliveryAddress();
			

				d.setName(resultSet.getString("name"));
				d.setAddress(resultSet.getString("address"));
				d.setCity(resultSet.getString("city"));
				d.setState(resultSet.getString("state"));
				d.setCountry(resultSet.getString("country"));
				d.setPincode(resultSet.getInt("pincode"));
				d.setEmail(resultSet.getString("email"));
				d.setPhone(resultSet.getInt("phone"));
				System.out.println(resultSet.getString("phone"));
				d.setAddressid(resultSet.getInt("addressid"));
				d.setUserid(userid);
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

	public static int Unreguser(String email) {
		Connection con;
		int userid = 0;
		try {
			con = DBConnection.getDBConnection();
			System.out.println(email);
			String query = "Insert into customer(email) values('" + email
					+ "')";
			System.out.println("trying to insert " + query);
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement(query);
			int rs = ps.executeUpdate();
			
			 if(rs==1) { query =
			 "select userid from customer where email='"+email+"'";
			 PreparedStatement ps2 = (PreparedStatement) con
			 .prepareStatement(query); 
			 ResultSet rs2 = ps2.executeQuery();
			 while(rs2.next()) 
			 { userid = rs2.getInt("userid"); }
			  System.out.println("user id inserted"+userid); }
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userid;
	}

	public static void insertAddid(Order o, int addressid) {

		Connection con;
		try {
			con = DBConnection.getDBConnection();
System.out.println("inside insert add.....");
			String query1 = "insert into flipkart3.order (addressid) value (?)";
				
			PreparedStatement ps1 = (PreparedStatement) con
					.prepareStatement(query1);
			ps1.setInt(1, addressid);

			ps1.executeUpdate();

			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	/*public static void getCartItem(int cartid,ArrayList<OrderItem> o)
	{
		Connection con;
		try {
			con = DBConnection.getDBConnection();

			String query1 = "select name,address,city,state,country,pincode,email,phone,addressid from address where userid="
					+ cartid;

			PreparedStatement ps1 = (PreparedStatement) con
					.prepareStatement(query1);

			ResultSet resultSet = ps1.executeQuery();
			System.out.println("Query" + query1);
			while (resultSet.next()) {
				DeliveryAddress d = new DeliveryAddress();
			


			}

			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		
	}*/
	
	
	/*to get products for order summary page from cartitem table*/
	public static int getProducts(ArrayList<OrderItem> orditm,int cartid)
	{int totalamount = 0;
		Connection con;
		try {
			con = DBConnection.getDBConnection();
			PreparedStatement ps1;
			ResultSet resultSet;
			String query1 = " select productname,quantity,price,subtotal,deliverydetails,attributevalue "+
					 "from product,cartitem,producteav where cartid=? and product.productid=cartitem.productid "
					 + "and product.productid=producteav.productid and producteav.attributeid=6";
			
			//attribute id =6 is defined in attributes table

			 ps1 = (PreparedStatement) con.prepareStatement(query1);
			 ps1.setInt(1, cartid);
			 resultSet = ps1.executeQuery();
			 
		//	System.out.println("Query" + query1);
			
			while (resultSet.next()) {
			OrderItem o = new OrderItem();
				o.setProductname(resultSet.getString("productname"));
				System.out.println(resultSet.getString("productname"));
				
				o.setQuantity(resultSet.getInt("quantity"));
				System.out.println("Quantity::"+resultSet.getInt("quantity"));
				
				
				o.setPrice(resultSet.getInt("price"));
				o.setSubtotal(resultSet.getInt("subtotal"));
				o.setDeliverydetails(resultSet.getString("deliverydetails"));
				o.setImagepath(resultSet.getString("attributevalue"));
				orditm.add(o);

			}
			String query2="select totalamount from cart where cartid="+cartid;
			ps1 = (PreparedStatement) con.prepareStatement(query2);
			resultSet = ps1.executeQuery();
			while (resultSet.next()) {
				totalamount=resultSet.getInt("totalamount");
			}
			

			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return totalamount;
	}

	public static void insertOrder(int userid,int cartid,int addressid) throws SQLException
	{
		Connection con = null;
		int orderid=0;
	 int totalamount=0;
		int shipmentcharges=0;
		int cnt=0;
	//	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date(0);
		//dateFormat.format(date);
		Date tomorrow = new Date(date.getTime() + (1000 * 60 * 60 * 24));
		ArrayList<OrderItem> orderitm =new ArrayList<OrderItem>();
		
		try {
			ResultSet resultSet;
			con = DBConnection.getDBConnection();
		
			String query = "select totalamount,shipmentcharges from cart where cartid="+cartid;
			
			PreparedStatement ps;
			ps= (PreparedStatement) con
					.prepareStatement(query);
			resultSet = ps.executeQuery();
			while (resultSet.next())
			{
				totalamount=resultSet.getInt("totalamount");
				shipmentcharges=resultSet.getInt("shipmentcharges");
				
			}
			String query1="select productid,quantity,price,subtotal,deliverydetails from cartitem where cartid="+cartid;
			ps= (PreparedStatement) con
					.prepareStatement(query1);
			resultSet = ps.executeQuery();
			while (resultSet.next())
			{
				OrderItem o= new OrderItem();
				o.setProductid(resultSet.getInt("productid"));
				o.setQuantity(resultSet.getInt("quantity"));
				o.setPrice(resultSet.getInt("price"));
				o.setSubtotal(resultSet.getInt("subtotal"));
				o.setDeliverydetails(resultSet.getString("deliverydetails"));
				orderitm.add(o);
				cnt++;
			}
		String query2="insert into flipkart3.order (userid,totalamount,shipmentcharges,numberofitems,orderdate,addressid) values(?,?,?,?,?,?)";
	 ps = (PreparedStatement) con.prepareStatement(
				query2, Statement.RETURN_GENERATED_KEYS); //this statement will return auto increment key
		ps.setInt(1, userid);
		ps.setInt(2,totalamount);
		ps.setInt(3, shipmentcharges);
		ps.setInt(4, cnt);
		ps.setDate(5, date);
		ps.setInt(6, addressid);
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			orderid= rs.getInt(1);
		}
		
		int j = 0;
		while (j < orderitm.size()) {
			
		
		
		String query3="insert into orderitem (orderid,productid,quantity,price,orderstatus,deliverydate,subtotal,deliverydetails) values(?,?,?,?,?,?,?,?)";
				ps= (PreparedStatement) con.prepareStatement(query3);
				ps.setInt(1, orderid);
				ps.setInt(2, orderitm.get(j).getProductid());
				ps.setInt(3, orderitm.get(j).getQuantity());
				ps.setInt(4, orderitm.get(j).getPrice());
				ps.setString(5, "Placed");
				ps.setDate(6,tomorrow);
				ps.setInt(7, orderitm.get(j).getSubtotal());
				ps.setString(8, orderitm.get(j).getDeliverydetails());
				
		ps.executeUpdate();
				
		/*  Stock Updation  */
		int stock=0;
		String query4="select attributevalue from producteav where productid=? and attributeid=8";
		
		ps= (PreparedStatement) con.prepareStatement(query4);
		ps.setInt(1,orderitm.get(j).getProductid());
		resultSet = ps.executeQuery();
		
		while (resultSet.next())
		{
			stock=resultSet.getInt("attributevalue");
		}
		stock=stock-orderitm.get(j).getQuantity();
		String query5="update producteav set attributevalue=? where attributeid=8 and productid=?";
		ps= (PreparedStatement) con.prepareStatement(query5);
		ps.setInt(1,stock);
		ps.setInt(2,orderitm.get(j).getProductid());
		
		ps.executeUpdate();
		
				j++;
				
				}
			System.out.println("Cart Id:::"+cartid);
			
		String query6="update cart set totalamount=0,shipmentcharges=0 where cartid="+cartid;
		ps= (PreparedStatement) con.prepareStatement(query6);
		ps.executeUpdate();
		
		
		String query7="delete from cartitem where cartid="+cartid;
		ps= (PreparedStatement) con.prepareStatement(query7);
		ps.executeUpdate();

		
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
	
		con.close();
	}
	
	/**********************************************************************************/

	public static UserWho orderLogin(String email, String password) {
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
		String str1;
		int count=0;
		DBConnection connect = new DBConnection();
		Connection con;
		try {
			con = connect.getDBConnection();
			str1="select * from wishlist where userid="+userid;
			PreparedStatement pr2=(PreparedStatement) con.prepareStatement(str1);
			ResultSet result=pr2.executeQuery();
			while(result.next())
			{
				count++;
			}
			System.out.println("inside wishlist insert count value:"+count);
            if(count==0)
            {
			  str = "insert into wishlist (userid) values(" + userid + ")";
			  PreparedStatement pr1 = (PreparedStatement) con
					.prepareStatement(str);
			  pr1.executeUpdate();
            }
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
	
	
	
	//----kavya----Wallet---
	public static int getamount(int userid)
	{
		Connection con;
		int amount=0;
		try {
			con = DBConnection.getDBConnection();
			System.out.println(userid);
			String query = "select walletamount from wallet where userid="+userid;
			PreparedStatement ps2 = (PreparedStatement) con
					.prepareStatement(query);
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next())
			{
				amount = rs2.getInt("walletamount");
			}
			System.out.println("Db wallet amount"+amount);
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return amount;
	}

}
