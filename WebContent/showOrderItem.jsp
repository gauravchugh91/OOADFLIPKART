<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Flipkart page</title>
<script src="assets/js/jquery.js"></script>
<script src="assets/js/jquery-ui-1.10.4.custom.min.js"></script>

<script type="text/javascript">
		function editstatus (ord) 
		{
			
            var status = $("#"+ord).val();
            //alert ("status="+status+" and got set as="+ord);
			$.ajax({
				type : 'GET',
				url : 'updateStatus?orderitemid='+ord+'&got_orderstatus='+status,
				success : function(data) {
					// Your Code here

					$("#edit").html(data.message);
					$("#edit").unbind();
					
				}
				
			});

		}
	</script>
	
</head>
<body>
<h3> Showing details of Order : <s:property value="orderid"/></h3>
<table border="2">
<tr>
<th>Order Item id</th>
<th>Product id </th>
<th> Quantity </th>
<th> Price </th>
<th> Sub total </th>
<th> Status </th>
<th> * </th>
</tr>
<s:iterator value="orderItems" >
<tr> 
<td> <s:textfield name="orderitemid" id="orderitemid" value="%{orderitemid}" readonly="true" theme="simple"/></td>
<td> <s:property value="productid"/></td>
<td> <s:property value="quantity"/></td>
<td> <s:property value="price"/></td>
<td> <s:property value="subtotal"/></td> 
<s:if test="orderstatus=='Order Closed'"> <td>Order Closed</td><td>*</td></s:if>
<s:else><td><s:select list="validStatuses" theme="simple" id ="%{orderitemid}" name="got_orderstatus"/></td>
<td> <button id="edit" onclick="editstatus('<s:property value="orderitemid"/>');"> Edit Status</button></td></s:else>
</tr>
</s:iterator>
</table>
</body>
</html>