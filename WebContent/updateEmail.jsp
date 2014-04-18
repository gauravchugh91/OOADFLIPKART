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
<title>Account: Update Email</title>
</head>
<body>
	<h2 class="modal-title">Update Email</h2>
	<br />
	<form action="changeEmail">
		<table>
			<tr>
				<td><label for="email">Email Address:</label></td>
				<td><s:property value="%{#session['email']}" /></td>
			</tr>
			<tr>
				<td><label for="new_Email">New Email Address:</label></td>
				<td><input type="text" name="new_email" /></td>
			</tr>
		</table>
		<center>
			<input type="submit" class="btn btn-primary"
				value="SAVE CHANGES" />
				<s:if test="status==1">
				<TABLE BORDER="0" CELLPADDING="3" CELLSPACING="0" WIDTH="350"
					BGCOLOR="#66FF99">
					<TR>
						<TD>
							<TABLE BORDER="0" CELLPADDING="2" CELLSPACING="0" WIDTH="100%"
								BGCOLOR="#66FF66">
								<TR>
									<TD WIDTH="80%" BGCOLOR="#007700"><img
										src="assets/images/correct.png" width="32" height="32">
										Your email has been updated.</TD>
								</TR>
							</TABLE>
						</TD>
					</TR>
				</TABLE>
			</s:if>
			<s:elseif test = "status==0">
			     <TABLE BORDER="0" CELLPADDING="3" CELLSPACING="0" WIDTH="350"
					BGCOLOR="#BB0000">
					<TR>
						<TD>
							<TABLE BORDER="0" CELLPADDING="2" CELLSPACING="0" WIDTH="100%"
								BGCOLOR="#FF9999">
								<TR>
									<TD WIDTH="80%" BGCOLOR="#BB0000"><img
										src="assets/images/wrong.png" width="32" height="32">
										Please enter a valid email !</TD>
								</TR>
							</TABLE>
						</TD>
					</TR>
				</TABLE>
			</s:elseif>			    
		</center>
		<br /> <br /> <br />
		<P>
			<B>What happens when the Email ID is updated?</B>


		</P>
		<br />
		<ul>
			<li>Wallet balance is transferred to the new account</li>
			<li>Your order history is available under the new account</li>
			<li>Your wishlist items are associated with the new account's URL.</li>
			
		</ul>
		

	</form>
</body>
</html>
