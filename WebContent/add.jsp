<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br>
   <s:div>Enter a new shipping address:</s:div>
   <br>
   <s:form action="addaddress" method="post" enctype="multipart/form-data" theme="simple" >
   
   <label for="name"> Name:    </label>
   <s:textfield  key="Name" name="name" required="required"/>
  <br>
   
   <label for="pin"> Pincode: </label>
   <s:textfield key="Pincode" name="pincode" required="required"/>
	<br>
	
 <label for="address"> Address: </label>
<br>  <textarea name="Address" rows="5" cols="40" required="required" maxlength="200"></textarea> </br>
 
  <label for="city"> City:</label>
 <s:textfield   name="city" required="required"/>
 <br>
 <label for="state"> State:</label>
 <s:textfield  name="state" required="required"/>
 <br>
 <label for="country"> Country:</label>
 <s:textfield   name="country" required="required"/>
 <br>
 
 
 <label for="phone"> PhoneNo: +91</label>
 <s:textfield   name="phone" required="required"/>
 
 
 <br>
 <button id="2"> Save & Continue</button>
  <!--  <input  id="2" type="submit" value="Save & Continue" /> -->
   
   </s:form>
</body>
</html>