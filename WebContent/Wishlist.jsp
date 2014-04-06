<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.divison1 {
	position: relative;
	left: 400px;
	bottom: 580px
}

.divison2 {
	position: relative;
	left: 150px;
	top: 80px
}

a {
	color: DodgerBlue
}

a:hover {
	color: black
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Wishlist Page</title>
</head>
<body>
	<div>
		<div
			style="background-color: DodgerBlue; width: 200px; height: 35px; border: 1px solid #000">
			<h4>My Account</h4>
		</div>
		<br> <b> ORDERS<b><br>
			<br> <a href=""><b>My Orders</b></a><br>
				<hr size=2 width=200 align=left> <b> PAYMENTS<b><br>
					<br> <a href=""><b>Wallet</b></a></br>
						<hr size=2 width=200 align=left> <b> MY STUFF<b><br>
							<br> <b><font color="DodgerBlue"> My Wishlist</font></b><br>
								<a href=""><b> Recommendations For You</b></a><br>
								<hr size=2 width=200 align=left> <b> SETTINGS</b><br>
							<br> <a href=""><b>Personal Information</b></a><br> <a
								href=""><b>Change Password</b></a><br> <a href=""><b>Addresses</b></a><br>
								<a href=""><b>Profile Settings</b></a><br>
								<hr size=2 width=200 align=left>
								<br>
	</div>
	<div class="divison1">
		<h3>
			My Wishlist(
			<s:property value="count_prod" />
			items)
		</h3>
		<br>
		<br>
		<s:iterator value="Wishlist">
			<div class="divison2">
				<s:property value="productname" />
				<hr size=2 align=left width=200 />
				<b>RS.&nbsp</b>
				<s:property value="price" />
				&nbsp &nbsp &nbsp&nbsp&nbsp&nbsp
				<s:property value="gen_desc" />
				<br>
				<br> <b><font color="green">In Stock</font></b><br> <i>Delivered
					in 4-5 business days. [?]<i><br>
					<br>
						<form>
							<input type="submit"
								style="background-color: Orange; border: 1px solid #000"
								value="Buy Now">
							</button>
						</form>
						<br> 
						<a href="deleteproduct?Productid=<s:property value="productid"/>">Remove from Wishlist</a>
			</div>

			<img style="position: relative; bottom: 150px"
				src=<s:property value="image_path" /> width="100" height="150" />&nbsp &nbsp &nbsp&nbsp

        <hr size=2 align=left width=900 />
			<br>
		</s:iterator>
	</div>


</body>
</html>


