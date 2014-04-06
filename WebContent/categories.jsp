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

<ul>

<s:if test="level==1">
<h3> Categories </h3>
<s:iterator value="cats">

<li><a href="<s:url action="showcatlist"> <s:param name="clicked_id"> <s:property value="id"/></s:param> </s:url>"><s:property value="name"/></a></li></s:iterator> </ul>
<h3><a href="<s:url action="addCat"> <s:param name="clicked_id"><s:property value="clicked_id"/></s:param></s:url>">Add Category</a></h3></s:if>
<s:else>
<h3> Products </h3>
<s:iterator value="products">

<li><s:property /></li></s:iterator> </ul>
<h3><a href="<s:url action="addProd"> <s:param name="clicked_id"><s:property value="clicked_id"/></s:param></s:url>">Add Product</a></h3>
</s:else>



</body>
</html>