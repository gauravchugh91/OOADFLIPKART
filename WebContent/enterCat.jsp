<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flipkart admin account</title>
</head>
<body>
<h3>Enter category details</h3>

<s:form action="insertCat"> <s:textfield label="Category name" name="category" />

<s:hidden name="clicked_id" value="%{clicked_id}"/>
<s:submit key="submit" name="submit" /> </s:form>
</body>
</html>