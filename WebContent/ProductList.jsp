<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product list</title>

<style type="text/css">
.button {
	border-style: solid;
	border-width: 0px;
	cursor: pointer;
	font-family: "Helvetica Neue", "Helvetica", Helvetica, Arial, sans-serif;
	font-weight: normal;
	line-height: normal;
	margin: 0 0 1.25rem;
	position: relative;
	text-decoration: none;
	text-align: center;
	display: inline-block;
	padding-top: 1rem;
	padding-right: 2rem;
	padding-bottom: 1.0625rem;
	padding-left: 2rem;
	font-size: 1rem;
	background-color: #008cba;
	border-color: #007095;
	color: white;
	-webkit-transition: background-color 300ms ease-out;
	-moz-transition: background-color 300ms ease-out;
	transition: background-color 300ms ease-out;
	padding-top: 1rem;
	padding-right: 2rem;
	padding-bottom: 1.0625rem;
	padding-left: 2rem;
	font-size: 1rem;
}
a:HOVER {
	text-decoration: none;
	color: black;
}
</style>
</head>
<body style="background-color: #f5f2fc; ">

<div style="float:center ;width:100%; background-color:#ffffff; ">

<center><h2>List of Existing Products</h2></center>
</div>
<br>
<div style="float:right ;width:100%;  ">

<div style="float:left ;width:20%; background-color:#ffffff;">
<ul>
<s:iterator value="subCategoryList">
<li><a href="search?searchBar=<s:property value="categoryName"/>" style="color: black;"><s:property value="categoryName"/></a></li></s:iterator>
</ul>
</div>
	
	<div style="float:right ;width:77%; background-color:#ffffff; ">

	<center>
		<br> <br>
		<div class="container">

			<s:iterator value="products">
			
				<div class="container col-md-4">
				
					<a class="button" href="showProduct?productId=<s:property value="productId" />" style="background-color: #ffffff;">
					<s:iterator value="productEAV">
						<s:if test="attributeName=='Image_Path'"> 
				<img src="<s:property value="attributeValue"/>" alt="340x340" height="200" width="160">
				</s:if>
				</s:iterator>
						<br>
						<br>
						<font color="#000000" size="3">
						<b><s:property value="productName" /></font> </b> <br> 
						<s:iterator value="productEAV">
							<br>
						<s:if test="attributeName=='Discount'">
							
							<font color="#008000" size="4">
							<b>
						<s:property value="attributeName" /> : 
						<s:property value="attributeValue" />%
						</b></font>
						</s:if>
						<s:elseif test="attributeName=='General_Desc'">
						<font color="#000000" size="3">
						<b>
						<s:property value="attributeValue" /></b>
						</font>
						</s:elseif>
						<s:elseif test="attributeName=='Offer'">
						<font color="#008000" size="4">
						<b>
						<s:property value="attributeName" /> : 
						<s:property value="attributeValue" /></b>
						</font>
						</s:elseif>
						</s:iterator>
					</a>
				</div>
			</s:iterator>
		</div>
	</center>
	</div>
	</div>
</body>
</html>



<!-- 
<button type="button" clas	s="btn btn-primary"
					onclick='this.form.action="showProduct?productId=${productId}";'>
					


 -->