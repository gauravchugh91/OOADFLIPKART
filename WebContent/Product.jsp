<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="assets/css/bootstrap.css" rel="stylesheet">


<link href="assets/css/theme.css" rel="stylesheet">
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
	position: fixed;
	right: 500px;
}

.change1 {
	color: DodgerBlue
}

.change1:hover {
	color: black
}
</style>

</head>
<body>

<ul>
<s:iterator value="productPath">
<li><a href="search?searchBar=<s:property value="categoryName"/>" style="color: black;"><s:property value="catNavName"/></a></li></s:iterator>
</ul>
<br>
	<div style="float: center; width: 100%;">

			<div style="float: right; width: 40%;">
				<s:if test="%{ #session['userID'] != -1}">
					<a class="change1" id="link">Add to Wishlist</a>
					<br><br>
				</s:if>
				<input type="hidden" id="prodId"
					value="<s:property value="product.productId"/>"> <b><s:property
						value="product.productName" /></b> <br>
				<s:iterator value="product.productEAV">
				<br>
				<s:if test="attributeName!='Image_Path'">

					<s:if test="attributeName=='Discount'">
						<font color="008000" size="4"> <b><s:property
									value="attributeName" /> : <s:property value="attributeValue" />%
								<br> Discount Price: Rs <s:property
									value="product.discountPrice" /></b>
						</font>
					</s:if>
					<s:elseif test="attributeName=='Offer'">
						<font color="#008000" size="4"> <b> <s:property
									value="attributeName" /> : <s:property value="attributeValue" /></b>
						</font>
					</s:elseif>
					<s:else>

						<s:property value="attributeName" /> : 
			            <s:property value="attributeValue" />
					</s:else>
				</s:if>
			</s:iterator>
				<br> <br> <br> <br>
<s:if test="check==1"> 
<!-- Button trigger modal -->
				<input type="submit" class="btn btn-primary btn-lg"
					data-toggle="modal" data-target="#cart" value="Buy Now" id="buyNow" />

			</div>
			
</s:if>
<s:else>
<div style="background-color: #005387;">
<font style="color: white;"><label>Out Of Stock</label></font>
</div>
</s:else>
				
			<div style="float: left; width: 50%;">

				<s:iterator value="product.productEAV">
					<s:if test="attributeName=='Image_Path'">

						<img src="<s:property value="attributeValue"/>" alt="340x340"
							height="350" width="200" />
					</s:if>
				</s:iterator>
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
</body>
</html>

