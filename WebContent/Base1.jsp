<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:insertAttribute name="title" /></title>
<style type="text/css">
html,body {
	height: 100%;
}

.wrapper {
	min-height: 100%;
	height: auto !important;
	height: 100%;
	margin: 0 auto -4em;
}
</style>
</head>
<body>
	<tiles:insertAttribute name="menu" />
	<div class="container">
	    <div class="row">
			<div class="col-md-4" >
				<tiles:insertAttribute name="sidebar" />
			</div>
			<div class="col-md-8" >
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>

		<tiles:insertAttribute name="footer" />
	    
	

</body>
</html>