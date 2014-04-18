<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Addresses shown</title>
</head>
<body>
 <%-- <table width="25%"  cellpadding="0" cellspacing="0" style="border-width: 0px; background-color: #CCFFFF;">
		<tr><th>Wallet</th></tr>
		<tr><td><br /> Total Amount :<s:property value="walletamount" /></td><br /> </tr>
		
	
	</table> --%>
<div style="opacity:0.5;position:absolute;left:50px;width:300px;height:150px;background-color:#40B3DF">
<h3>Wallet Amount</h3>
<div style="color:#000000;">Total Amount :<s:property value="walletamount"/>	</div>
</div>
</body>
</html>