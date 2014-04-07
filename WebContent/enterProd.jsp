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


<s:form action="insertProd"> Please enter product details: <s:textfield label="Product name" name="productname"/>

<s:iterator value="Attributes">
<s:if test="AttrName=='price'">
<s:textfield label="%{AttrName}" name="AttValues" required="true" />
</s:if>
<s:elseif test="AttrName=='size'">
<s:textfield label="%{AttrName}" name="AttValues" required="true" />
</s:elseif>
<s:elseif test="AttrName=='Image_Path'">
<s:textfield label="%{AttrName}" name="AttValues" required="true" />
</s:elseif>
<s:elseif test="AttrName=='General_Desc'">
<s:textfield label="%{AttrName}" name="AttValues" required="true" />
</s:elseif>
<s:elseif test="AttrName=='Stock'">
<s:textfield label="%{AttrName}" name="AttValues" required="true" />
</s:elseif>
<s:elseif test="AttrName=='Threshold'">
<s:textfield label="%{AttrName}" name="AttValues" required="true" />
</s:elseif>
<s:else>
<s:textfield label="%{AttrName}" name="AttValues"/>
</s:else> 
</s:iterator>
<s:submit value="Add product"/>
<s:hidden name="clicked_id" value="%{clicked_id}"/>
</s:form>
</body>
</html>