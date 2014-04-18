<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Flipkart display product</title>

<script type="text/javascript">
function onlyText()
{
	var element_name = document.getElementById("string").value;
	var letters = /^[A-Za-z]+$/;  
	
	   if(element_name.match(letters))  
	     {  
	      return true;  
	     }  
	   else  
	     {  
	     alert("Only letters");  
	     return false;  
	     }  
	
	
	}
function textAndNumbers()
{
	var element_name = document.getElementById("textNum").value;
	var letters = /^[0-9a-zA-Z]+$/;  
	
	   if(element_name.match(letters))  
	     {  
	      return true;  
	     }  
	   else  
	     {  
	     alert("Only text and numbers");  
	     return false;  
	     }  
	
	
	}
function stock()
{
	var element_name = document.getElementById("stock").value;
	var letters =  /^[0-9]+$/; 
	
	   if(element_name.match(letters))  
	     {  
	      return true;  
	     }  
	   else  
	     {  
	     alert("Only numbers");  
	     return false;  
	     }  
	
	
	}
function onlyNum()
{
	var element_name = document.getElementById("num").value;
	var letters =  /^[0-9]+$/; 
	
	   if(element_name.match(letters))  
	     {  
	      return true;  
	     }  
	   else  
	     {  
	     alert("Only numbers");  
	     return false;  
	     }  
	
	
	}
function thresh()
{
	var element_name = document.getElementById("thresh").value;
	var letters = /^[0-9]+$/; 
	
	   if(element_name.match(letters))  
	     {  
		   var stock = document.getElementById("stock").value;
		   if(stock<element_name)
			   alert("Please provide threshold less than stock or update stock");
		   else
	      return true;  
	     }  
	   else  
	     {  
	     alert("Only numbers");  
	     return false;  
	     }  
	
	
	}
</script>


</head>

<a href="<s:url action="showmenu"/>"> Main Menu> </a><s:iterator value="navigation"> <a href="<s:url action="showcatlist"><s:param name="clicked_id"><s:property value="category_id"/></s:param></s:url>"><s:property value="catNavName"/></a></s:iterator>

<body>
<h3>Product details</h3>
<s:form action="editProd" >
<s:hidden name="prodname" value="%{prod_name}"/>
<s:iterator value="dispProd">

<s:if test="attname=='Price'">
<s:textfield label="%{attname}" name="attvalues" value="%{attvalue}" required="true"  />
</s:if>
<s:elseif test="attname=='Size'">
<s:textfield label="%{attname}" name="attvalues" value="%{attvalue}"  />
</s:elseif>
<s:elseif test="attname=='Image_Path'">
<s:textfield label="%{attname}" name="attvalues" value="%{attvalue}" required="true" />
</s:elseif>

<s:elseif test="attname=='General_Desc'">
<s:textfield label="%{attname}" name="attvalues" value="%{attvalue}" required="true" />
</s:elseif>

<s:elseif test="attname=='Stock'">
<s:textfield label="%{attname}" name="attvalues" value="%{attvalue}" required="true" />
</s:elseif>
<s:elseif test="attname=='Threshold'">
<s:textfield label="%{attname}" name="attvalues" value="%{attvalue}" required="true" />
</s:elseif>

<s:else>
<s:textfield label="%{attname}" name="attvalues" value="%{attvalue}" />
</s:else> 
</s:iterator>
<s:submit value="Edit Product"/>
</s:form> 
</body>
</html>