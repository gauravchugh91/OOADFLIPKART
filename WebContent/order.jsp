<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>flipkart</title>
<link rel="stylesheet" type="text/css"
	href="assets/css/smoothness/jquery-ui-1.10.4.custom.css">
<link rel="stylesheet" type="text/css" href="assets/css/creditcard.css">
<script src="assets/js/jquery.js"></script>
<script src="assets/js/jquery-ui-1.10.4.custom.min.js"></script>

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
	width: 100%;
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

<script>
  $(function() {
    $( "#accordion" ).accordion();
  });
  </script>
<script type="text/javascript">
$(document).ready(
		function() {
			$("#load").click(function(event) {
				$('#add1').load('add.jsp');
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
			};
			
			accordion._std_clickHandler = accordion._clickHandler;

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
			}); --%>

					
		});


</script>


<script>
	$(function() {
		$("#selectable").selectable();
	});
</script>

<!-- Accordian   Disable script-->
 
<!--Script for Email  in first  Accordian-->
<script type="text/javascript">
	$(document).ready(function(){

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

	});
</script>

</head>
<body>



	<!-----------------------Accordian Division   --------------------------->
	<div id="accordion">
		<h2 id="section1" class="accClass">
			<a href="#">1. EMAIL ID</a>
		</h2>
		<div>
			EMAIL ID
			<s:if test="isLogin=='true'">
			<s:property value="emailid"/>
			</s:if>
		<s:else>
			 <form action="login_action" method="post">
		
			<label for="Emailid">Email Address</label>
			 <input type="text"
				id="emailid" name="email" required  value="" onfocus="inputFocus(this)"
				onblur="inputBlur(this)" /><br> 
				<label for="Password"
				id="label">Password</label>
				<input type="password" id="password"
				name="password" required="required" value="" onfocus="inputFocus(this)"
				onblur="inputBlur(this)" /> <br /> 
				<input type="checkbox"
				name="my_checkbox" value='false'> <label for="Password">I
				have flipkart account</label> <br>  

			<button id="1"> Continue</button>
			 <!-- <input type="submit" id="1" value="Continue" /> -->
			</form>
			</s:else> 
		</div>


		<h2 id="section2" class="accClass">
			<a href="#">2. DELIVERY ADDRESS </a>
		</h2>
		<div>
			DELIVERY ADDRESS
			<div>
			<button id="load" style="height: 25px; width: 400px">+ Add New Address</button>
				<!-- <input type="button" id="load" value="+ Add New Address"> -->
			</div>
			<div id="add1">
				<h2>Address</h2>
			</div>
		</div>


		<h2 id="section3" class="accClass">
			<a href="#">3. ORDER SUMMARY</a>
		</h2>
		<div>
			ORDER SUMMARY 

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
				</ul>
				<!-- <div id="tabs-1"></div> -->
				<div id="tabs-1">
					<form action="makepayment">
						<s:select headerKey="-1" headerValue="Select BANK" list="bank"
							name="semester" cssStyle="width:150px" />
						<br> <br> <input id="paycard" type="submit"
							value="MAKE PAYMENT" />
					</form>
				</div>

				<div id="tabs-2">
					<section class="credit-card visa gr-visa">
						<div class="logo">Credit Card</div>
						<form action="creditcard" method="post">
							<h2>Payment Details</h2>

							<ul class="inputs">
								<li><label>Card Number</label> <input type="text"
									name="cardNumber" value="9842 9472 9457 9472"
									class="full gr-input" required /></li>

								<li class="expire last"><label>EXPIRY DATE (VALID
										THRU)</label> <input type="text" name="expireMonth" value="MM"
									size="10" class="month gr-input" required="required" /> <input
									type="text" name="expireYear" value="YYYY" size="10"
									class="year gr-input" required="required" />
									<div class="clearfix"></div></li>
								<li class="cvc-code last"><label>CVC Code</label> <input
									type="text" name="cvcCode" value="174" size="10"
									class="gr-input" required="required" /></li>

								<li><label>NAME ON CARD</label> <input type="text"
									name="cardName" value="NAME" class="full gr-input"
									required="required" /></li>
								<div class="clearfix"></div>
							</ul>
								<button id="paycard" style="height: 25px; width: 400px"> PAY</button>
							<!-- <input id="paycard" type="submit" value="PAY" /> -->


						</form>
						<div class="watermark">CREDI</div>
					</section>

				</div>
				<div id="tabs-3">
					<section class="credit-card visa gr-visa">
						<div class="logo">Debit Card</div>
						<form action="debitcard" method="post">
							<h2>Payment Details</h2>

							<ul class="inputs">
								<li><label>Card Number</label> <input type="text"
									name="cardNumber" value="9842 9472 9457 9472"
									class="full gr-input" required="required" /></li>

								<li class="expire last"><label>EXPIRY DATE (VALID
										THRU)</label> <input type="text" name="expireMonth" value="MM"
									size="10" class="month gr-input" required="required" /> <input
									type="text" name="expireYear" value="YY" size="10"
									class="year gr-input" required />
									<div class="clearfix"></div></li>
								<li class="cvc-code last"><label>CVC Code</label> <input
									type="text" name="cvcCode" value="174" size="10"
									class="gr-input" required="required" /></li>

								<li><label>NAME ON CARD</label> <input type="text"
									name="cardName" value="NAME" class="full gr-input"
									required="required" /></li>
								<div class="clearfix"></div>
							</ul>
							<button id="paycard" style="height: 25px; width: 400px"> PAY</button>
							<!-- <input id="paycard" type="submit" value="PAY" /> -->


						</form>
						<div class="watermark">DEB</div>
					</section>

				</div>

			</div>
			<br> <br> <br> <br> <br> <br> <br>
			<br> <br>

		</div>
	</div>
</body>
</html>
