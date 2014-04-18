<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NET BANKING</title>
</head>
<body>
<h1>Welcome to <s:property value ="bank"/> bank</h1>
<h2>Make payment</h2>
<s:if test ="%{#errormessage=='successfull!!'}">
<h1>Success......</h1>
</s:if>
<s:else>
<form action="netbankpay" method="post">
		
			<label for="accountid">Username</label>
			 <input type="text"
				id="accountid" name="accountid" required ="required" value="" onfocus="inputFocus(this)"
				onblur="inputBlur(this)" /><br> 
				<label for="Pass" id="label">Password</label>
				<input type="password" id="accountpass" 
				name="accountpass" required ="required" onfocus="inputFocus(this)"
				onblur="inputBlur(this)" /> <br /> 
				
					<br> <input type="submit" 
						value="Pay" />
				
		
	</form>
	<s:property value="errormessage"></s:property>
</s:else>

</body>
</html>