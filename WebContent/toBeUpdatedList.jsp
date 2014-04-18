<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flipkart admin page</title>
</head>
<body>
<h3> List of products to be stocked up immeadiately</h3>
<table border="2">
<tr>
<th>Update Stock of Product</th>
<th>Stock available Now</th>
<th>Threshold </th>
</tr>
<s:iterator value="products"> 
<tr>
<td><a href="<s:url action="showproduct_admin"><s:param name="prod_name"><s:property value="productname"/></s:param><s:param name="catid"><s:property value="categoryid"/></s:param></s:url>" ><s:property value="productname"/></a></td>
<td><s:property value="stock"/></td>
<td><s:property value="threshold"/></td>
</s:iterator>
</body>
</html>