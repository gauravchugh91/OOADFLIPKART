<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flipkart Admin Account</title>
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
	     document.insertProd.getElementById("num").focus();
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
<body>

<h3>Enter product details:</h3>

<s:form action="insertProd" id="insertProd" name="insertProd"> <s:textfield label="Product name" id="string" name="productname" require="true" />

<s:iterator value="Attributes">
<s:if test="AttrName=='Price'">
<s:textfield label="%{AttrName}" name="AttValues" required="true" id="num" onblur="onlyNum()" />
</s:if>
<s:elseif test="AttrName=='Size'">
<s:textfield label="%{AttrName}"  name="AttValues"   />
</s:elseif>
<s:elseif test="AttrName=='Image_Path'">
<s:textfield label="%{AttrName}" name="AttValues" id="string" required="true" onblur="onlyText()"/>
</s:elseif>
<s:elseif test="AttrName=='General_Desc'">
<s:textfield label="%{AttrName}" name="AttValues" id="textNum" required="true" />
</s:elseif>
<s:elseif test="AttrName=='Stock'">
<s:textfield label="%{AttrName}" name="AttValues" required="true" id="stock" onblur="stock()" />
</s:elseif>
<s:elseif test="AttrName=='Threshold'">
<s:textfield label="%{AttrName}" name="AttValues" required="true" id="thresh" onblur="thresh()" />
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