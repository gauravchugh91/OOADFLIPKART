<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Flipkart Header</title>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="assets/css/theme.css" rel="stylesheet">

<style type="text/css">
.fk-font-12 {
	font-size: 12px;
	margin: 5px;
}

.link-list>li {
	border-right: 1px solid;
	border-color: #ADD3E8;
	display: table-cell;
	vertical-align: middle;
	padding-left: 10px;
	padding-right: 10px;
	vertical-align: middle;
	display: table-cell;
	vertical-align: middle;
}

a {
	color: #ADD3E6
}

a:HOVER {
	color: white;
}

.searchBarText {
	border: 0 none;
	outline: 0 none;
	height: 30px;
	width: 70%;
	font-size: 13px;
	font-family: arial, tahoma, verdana, sans-serif;
}

.searchButton {
	background-color: #fdd922;
	border: 1px solid #e0bc27;
	border-radius: 0 2px 2px 0;
	color: #565656;
	text-transform: uppercase;
	line-height: 17px;
	height: 30px;
	width: 100px;
	cursor: pointer;
	font-size: 13px;
	font-weight: bold;
	font-family: arial, tahoma, verdana, sans-serif;
	appearance: button;
	-moz-appearance: button;
	-webkit-appearance: button;
	cursor: pointer;
}

.cartbutton {
	margin-left: 20px;
	width: 100px;
	font-size: 13px;
	font-family: arial, tahoma, verdana, sans-serif;
}

.modal-dialog {
	padding-top: 10%;
}

.close:HOVER {
	color: #ff0000;
}

.change {
	width: 30px;
}

.modal-body {
	visibility: hidden;
	display: inline;
	overflow-y: scroll;
	height: 250px;
}
</style>
</head>
<!-- 
				
 -->
 <script type="text/javascript">
 
 
 </script>
<body>
	<div class="page-container">
		<div class="navbar navbar-inverse navbar-fixed-top "
			id="mainHeaderDiv"
			style="background-color: #005387; padding-bottom: 15px; margin: 0">
			<div class="container " id="mainHeaderTopDiv">
				<div id="topHeaderContentDiv" class="container">
					<div class="navbar-header col-md-3">
						<a class="navbar-right" style="padding-top: 5%"
							href="showHomePage"><img src="assets/images/flip.png" /></a>
					</div>
					<div class="container col-md-8 " id="search">
						<s:if test="isLoggedIn==1">
							<div class="container nav-collapse collapse"
								style="display: block;">
								<ul class="fk-font-12 navbar-right link-list">
									<li><a href="aboutUs"> 24x7 Customer Care </a></li>
									<li><a href="trackOrder">Track Order</a></li>
									<li><a href="#" class="dropdown-toggle js-activated">
											Hi! <s:property value="email" /> <b class="caret"></b>
									</a>
										<ul class="dropdown-menu">
											<li class="divider"></li>
											<li><a href="account">Account</a></li>
											<li><a href="orders">Orders</a></li>
											<li><a href="wallet">Wallet</a></li>
											<li><a href="displaywishlist">Wishlist</a></li>
											<li><a href="logout">Logout</a></li>
										</ul></li>
								</ul>
							</div>

						</s:if>
						<s:else>
							<div class="container nav-collapse collapse"
								style="display: block;">
								<ul class="fk-font-12 navbar-right link-list">
									<li><a href="aboutUs"> 24x7 Customer Care </a></li>
									<li><a href="trackOrder">Track Order</a></li>
									<li><a href="#" class="sign">SignUp</a></li>

									<li><a href="#" class="log" data-toggle="modal"
										data-target="#LoginModal">Login</a></li>
								</ul>
							</div>
						</s:else>
						<form action="search" method=post>
						<div id="searchIconDiv" style="margin-top: 5px;">
						
							<div class="container col-md-8" style="background-color: white">
								<img src="assets/images/searchicon.jpg"
									style="width: 19px; height: 19px;" /> <input type="text"
									class="searchBarText" name="searchBar"
									placeholder="Search for a product, category or brand"
									autofocus="autofocus" />
							</div>
							<input class="searchButton" type="submit" value="Search" />
							
							 <input type="submit" class="btn btn-sm btn-primary cartbutton"
								data-toggle="modal" data-target="#cartHome" id="btnCart"
								value="CART" />
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="navbar navbar-default navbar-static-top"
			style="background-color: #0a3151">
			<div class="container" style="width: 60%">
				<ul class="nav navbar-nav">
					<s:iterator value="rootCategoryList">
						<li class="dropdown"><a href="#"
							class="dropdown-toggle js-activated"><s:property
									value="categoryName" /> <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<s:iterator value="subCategoryList">
									<li class="divider"></li>
									<li><a
										href="browse?category=<s:property value="categoryid"/>"><s:property
												value="categoryName" /></a></li>
								</s:iterator>
							</ul></li>
					</s:iterator>
				</ul>

			</div>
		</div>
	</div>
	<div id="LoginModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">LOGIN</h4>
				</div>
				<div class="modal-body" style="visibility: visible;">
					<div class="container">
						<form name="Loginform" action="login" method="post"
							onsubmit="regvalidate()">
							<table class="spacer">
								<tr>
									<td><label for="email">Email :</label></td>
									<td><input type="text" name="email" /></td>
								</tr>
								<tr>
									<td><label for="password">Password :</label></td>
									<td><input type="password" name="password" /></td>
								</tr>
							</table>
							<input type="submit" class="btn btn-primary" value="LOGIN" />
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

