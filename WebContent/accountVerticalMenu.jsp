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
			<center><h4>My Account</h4></center>
		</div>
		<br> <b> ORDERS<b><br> <br> <a href=""><b>My
						Orders</b></a><br>
				<hr size=2 width=200 align=left> <b> PAYMENTS<b><br>
						<br> <a href="wallet_action"><b>Wallet</b></a></br>
						<hr size=2 width=200 align=left> <b> MY STUFF<b><br>
								<br><a href="displaywishlist"><b> My Wishlist</b></a><br>
								<hr size=2 width=200 align=left> <b> SETTINGS</b><br>
								<br>
								<a href="fetchPersonalInfo"><b>Personal Information</b></a><br>
								<a
								href="fetchPasswordSetter"><b>Change Password</b></a><br> <a href="fetchAccAddress"><b>Addresses</b></a><br>
								<a href="fetchDeactivator"><b>Deactivate Account</b></a><br>
								<a href="fetchUpdateEmail"><b>Update Email</b></a><br>
								<hr size=2 width=200 align=left> <br>
	</div>
</body>
</html>