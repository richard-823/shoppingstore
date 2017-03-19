<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirm your Shooping Cart</title>
</head>
<body>
	<h1>Please review your Shopping Cart</h1>  
  	<hr>   
   	<div id="shopping">  
   	<form action="payment" method="POST">    
	  First name: <input type="text" value="William" name="firstName" />
	  Last name: <input type="text" value="Smith" name="lastName" /><br/>
	  Email: <input type="text" value="williamSmith@yahoo.com" name="email" /><br/>
	  Phone Number: <input type="text" value="(415)-563-1234" name="phone" /><br/>
	  Shipping Address<br/>
	  Street: <input type="text" style="width:300px;" value="336 Main Street #7F, San Francisco" name="street" /><br/>
	  State: <input type="text" value="CA" name="state" />
	  County: <input type="text" value="USA" name="country" /><br/>
	  Zip: <input type="text" value="94105" name="zip" /><br/><br/><br/>
	  
	  ItemName: <input type="text" value="KitchenAid Stand Mixer" name="itemName" /><br/>
	  item#: <input type="text" value="100435673" name="itemNum" /><br/>
	  unitPrice: <input type="text" value="169.99" name="unitPrice" /><br/>
	  quantity: <input type="text" value="1" name="quantity" /><br/>
	  totalPrice: <input type="text" value="169.99" name="totalPrice" /><br/>
	  
    <br/>
   	<input type="submit" value="checkout"/> 
     </form>  
    </div>  
</body>
</html>