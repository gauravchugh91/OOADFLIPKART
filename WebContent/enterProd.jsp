<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flipkart Admin Account</title>
</head>
<body>
<s:form action="insertProd"> Please enter product details: <s:textfield label="Product name"/>
<s:select label="Attribute" list="attnames"/> 
<s:submit value="Add product"/>
</s:form>
</body>
</html>