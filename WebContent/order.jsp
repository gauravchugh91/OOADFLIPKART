<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Flipkart</title>
<link rel="stylesheet" type="text/css"
	href="assets/css/smoothness/jquery-ui-1.10.4.custom.css">
<link rel="stylesheet" type="text/css" href="assets/css/creditcard.css">

<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="assets/css/bootstrap.min.css">

<script src="assets/js/jquery.js"></script>
<script src="assets/js/jquery-ui-1.10.4.custom.min.js"></script>

<script type="text/javascript">
window.history.forward();
function noBack () {window.history.forward();}
</script>

<script>
	$(function() {
		$("#tabs").tabs().addClass("ui-tabs-vertical ui-helper-clearfix");
		$("#tabs li").removeClass("ui-corner-top").addClass("ui-corner-left");
	});
</script>

<style>
.ui-tabs-vertical {
	width: 55em;
}

.ui-tabs-vertical .ui-tabs-nav {
	padding: .2em .1em .2em .2em;
	float: left;
	width: 12em;
}

.ui-tabs-vertical .ui-tabs-nav li {
	clear: left;
	width: 70%;
	border-bottom-width: 1px !important;
	border-right-width: 0 !important;
	margin: 0 -1px .2em 0;
}

.ui-tabs-vertical .ui-tabs-nav li a {
	display: block;
}

.ui-tabs-vertical .ui-tabs-nav li.ui-tabs-active {
	padding-bottom: 0;
	padding-right: .1em;
	border-right-width: 1px;
	border-right-width: 1px;
}

.ui-tabs-vertical .ui-tabs-panel {
	padding: 1em;
	float: right;
	width: 40em;
}
</style>
<style type="text/css">
.address {
	border-style: solid;
	border-width: 2px;
	font-family: "Helvetica Neue", "Helvetica", Helvetica, Arial, sans-serif;
	font-weight: 30px;
	line-height: 10px;
	margin: 0 0 1.25rem;
	position: relative;
	text-decoration: none;
	text-align: center;
	display: inline-block;
	padding-top: 1rem;
	padding-right: 2rem;
	padding-bottom: 1.0625rem;
	padding-left: 2rem;
	font-size: 1rem;
	background-color: #0f92bd;
	border-color: #cccccc;
	color: black;
	-webkit-transition: background-color 300ms ease-out;
	-moz-transition: background-color 300ms ease-out;
	transition: background-color 300ms ease-out;
	font-size: 1rem;
}

/* .address:hover {
	text-decoration: none !important;
	border-style: solid;
	border-width: 2px;
	border-color: #ffd700;
} */
#accordion {
	width: 95%;
	margin-left: auto;
	margin-right: auto;
}
</style>
<script>
	$(function() {
		$("#accordion").accordion();
	});
</script>
<script type="text/javascript">
	$(document).ready(
					function() {
						$("#load").click(function(event) {			
							$('#add1').load('add.jsp');
						});
						});

	
	
	
						//ACCORDION
<%--$("#accordion").accordion({
				header : ".accClass"
			});
			$(".accClass").addClass("ui-state-disabled");

			// Now the hack to implement the disabling functionality
			var accordion = $("#accordion").data("accordion");
			
			accordion._clickHandler = function(event, target) {
				var clicked = $(event.currentTarget || target);
				if (!clicked.hasClass("ui-state-disabled")) {
					this._std_clickHandler(event, target);
				}
			};--%>
	/*  $("#accordion").accordion({
							header : ".accClass"
						}); */

	 /*		accordion._std_clickHandler = accordion._clickHandler;

						var $accordion = $("#accordion").accordion();

						function openNextAccordionPanel() {

							var current = $accordion.accordion("option",
									"active"), maximum = $accordion.find("h2").length, next = current + 1 === maximum ? 0
									: current + 1;
							$accordion.accordion("activate", next);

						}

						$("#section1").removeClass("ui-state-disabled");

						$("#1").click(function() {
						
							$("#section2").removeClass("ui-state-disabled");
							openNextAccordionPanel();
						});

						$("#2").click(function() {
							$("#section3").removeClass("ui-state-disabled");
							openNextAccordionPanel();
						});

						$("#3").click(function() {
							$("#section4").removeClass("ui-state-disabled");
							openNextAccordionPanel();
						});

						$("#4").click(function() {
							alert("Authenticate and Redirect to next page");
						});

					}); */
					
					
					
</script>

