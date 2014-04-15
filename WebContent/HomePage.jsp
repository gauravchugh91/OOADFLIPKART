<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flipkart</title>
</head>
<body style="background-color: #F5F2F6;">

<div style="float: center; width: 100%;">
<div style="float: left; width: 80%;">

<div id="myCarousel" class="carousel slide" style="padding: 12px; border-color: #000000;border-width: 12px;outline-color: black;color: #000000";">
      <!-- Indicators -->
      <ol class="carousel-indicators" style="padding: 12px; border-color: black;border-width: 12px;outline-color: black;color: black;">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner" >
        <div class="item active">
          <img src="assets/images/camera.jpg" data-src="holder.js/100%x500/auto/#777:#7a7a7a/text:First slide" alt="First slide">
          <div class="container">
            <div class="carousel-caption">
              <font color="#000000"><h1>Available On Best Price</h1></font>
			</div>
          </div>
        </div>
        <div class="item">
          <img src="assets/images/mobile.jpg" data-src="holder.js/100%x500/auto/#777:#7a7a7a/text:Second slide" alt="Second slide">
          <div class="container">
            <div class="carousel-caption">
              <font color="#000000"><h1>Hurry Now</h1></font>
			</div>
          </div>
        </div>
        <div class="item">
          <img src="assets/images/shoes.jpg" data-src="holder.js/100%x500/auto/#777:#7a7a7a/text:Third slide" alt="Third slide">
          <div class="container">
            <div class="carousel-caption">
              <font color="#000000"><h1>New Arrivals</h1></font>
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
</div>

	

	<br>
	<br>
	<div style="background-color: #2E2B2B; height: 40" >
	<font color="white"><h3>Electronics</h3></font>
	</div>
	<div id="myCarousel2" class="carousel slide">
      <!-- Indicators -->
      <ol class="carousel-indicators" >
        <li data-target="#myCarousel2" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel2" data-slide-to="1"></li>
        <li data-target="#myCarousel2" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="item active" style="width: 100%">
        <div style="float: left; width: 20%">
        <h4>Buy Now</h4>
        </div>
        <s:iterator value="electronicsList">
			
				<div style="float: left; width: 20%">
				
					<a class="button" href="showProduct?productId=<s:property value="productId" />" style="background-color: #ffffff;">
					<s:iterator value="productEAV">
						<s:if test="attributeName=='Image_Path'"> 
				<img src="<s:property value="attributeValue"/>" height="120" width="120">
				</s:if>
				</s:iterator>
						<br>
						<font color="#000000" size="2">
						<b><s:property value="productName" /></font> </b> <br> 
					</a>
				</div>
			</s:iterator> 
			<div style="float: left; width: 20%">
        <h4>Buy Now</h4>
        </div>  
        </div>
        <div class="item" style="width: 100%">
          <s:iterator value="electronicsList">
			
				<div style="float: left; width: 20%">
				
					<a class="button" href="showProduct?productId=<s:property value="productId" />" style="background-color: #ffffff;">
					<s:iterator value="productEAV">
						<s:if test="attributeName=='Image_Path'"> 
				<img src="<s:property value="attributeValue"/>" height="120" width="120">
				</s:if>
				</s:iterator>
						<br>
						<font color="#000000" size="2">
						<b><s:property value="productName" /></font> </b> <br> 
					</a>
				</div>
			</s:iterator>          
        </div>
        <div class="item" style="width: 100%">
          <s:iterator value="electronicsList">
			
				<div style="float: left; width: 20%">
				
					<a class="button" href="showProduct?productId=<s:property value="productId" />" style="background-color: #ffffff;">
					<s:iterator value="productEAV">
						<s:if test="attributeName=='Image_Path'"> 
				<img src="<s:property value="attributeValue"/>" height="120" width="120">
				</s:if>
				</s:iterator>
						<br>
						<font color="#000000" size="2">
						<b><s:property value="productName" /></font> </b> <br> 
					</a>
				</div>
			</s:iterator>           
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel2" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
      <a class="right carousel-control" href="#myCarousel2" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
    </div>

    
    <br><br>
    <div style="background-color: #2E2B2B; height: 40" >
	<font color="white"><h3>Buy Now</h3></font>
	</div>
	<br><br>
    <div style="background-color: white; height: 180">
   			<center>
		<br> <br>
		<div class="container">

			<s:iterator value="electronicsList">
			
				<div class="container col-md-3">
				
					<a class="button" href="showProduct?productId=<s:property value="productId" />" style="background-color: #ffffff;">
					<s:iterator value="productEAV">
						<s:if test="attributeName=='Image_Path'"> 
				<img src="<s:property value="attributeValue"/>" alt="340x340" height="160" width="130">
				</s:if>
				</s:iterator>
						<br>
						<br>
						<font color="#000000" size="3">
						<b><s:property value="productName" /></font> </b> <br> 
					</a>
				</div>
			</s:iterator>
		</div>
	</center>
	 </div>
    <br><br><br>
    
    <div style="background-color: #2E2B2B; height: 40" >
	<font color="white"><h3>Books</h3></font>
	</div>
	<div id="myCarousel3" class="carousel slide" style="background-color: white;">
      <!-- Indicators -->
      <ol class="carousel-indicators" >
        <li data-target="#myCarousel3" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel3" data-slide-to="1"></li>
        <li data-target="#myCarousel3" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="item active" style="width: 100%">
        <div style="float: left; width: 20%">
        <h4>Buy Now</h4>
        </div>
        <s:iterator value="booksList">
			
				<div style="float: left; width: 20%">
				
					<a class="button" href="showProduct?productId=<s:property value="productId" />" style="background-color: #ffffff;">
					<s:iterator value="productEAV">
						<s:if test="attributeName=='Image_Path'"> 
				<img src="<s:property value="attributeValue"/>" height="120" width="120">
				</s:if>
				</s:iterator>
						<br>
						<font color="#000000" size="2">
						<b><s:property value="productName" /></font> </b> <br> 
					</a>
				</div>
			</s:iterator>     
        </div>
        <div class="item" style="width: 100%">
          <s:iterator value="booksList">
			
				<div style="float: left; width: 20%">
				
					<a class="button" href="showProduct?productId=<s:property value="productId" />" style="background-color: #ffffff;">
					<s:iterator value="productEAV">
						<s:if test="attributeName=='Image_Path'"> 
				<img src="<s:property value="attributeValue"/>" height="120" width="120">
				</s:if>
				</s:iterator>
						<br>
						<font color="#000000" size="2">
						<b><s:property value="productName" /></font> </b> <br> 
					</a>
				</div>
			</s:iterator> 
			<div style="float: left; width: 20%">
        <h4>Buy Now</h4>
        </div>         
        </div>
        <div class="item" style="width: 100%">
          <s:iterator value="booksList">
			
				<div style="float: left; width: 20%">
				
					<a class="button" href="showProduct?productId=<s:property value="productId" />" style="background-color: #ffffff;">
					<s:iterator value="productEAV">
						<s:if test="attributeName=='Image_Path'"> 
				<img src="<s:property value="attributeValue"/>" height="120" width="120">
				</s:if>
				</s:iterator>
						<br>
						<font color="#000000" size="2">
						<b><s:property value="productName" /></font> </b> <br> 
					</a>
				</div>
			</s:iterator>          
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel3" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
      <a class="right carousel-control" href="#myCarousel3" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
    </div>
    
    <br><br>
    <div style="background-color: #2E2B2B; height: 40" >
	<font color="white"><h3>Fashion</h3></font>
	</div>
	<br><br>
    <div style="background-color: white;">
   			<center>
		<br> <br>
		<div class="container">

			<s:iterator value="fashionList">
			
				<div class="container col-md-3">
				
					<a class="button" href="showProduct?productId=<s:property value="productId" />" style="background-color: #ffffff;">
					<s:iterator value="productEAV">
						<s:if test="attributeName=='Image_Path'"> 
				<img src="<s:property value="attributeValue"/>" alt="340x340" height="160" width="130">
				</s:if>
				</s:iterator>
						<br>
						<br>
						<font color="#000000" size="3">
						<b><s:property value="productName" /></font> </b> <br> 
					</a>
				</div>
			</s:iterator>
		</div>
	</center>
	 </div>
    <br><br><br>

    
    
    
</div></div>
</body>
</html>