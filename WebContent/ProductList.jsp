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
<li><s:property value="categoryName"/></li></s:iterator>
</ul>
</div>
	
	<div style="float:right ;width:77%; background-color:#ffffff; ">

	<center>
		<br> <br>
		<div class="container">

			<s:iterator value="products">
			
				<div class="container col-md-4">
				
					<a class="product"
						href="showProduct?productId=<s:property value="productId" />">
						<s:iterator value="productEAV">
						<s:if test="attributeName=='Image_Path'"> 
						 
				<img src="<s:property value="attributeValue"/>" alt="340x340" height="80" width="80">
				</s:if>
				
				</s:iterator>
						<s:property value="productId" /> : <s:property
							value="productName" /> <br> <s:iterator value="productEAV">
							<br>
							<font color="#000000" size="1.5">
							<s:property value="attributeName" /> : 
						<s:property value="attributeValue" />
						</font>
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
<button type="button" class="btn btn-primary"
					onclick='this.form.action="showProduct?productId=${productId}";'>
					


 -->