<script type="text/javascript">
function debitvalidate()
{
	 var a = document.getElementById("cnum1");
var b = document.getElementById("emonth1");
var c = document.getElementById("eyear1");
var d = document.getElementById("cvv1");
checkvalidate(a,b,c,d);
}
function creditvalidate()
{
var a = document.getElementById("cnum");
var b = document.getElementById("emonth");
var c = document.getElementById("eyear");
var d = document.getElementById("cvv");
checkvalidate(a,b,c,d);
}
</script>





 <script type="text/javascript">
            function checkvalidate(a,b,c,d)
            {
               

                var e = a.value;
                   var f = b.value;
                var g = c.value;
                 var h = d.value ; 
               
                var valid = true;
                if(a.value.length!=16)
                    {
                        alert("Card number should consist 16 digits");
                        valid = false;
                    }
                if(b.value.length!=2)
                {
                    alert("Enter month in format MM");
                    valid = false;
                }
                if(c.value.length!=4)
                {
                    alert("Enter Year in format YYYY");
                    valid = false;
                }
                if(d.value.length!=3)
                {
                    alert("Enter 3 digit cvv number");
                    valid = false;
                }
                   else{
                        if(isNaN(e) || isNaN(f)||isNaN(g) || isNaN(h)){
                            alert("Enter a number");
                    valid = false;}
                } 
                return valid;
            };
   </script>

<script>
	$(function() {
		$("#selectable").selectable();
	});
</script>

<!-- Accordian   Disable script-->

<!--Script for Email  in first  Accordian-->
<script type="text/javascript">
	$(document).ready(function() {

		$("#password").hide();
		$("#label").hide();

		$(":checkbox").change(function() {
			if ($(this).is(":checked")) {
				$("#password").show();
				$("#label").show();
			} else {
				$("#password").hide();
				$("#label").hide();
			}
		});

		$("#save").click(function() {
			var radio_button_value = $(':radio[name=addressid]:checked').val();
		/* 	alert(radio_button_value); */
			$.ajax({
				type : 'POST',
				url : 'existingAdd?addressid=' + radio_button_value,

			});

		});
		
		$("#wallet_pay").click(function() {
		
			$.ajax({
				type : 'POST',
				url : 'walletpay',

			});

		});

		
		
		
		
	});
</script>
<!-- 
<script>

$('#ff').form({
    success:function(data){
        $.messager.alert('Info', data, 'info');
    }
});

</script>
 -->
</head>

<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">

