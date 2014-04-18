function showcart(data) {
	var length = data.cartItems.length;
	//alert("Hiiiiii");
	$("#noOfItems").html(length);
	if (length == 0) {
		
		$("#noItemsBody").css("visibility", "visible");
		$("#bod").css("visibility", "hidden");
		$("#order").css("visibility", "hidden");
		$("#amountDiv").css("visibility", "hidden");
	} else {
		//--alert(length);
		$("#noItemsBody").css("visibility", "hidden");
		$("#order").css("visibility", "visible");
		$("#bod").css("visibility", "visible");
		$("#amountDiv").css("visibility", "visible");
	}

	$("#bod").empty();
	$("#bod").append(
			"<table id =tbcart class='table table-striped'>" + "<thead><tr>"
					+ "<th></th><th>Product Name</th>" + "<th>Quantity</th>"
					+ "<th>Price</th>" + "<th>Subtotal</th><th>&nbsp;</th>"
					+ "</tr> </thead><tbody>");
	for (var i = 0; i < length; i++) {
		$("#tbcart")
				.append(
						"<tr>"
								+ "<td>"
								+ "<img src='"+data.productList[i].imagePath +"' height='60' width='40'>"
								+ "</td>" + "<td>"
								+ data.productList[i].productName
								+ "</td>"
								+ "<td class='colqty'><input class='change' maxlength='2' pid='"
								+ data.cartItems[i].productId
								+ "'type='text' value="
								+ data.cartItems[i].quantity
								+ ">"
								+ "<a style='color : green; visibility: hidden; display: inline;' href='#' class='savQty' "
								+ "pid='"
								+ data.cartItems[i].productId
								+ "'type='text' >Save</a></td>"
								+ "<td>"
								+ data.cartItems[i].price
								+ "</td>"
								+ "<td>"
								+ data.cartItems[i].subtotal
								+ "</td>"
								+ "<td><button type='button' pid='"
								+ data.cartItems[i].productId
								+ "' class='close delete' id='itemDelete'>"
								+ "&times;" + "</button>" + "</td>" + "</tr>");
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
				//alert(prodId);
				showcart(data);

			}
		});

	});

	$(".change").click(function(event) {
		console.log($(this).siblings());
		$(this).siblings().filter(".savQty").css("visibility", "visible");
		// $(".savQty").css("visibility", "visible");
	});

	$(".savQty").click(function(event) {
		// console.log(event.currentTarget);
		var elem = event.currentTarget;
		var prodId = $(elem).attr("pid");
		// console.log($(elem).siblings().filter(".change"));
		var quantity = $(elem).siblings().filter(".change").val();
		// alert(prodId + " " + quantity);
		// alert("editCart?productId=" + prodId + "quantity=" + quantity);
		$.ajax({
			type : 'POST',
			url : 'editCart?productId=' + prodId + '&quantity=' + quantity,
			success : function(data) {
				console.log("BUY");
				console.log(data);
				showcart(data);

			}
		});
	});
}



$(document).ready(function() {

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
	
	$("#btnCart").click(function() {
		$.ajax({
			type : 'POST',
			url : 'displayCart',
			success : function(data) {
				showcart(data);

			}
		});

	});
});
