<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/theme.css" rel="stylesheet">
<style type="text/css">
a {
	color: #ADD3E6
}

a:HOVER {
	color: white;
}
</style>
<title>Account: Deactivate Account</title>
</head>
<body>
	<h2 class="modal-title">Deactivate Account</h2>
	<br />
	<form action="deactivate">
		<table>
			<tr>
				<td><label for="email">Email:</label></td>
				<td><s:property value="%{#session['email']}" /></td>
			</tr>
			<tr>
				<td><label for="Password">Password:</label></td>
				<td><input type="password" name="password" /></td>
			</tr>
		</table>
		<center>
			<input type="submit" class="btn btn-primary"
				value="DEACTIVATE ACCOUNT" />
		</center>
		<br /> <br /> <br />
		<P>
			<B>When you deactivate your account</B>


		</P>
		<br />
		<ul>
			<li>You are logged out of your Flipkart Account</li>
			<li>Your account data is retained and is restored in case you
				choose to reactivate your account</li>
		</ul>
		<P>
			<br /> <br /> <br /> <b>How do I reactivate my Flipkart
				account?</b><br />
		<ul>
			<li>Reactivation is easy.</li>
			<li>Simply login with the email/social network ID and password
				combination used prior to deactivation.</li>
			<li>Your account data is fully restored. Default settings are
				applied and you will be subscribed to receive promotional emails
				from Flipkart. <br /> Flipkart retains your account data for you to
				conveniently start off from where you left, if you decide to
				reactivate your account.
			</li>
		</ul>
		</P>

	</form>
</body>
</html>
