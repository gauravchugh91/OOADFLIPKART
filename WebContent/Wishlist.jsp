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
		<br> <b> ORDERS<b><br> <br> <a href=""><b>My
						Orders</b></a><br>
				<hr size=2 width=200 align=left> <b> PAYMENTS<b><br>
						<br> <a href=""><b>Wallet</b></a></br>
						<hr size=2 width=200 align=left> <b> MY STUFF<b><br>
								<br> <b><font color="DodgerBlue"> My Wishlist</font></b><br>
								<a href=""><b> Recommendations For You</b></a><br>
								<hr size=2 width=200 align=left> <b> SETTINGS</b><br>
								<br> <a href=""><b>Personal Information</b></a><br> <a
								href=""><b>Change Password</b></a><br> <a href=""><b>Addresses</b></a><br>
								<a href=""><b>Profile Settings</b></a><br>
								<hr size=2 width=200 align=left> <br>
	</div>
	<div class="divison1">
		<h3>
			My Wishlist(
			<s:property value="count_prod" />
			items)
		</h3>
		<br> <br>
		<s:iterator value="Wishlist">
			<div class="divison2">
				<a class="button" href="showProduct?productId=<s:property value="productid" />" style="background-color: #ffffff;">
	
				<s:property value="productname" />
				</a>
				<hr size=2 align=left width=200 />
				<b>RS.&nbsp</b>
				<s:property value="price" />
				&nbsp &nbsp &nbsp&nbsp&nbsp&nbsp
				<s:property value="gen_desc" />
				<br> <br> <b><font color="green">In Stock</font></b><br>
				<i>Delivered in 4-5 business days. [?]<i><br> <br>
				
						<%--<input type="submit"
						style="background-color: Orange; border: 1px solid #000"
						data-toggle="modal" data-target="#cart" value="Buy Now"
						id="buyNow"> <br> --%><a
						href="deleteproduct?Productid=<s:property value="productid"/>">Remove
							from Wishlist</a>
			</div>

			<img style="position: relative; bottom: 150px"
				src=<s:property value="image_path" /> width="100" height="150" />&nbsp &nbsp &nbsp&nbsp

        <hr size=2 align=left width=900 />
			<br>
		</s:iterator>
	</div>
	<div class="modal fade" id="cart" tabindex="-1">
		<div class="modal-dialog" style="width: 900px; height: 500px;">
			<div class="modal-content" style="height: 500px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						Cart (<label id="noOfItems"></label>)
					</h4>

				</div>
				<div class="modal-body" id="noItemsBody"
					style="overflow-y: auto; height: 250px; padding-left: 2%; padding-right: 2%; border: medium; border-color: blue; top: 20%; left: 40%">No
					Items in cart</div>

				<div class="modal-body ">
					<div id="bod"
						style="overflow-y: auto; height: 250px; padding-left: 2%; padding-right: 2%"></div>
				</div>
				<div class="modal-footer" style="bottom: 0; height: 100px;">
					<div style="float: right" id="amountDiv">
						Total Amount Payable : Rs <label id="total"></label>
					</div>
					<br> <br>
					<button type="button" class="btn btn-default" data-dismiss="modal">Continue
						Shopping</button>
					<a href="placeorder" class="btn btn-primary" id="order">Place
						Order</a>
				</div>
			</div>
		</div>
	</div>

</body>
</html>


