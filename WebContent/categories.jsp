<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flipkart Admin Account</title>
<script>
    history.forward();
</script>
</head>
<body>
<a href="<s:url action="showmenu"/>"> Main Menu> </a><s:iterator value="navigation"> <a href="<s:url action="showcatlist"><s:param name="clicked_id"><s:property value="category_id"/></s:param></s:url>"><s:property value="catNavName"/></a></s:iterator>

<ul>

<s:if test="level==0">
<h3>List of categories</h3>
<s:iterator value="cats">
<li><a href="<s:url action="showcatlist"> <s:param name="clicked_id"> <s:property value="id"/></s:param> </s:url>"><s:property value="name"/></a></li></s:iterator> </ul>
<h3><a href="<s:url action="addCat"> <s:param name="clicked_id"><s:property value="clicked_id"/></s:param></s:url>">Add Category</a></h3>
</s:if>
<s:elseif test="level==1">
<h3> List of sub categories</h3>
<s:iterator value="cats">
<li><a href="<s:url action="showcatlist"> <s:param name="clicked_id"> <s:property value="id"/></s:param> </s:url>"><s:property value="name"/></a></li></s:iterator> </ul>
<h3><a href="<s:url action="addCat"> <s:param name="clicked_id"><s:property value="clicked_id"/></s:param></s:url>">Add Category</a></h3>
</s:elseif>
<s:elseif test="level==2">
<h3>  List of leaf categories in subcategory</h3>
<s:iterator value="cats">
<li><a href="<s:url action="showcatlist"> <s:param name="clicked_id"> <s:property value="id"/></s:param> </s:url>"><s:property value="name"/></a></li></s:iterator> </ul>
<h3><a href="<s:url action="addCat"> <s:param name="clicked_id"><s:property value="clicked_id"/></s:param></s:url>">Add Category</a></h3>
</s:elseif>
<s:elseif test="level==3">
<h3> List of products in leaf category</h3>
<s:iterator value="products">

<li><a href="<s:url action="showproduct_admin"> <s:param name="prod_name"><s:property /></s:param><s:param name="catid"><s:property value="clicked_id"/></s:param></s:url>"> <s:property/></a></li></s:iterator> </ul>
<h3><a href="<s:url action="addProd"> <s:param name="clicked_id"><s:property value="clicked_id"/></s:param></s:url>">Add Product</a></h3>
</s:elseif>



</body>
</html>