<div id="SignUpModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">SIGN UP!</h4>
            </div>
           
            <div >
                
                <form name = "SignUpform" action="signup" method="post">
                <table>
                <tr>
                
                   <td>  <label for="email">Enter Email :</label></td>
                   <td>  <input type="text" name="email"/></td>
                               <br/><br/>
                </tr>
                <tr><td>  </td><td> </td></tr>
                <tr><td> </td><td> </td></tr>
                <tr>
                   
                   <td> <label for="assign_password">Enter Password :</label></td>
                   <td><input type="password" name ="assign_password"/></td>
                 
                </tr>   
                   <br/><br/>
                <tr><td>  </td><td> </td></tr>
                <tr><td> </td><td> </td></tr>
                <tr>   
                
                   <td> <label for="reTypePass">Retype Password :</label></td>
                   <td><input type="password" name ="reTypePass"/></td>
                
                </tr>
                <tr><td> </td><td>  </td></tr>
                <tr><td> </td><td>  </td></tr>
                </table>   
                  <input type="submit" class="btn btn-primary" value="Sign Up!"/>
                </form>
                
            </div> 
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


	<div class="modal fade" id="cartHome" tabindex="-1">
		<div class="modal-dialog" style="width: 900px; height: 500px;">
			<div class="modal-content" style="height: 500px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						Cart (<label id="itemCount"></label>)
					</h4>

				</div>
				<div class="modal-body" id="noItem"
					style="overflow-y: auto; height: 250px; padding-left: 2%; padding-right: 2%; border: medium; border-color: blue; top: 20%; left: 40%">No
					Items in cart</div>

				<div class="modal-body ">
					<div id="itemBody"
						style="overflow-y: auto; height: 250px; padding-left: 2%; padding-right: 2%"></div>
				</div>
				<div class="modal-footer" style="bottom: 0; height: 100px;">
					<div style="float: right" id="totalAmountDiv">
						Total Amount Payable : Rs <label id="totalAmount"></label>
					</div>
					<br> <br>
					<button type="button" class="btn btn-default" data-dismiss="modal">Continue
						Shopping</button>
					<a href="placeorder" class="btn btn-primary" id="orderPlace">Place
						Order</a>
				</div>
			</div>
		</div>
	</div>

	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.js"></script>
	<script src="assets/js/bootstrap-hover-dropdown.js"></script>

	<script>
	
	$(document).ready(function(){
		 $('a.sign').click(function() {
				$("#SignUpModal").modal('show');
			});
		 });
		 
	
	
	
		$('.js-activated').dropdownHover().dropdown();
		// 		$('a.log').click(function() {
		// 			$("#LoginModal").modal('show');
		// 		});

		function showMyCart(data) {
			var length = data.cartItems.length;
			$("#itemCount").html(length);
			if (length == 0) {
				$("#noItem").css("visibility", "visible");
				$("#itemBody").css("visibility", "hidden");
				$("#orderPlace").css("visibility", "hidden");
				$("#totalAmountDiv").css("visibility", "hidden");
			} else {
				$("#noItem").css("visibility", "hidden");
				$("#orderPlace").css("visibility", "visible");
				$("#itemBody").css("visibility", "visible");
				$("#totalAmountDiv").css("visibility", "visible");
			}

			$("#itemBody").empty();
			$("#itemBody").append(
					"<table id =tablecart class='table table-striped'>"
							+ "<thead><tr>" + "<th></th><th>Product Name</th>"
							+ "<th>Quantity</th>" + "<th>Price</th>"
							+ "<th>Subtotal</th><th>&nbsp;</th>"
							+ "</tr> </thead><tbody>");
			for (var i = 0; i < length; i++) {
				$("#tablecart")
						.append(
								"<tr>"
										+ "<td>"
										+ "<img src='http://lorempixel.com/140/140/' height='60' width='40'>"
										+ "</td>"
										+ "<td>"
										+ data.productList[i].productName
										+ "</td>"
										+ "<td class='tdQty'><input class='change' pid='"+data.cartItems[i].productId+"'type='text' value="
											+ data.cartItems[i].quantity
											+ ">"
										+ "<a style='color : green; visibility: hidden; display: inline;' href='#' class='savQty' "
											+ "pid='"+data.cartItems[i].productId+"'type='text' >Save</a></td>"
										+ "<td>"
										+ data.cartItems[i].price
										+ "</td>"
										+ "<td>"
										+ data.cartItems[i].subtotal
										+ "</td>"
										+ "<td><button type='button' pid='"+data.cartItems[i].productId+"' class='close delete' id='itemDelete'>"
										+ "&times;" + "</button>" + "</td>"
										+ "</tr>");
			}
			$("#itemBody").append("</tbody></table>");
			$("#totalAmount").html(data.currentCart.totalAmount);

			$(".delete").click(function(event) {
				var elem = event.currentTarget;
				var prodId = $(elem).attr("pid");

				$.ajax({
					type : 'POST',
					url : 'deleteCart?productId=' + prodId,
					success : function(data) {
						console.log(data);
						$("#itemBody").empty();
						showMyCart(data);

					}
				});

			});
			$(".change").click(function(event) {
				var elem = event.currentTarget;
				var prodId = $(elem).attr("pid");
				$(".savQty").css("visibility", "visible");

			});

			$(".savQty").click(function(event) {
				var elem = event.currentTarget;
				var prodId = $(elem).attr("pid");
				var qty = $(".change").attr(value);
				alert(prodId + "		" + qty);
				// 				$.ajax({
				// 					type : 'POST',
				// 					url : 'editCart?productId=' + prodId,
				// 					success : function(data) {
				// 						// 												console.log(data);
				// 						$("#bod").empty();
				// 						showcart(data);

				// 					}
				// 				});
				$("#savQty").css("visibility", "hidden");
			});
		}

		$("#btnCart").click(function() {
			$.ajax({
				type : 'POST',
				url : 'displayCart',
				success : function(data) {
					showMyCart(data);

				}
			});

		});
	</script>
</body>
</html>

