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
<title>Account: Add Address</title>
</head>
<body>
	<h2 class="modal-title">Add A New Address</h2>
	<br />
	<form action="updateAccountAddress">
		<table>
			<tr>
				<td><label for="name">Name:</label></td>
				<td><s:property value="name" /></td>
			</tr>
			<tr>
				<td><label for="address">Address:</label></td>
				<td><input type="text" name="address"
					value="<s:property value="address"/>" /></td>
			</tr>
			<tr>
				<td><label for="city">City:</label></td>
				<td><input type="text" name="city"
					value="<s:property value="city" />" /></td>
			</tr>
			<tr>
				<td><label for="city">State:</label></td>
				<td><input type="text" name="state"
					value="<s:property value="state" />" /></td>
			</tr>
			<tr>
				<td><label for="country">Country :</label></td>
				<td><input type="text" name="country"
					value="<s:property value="country" />" /></td>
			</tr>
			<tr>
				<td><label for="phone">Pincode :</label></td>
				<td><input type="pincode" name="pincode"
					value="<s:property value="pincode" />" /></td>
			</tr>
			<tr>
				<td><label for="phone">Phone:</label></td>
				<td><s:textfield id="phone" key="phone" name="phone"
						required="required" /></td>
			</tr>
		</table>
		<center>
			<input type="submit" class="btn btn-primary" value="SAVE CHANGES" />
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
										Your changes have been saved successfully.</TD>
								</TR>
							</TABLE>
						</TD>
					</TR>
				</TABLE>
			</s:if>
		</center>
	</form>

</body>
</html>