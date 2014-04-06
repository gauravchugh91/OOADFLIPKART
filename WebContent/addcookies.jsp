<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ad Cookies</title>
<link href="assets/css/bootstrap.css" rel="stylesheet">
<style>
label {
	width: 220px;
	display: inline-block;
}
</style>
</head>
<body>
	<div class="container centered ">
	<h2>Add Data to the cookies</h2>
	<br>
	<br>
	<form>
		<label>Name</label> <input type="text" name="name" /><br> <label>Age</label>
		<input type="text" name="age" /><br> <label>Gender</label> <input
			type="text" name="gender" /><br> <label>Country</label> <input
			type="text" name="country" /><br> <input type="submit"
			name="add" class="btn btn-sm btn-success text-center" value="Add Cookies"
			onclick='this.form.action="add";' /> <input type="submit"
			name="show" class="btn btn-sm btn-primary text-center" value="Show Cookies"
			onclick='this.form.action="show";' />
			</form>
	</div>
</body>
</html>