<div style="background-color: #005387;">
<a href="showHomePage"><img src="assets/images/flip.png" /></a>
</div>
<br><br><br>
	<!-----------------------Accordian Division   --------------------------->
	<div id="accordion">
		<h2 id="section1" class="accClass">
			<a href="#">1. EMAIL ID</a>
		</h2>
		<div>
			EMAIL ID
			<s:if test= "userid > 0" >
				<s:property value="emailid" />
			</s:if>
			
			<s:else>
			<s:if test="existingemail==1">
			<h3> Email Already Exist Please Enter Another Email Address !!!! </h3> 
			</s:if>
				<form id="ff" action="Orlogin" method="post">

					<label for="Emailid">Email Address</label> <input type="text"
						id="emailid" name="email" required value=""
						onfocus="inputFocus(this)" onblur="inputBlur(this)" /><br> <label
						for="Password" id="label">Password</label> <input type="password"
						id="password" name="password" value="" onfocus="inputFocus(this)"
						onblur="inputBlur(this)" /> <br />
					<s:checkbox name="my_checkbox" value='false' />
					<label for="Password"> I have flipkart account</label> <br>

					<button id="1">Continue</button>
					<!-- <input type="submit" id="1" value="Continue" /> -->
				</form>
			</s:else>
		</div>


		<h2 class="accClass">
			<a href="#">2. DELIVERY ADDRESS </a>
		</h2>
		<div id="section2">
			DELIVERY ADDRESS
			<div class="container">

				<s:iterator value="addr" status="userStatus">
					<center>
						<div class="container col-md-4">

							<div class="address">
								<br> <label>Name:</label>
								<s:property value="name" />
								<br> <label>Address:</label> <br>
								<%-- 	<div class="addrid"><s:property value="addressid"/></div><br/> --%>

								<s:property value="address" />
								, <br>
								<s:property value="city" />
								, <br>
								<s:property value="state" />
								, <br>
								<s:property value="country" />
								, <br>
								<s:property value="pincode" />
								, <br> <label>Phone:</label>
								<s:property  value="phone" />

							</div>

							<td><s:radio name="addressid" list="#{addressid:''}"
									theme="simple"></s:radio></td>
						</div>
					</center>
				</s:iterator>

				<s:if test="userid!=-1">
					<button id="save">Select Existing Address</button>
				</s:if>



			</div>

			<div>
				<button id="load" style="height: 25px; width: 400px">+ Add
					New Address</button>

			</div>
			<div id="add1"></div>
		</div>


		<h2 id="section3" class="accClass">
			<a href="#">3. ORDER SUMMARY</a>
		</h2>
		<div>
			ORDER SUMMARY:

			<div>

				<s:iterator value="orditm" status="userStatus">
					<table class='table table-striped'>
						<thead>
							<tr>

								<th></th>
								<th>Product Name</th>
								<th>Quantity</th>
								<th>Subtotal</th>
								<th>DeliveryDetails</th>
							<tr>
								<td><img src="<s:property value="imagepath"/>"
									alt="340x340" height="40" width="60" /></td>
								<td><s:property value="productname" /></td>
								<td><s:property value="quantity" /></td>
								<td><s:property value="subtotal" /></td>
								<td><s:property value="deliverydetails" /></td>
							</tr>
					</table>
				</s:iterator>
			</div>
		</div>
		<h2 id="section4" class="accClass">
			<a href="#">4. PAYMENT METHOD</a>
		</h2>
		<div>
			SELECT PAYMENT METHOD :

			<div id="tabs">
				<ul>
					<!-- <li><a href="#tabs-1">CASH ON DELIVERY</a></li> -->
					<li><a href="#tabs-1">NET BANKING</a></li>
					<li><a href="#tabs-2">CREDIT CARD</a></li>
					<li><a href="#tabs-3">DEBIT CARD</a></li>
					<li><a href="#tabs-4">WALLET</a></li>
				</ul>
				<!-- <div id="tabs-1"></div> -->
				<div id="tabs-1">
					<form action="makepayment1">
						<s:select headerKey="-1" headerValue="Select BANK" list="bank"
							name="bank" cssStyle="width:150px" />
						<br> <br>
						<button id="paycard" style="height: 25px; width: 400px">MAKE
							PAYMENT</button>
					</form>
				</div>

				<div id="tabs-2">
					<section class="credit-card visa gr-visa">
						<div class="logo">Credit Card</div>
						<form action="creditcard" method="post" onsubmit="return creditvalidate();">
							<h2>Payment Details</h2>

							<ul class="inputs">
								<li><label>Card Number</label> <input type="text"
									name="cardNumber" value="XXXX XXXX XXXX XXXX"
									class="full gr-input" required id="cnum"/></li>

								<li class="expire last"><label>EXPIRY DATE (VALID
										THRU)</label> <input type="text" name="expireMonth" value="MM"
									size="10" class="month gr-input" required="required" id="emonth"/> <input
									type="text" name="expireYear" value="YYYY" size="10"
									class="year gr-input" required="required" id="eyear" />
									<div class="clearfix"></div></li>
								<li class="cvc-code last"><label>CVC Code</label> <input
									type="text" name="cvcCode" value="174" size="10"
									class="gr-input" required="required" id="cvv" /></li>

								<li><label>NAME ON CARD</label> <input type="text"
									name="cardName" value="NAME" class="full gr-input"
									required="required" /></li>
								<div class="clearfix"></div>
							</ul>
							<button id="paycard" style="height: 25px; width: 400px">
								PAY</button>
							<!-- <input id="paycard" type="submit" value="PAY" /> -->


						</form>
						<div class="watermark">CREDI</div>
					</section>

				</div>
				<div id="tabs-3">
					<section class="credit-card visa gr-visa">
						<div class="logo">Debit Card</div>
						<form action="debitcard" method="post" onsubmit="return debitvalidate();">
							<h2>Payment Details</h2>

							<ul class="inputs">
								<li><label>Card Number</label> <input type="text"
									name="cardNumber" value="XXXX XXXX XXXX XXXX"
									class="full gr-input" required="required" id="cnum1" /></li>

								<li class="expire last"><label>EXPIRY DATE (VALID
										THRU)</label> <input type="text" name="expireMonth" value="MM"
									size="10" class="month gr-input" required="required" id="emonth1"/> <input
									type="text" name="expireYear" value="YYYY" size="10"
									class="year gr-input" required  id="eyear1"/>
									<div class="clearfix"></div></li>
								<li class="cvc-code last"><label>CVC Code</label> <input
									type="text" name="cvcCode" value="XXX" size="10"
									class="gr-input" required="required" id="cvv1"/></li>

								<li><label>NAME ON CARD</label> <input type="text"
									name="cardName" value="NAME" class="full gr-input"
									required="required" /></li>
								<div class="clearfix"></div>
							</ul>
							<button id="paycard" style="height: 25px; width: 400px">
								PAY</button>
							<!-- <input id="paycard" type="submit" value="PAY" /> -->


						</form>
						<div class="watermark">DEB</div>
					</section>

				</div>
				<div id="tabs-4">
			 	<s:if test="walletflag==1"> 
			 	<h3>Wallet Amount:</h3><s:property value="walletamount"/>
				
				<br>
				<br>
				<br>
				<button id="wallet_pay" style="height: 25px; width: 400px">PAY</button>
			 </s:if> 
			<s:else>
				You Dont have Enough Amount In Your Wallet
			</s:else>
				</div>
			</div>
			<br> <br> <br> <br> <br> <br> <br>
			<br> <br>

		</div>
	</div>
</body>
</html>