<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Flipkart Order</title>
</head>
<body>
<h3> List of orders </h3>


<table border="2">
<tr> <th>OrderID</th><th>UserId</th><th>Order date</th><th>No of items</th><th>shipment charges</th><th>total amount payable</th><th>Address</th></tr>
<s:iterator value="orderList">
<tr>
<td><a href="<s:url action="showOrderItem"> <s:param name="orderid"><s:property value="orderid"/></s:param></s:url>"><s:property value="orderid"/></a></td>
<td><s:property value="userid"/></td>
<td><s:property value="orderdate"/></td>
<td><s:property value="noofItems"/></td>
<td> <s:property value="shipmentcharges"/> </td>
<td> <s:property value="totalamount"/></td>
<td> <s:property value="addressid"/></td>
</tr>
</s:iterator>
</table>
</body>
</html>