<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>index</title>

<style type="text/css">
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

.change1 {
	position:fixed;
	right:500px;
}

.change1{
	color: DodgerBlue
}

.change1:hover {
	color: black
}
</style>
</head>
<body>

	<div style="float: right; width: 100%;">

		<div style="float: left; width: 20%;">
			<ul>
				<s:iterator value="subCategoryList">
					<li><s:property value="categoryName" /></li>
				</s:iterator>
			</ul>
		</div>

		<div style="float: right; width: 80%;">

			<div style="float: right; width: 40%;">
			<a class="change1" id="link" >Add to Wishlist</a>
				<input type="hidden" id="prodId"
					value="<s:property value="product.productId"/>"> <b><s:property
						value="product.productName" /></b> <br>
				<s:iterator value="product.productEAV">
					<br>
					<s:property value="attributeName" /> : 
			<s:property value="attributeValue" />
				</s:iterator>
				<br> <br> <br> <br>

				<!-- Button trigger modal -->
				<input type="submit" class="btn btn-primary btn-lg"
					data-toggle="modal" data-target="#cart" value="Buy Now" id="buyNow" />

			</div>
			<div style="float: left; width: 50%;">

			<s:iterator value="product.productEAV">
						<s:if test="attributeName=='Image_Path'"> 
				<img src="<s:property value="attributeValue"/>" alt="340x340" height="80" width="80">
				</s:if>
				</s:iterator>

                
			</div>

		</div>

	</div>

	<div class="modal fade" id="cart" tabindex="-1">
		<div class="modal-dialog" style="width: 900px; height: 500px;">
			<div class="modal-content" style="height: 500px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						Cart (<label id="noOfItems"></label>)
					</h4>

				</div>
				<div class="modal-body" id="noItemsBody"
					style="overflow-y: auto; height: 250px; padding-left: 2%; padding-right: 2%; border: medium; border-color: blue; top: 20%; left: 40%">No
					Items in cart</div>

				<div class="modal-body ">
					<div id="bod"
						style="overflow-y: auto; height: 250px; padding-left: 2%; padding-right: 2%"></div>
				</div>
				<div class="modal-footer" style="bottom: 0; height: 100px;">
					<div style="float: right" id="amountDiv">
						Total Amount Payable : Rs <label id="total"></label>
					</div>
					<br> <br>
					<button type="button" class="btn btn-default" data-dismiss="modal">Continue
						Shopping</button>
					<a href="placeorder" class="btn btn-primary" id="order">Place
						Order</a>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function showcart(data) {
			var length = data.cartItems.length;
			$("#noOfItems").html(length);
			if (length == 0) {
				$("#noItemsBody").css("visibility", "visible");
				$("#bod").css("visibility", "hidden");
				$("#order").css("visibility", "hidden");
				$("#amountDiv").css("visibility", "hidden");
			} else {
				$("#noItemsBody").css("visibility", "hidden");
				$("#order").css("visibility", "visible");
				$("#bod").css("visibility", "visible");
				$("#amountDiv").css("visibility", "visible");
			}

			$("#bod").empty();
			$("#bod").append(
					"<table id =tbcart class='table table-striped'>"
							+ "<thead><tr>" + "<th></th><th>Product Name</th>"
							+ "<th>Quantity</th>" + "<th>Price</th>"
							+ "<th>Subtotal</th><th>&nbsp;</th>"
							+ "</tr> </thead><tbody>");
			for ( var i = 0; i < length; i++) {
				$("#tbcart")
						.append(
								"<tr>"
										+ "<td>"
										+ "<img src='http://lorempixel.com/140/140/' height='60' width='40'>"
										+ "</td>"
										+ "<td>"
										+ data.productList[i].productName
										+ "</td>"
										+ "<td class='colqty'><input class='change' maxlength='2' pid='"+data.cartItems[i].productId+"'type='text' value="
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
			$("#bod").append("</tbody></table>");
			$("#total").html(data.currentCart.totalAmount);

			$(".delete").click(function(event) {
				var elem = event.currentTarget;
				var prodId = $(elem).attr("pid");

				$.ajax({
					type : 'POST',
					url : 'deleteCart?productId=' + prodId,
					success : function(data) {
						console.log(data);
						$("#bod").empty();
						showcart(data);

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
				var qt = $(".change").val();
				alert(prodId + "   " + qt);
			});
		}

		$("#buyNow").click(function() {
			$.ajax({
				type : 'POST',
				url : 'addCart?productId=' + $("#prodId").val(),
				success : function(data) {
					console.log("BUY");
					console.log(data);
					showcart(data);

				}
			});

		});

		$("#link").click(function() {
			var id = $("#prodId").val();

			$.ajax({
				type : 'GET',
				url : 'wishlistadd?prodId=' + id,

				success : function(data) {
					// Your Code here

					$("#link").html(data.message);
					$("#link").unbind();
					$("#redirect").click(function() {

					});

				}
			});

		});
	</script>
	<script src="asset/JS/jquery.js"></script>
	<script src="asset/JS/cartScript.js"></script>
</body>
</html>

