<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
 <script type='text/javascript'>
    /* attach a submit handler to the form */
    $("#formid").submit(function(event) {

      /* stop form from submitting normally */
      event.preventDefault();

      /* get some values from elements on the page: */
      var $form = $( this ),
          url = $form.attr( 'action' );

      /* Send the data using post */
      var posting = $.post( url, { name: $('#name').val(), pincode: $('#pincode').val(),address: $('#address').val(), city: $('#city').val(), state: $('#state').val(), country: $('#country').val(), phone: $('#phone').val() } );

      /* Put the results in a div */
      posting.done(function( data ) {
        //alert('success');
      });
    });
</script> 


<body>
<br>
   <s:div>Enter a new shipping address:</s:div>
   <br>
   <s:form id="formid" action="addaddress" method="post" enctype="multipart/form-data" theme="simple" >
   
   <label for="name"> Name:    </label>
   <s:textfield id="name" key="Name" name="name" required="required"/>
  <br>
   
   <label for="pin"> Pincode: </label>
   <s:textfield id="pincode" key="Pincode" name="pincode" required="required"/>
	<br>
	
 <label for="address"> Address: </label>
<br>  <textarea id="address" name="address" rows="5" cols="40" required="required" maxlength="200"></textarea> </br>
 
  <label for="city"> City:</label>
 <s:textfield  id="city" name="city" required="required"/>
 <br>
 <label for="state"> State:</label>
 <s:textfield id="state" name="state" required="required"/>
 <br>
 <label for="country"> Country:</label>
 <s:textfield  id="country" name="country" required="required"/>
 <br>
 
 
 <label for="phone"> PhoneNo: +91</label>
 <s:textfield  id="phone" name="phone" required="required"/>
 
 
 <br>
 <button id="save"> Save & Continue</button>
  <!--  <input  id="2" type="submit" value="Save & Continue" /> -->
   <s:hidden  value="newaddress" name="check_address" />
   </s:form>
</body>
</html>