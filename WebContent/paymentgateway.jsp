<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Payment gateway</title>
</head>
<body>
<h1>payment</h1>
	<s:if test="statusflag!=1">
	<s:form  action="pay" method ="post" >
  <%-- <s:textfield label="Password" name="password" /> --%>
  <label for="Password" id="label">Password</label>
	<input type="password" id="password" name="password"  onfocus="inputFocus(this)"
				onblur="inputBlur(this)" /> <br /> 
  <%-- <s:property value="bank card id"/> --%>
  <s:hidden name="bankcardid" value="%{bankcardid}"/> 
  <s:submit />
</s:form>	
<s:if test="statusflag==2">
<h2>Please Enter Valid Details</h2>
</s:if>
</s:if>
<s:else>
<h2> Payment Successful </h2>
</s:else>
</body>
</html>