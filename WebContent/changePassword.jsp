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
<title>Account: Change Password</title>
</head>
<body>
	<h2 class="modal-title">Change Password</h2>
	<br />
	<form action="updatePassword">
		<table>
			<tr>
				<td><label for="email">Name:</label></td>
				<td><s:property value="%{#session['email']}" /></td>
			</tr>
			<tr>
				<td><label for="oldPassword">Old Password:</label></td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td><label for="newPassword">New Password:</label></td>
				<td><input type="password" name="new_password" /></td>
			</tr>
			<tr>
				<td><label for="retype_new">Retype New Password:</label></td>
				<td><input type="password" name="retype_new" /></td>
			</tr>
			</table>
		<center>
			<input type="submit" class="btn btn-primary" value="CHANGE PASSWORD" />
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
										Your password has been changed successfully.</TD>
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
										Invalid Entry!!</TD>
								</TR>
							</TABLE>
						</TD>
					</TR>
				</TABLE>
			</s:elseif>			    
		</center>
	</form>

</body>
</html>