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
<title>Account: Personal Information</title>
</head>
<body>
	<h2 class="modal-title">Personal Information</h2>
	<br />
	<form action="updatePersonalInfo">
		<table>
			<tr>
				<td><label for="fname">First Name:</label></td>
				<td><input type="text" name="fname"
					value="<s:property value="fname"/>" /></td>
			</tr>
			<tr>
				<td><label for="lname">Last Name:</label></td>
				<td><input type="text" name="lname"
					value="<s:property value="lname" />" /></td>
			</tr>
			<tr>
				<td><label for="gender">Gender:</label></td>
				<td><s:select label="Gender:" headerKey="-1"
						headerValue="SELECT" list="#{'Male':'Male', 'Female':'Female'}"
						name="gender" value="gender" /></td>
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