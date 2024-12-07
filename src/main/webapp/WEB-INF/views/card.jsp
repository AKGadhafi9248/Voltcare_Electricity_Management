<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
 <meta charset="UTF-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=edge" />
 <meta name="viewport" content="width=device-width, initial-scale=1.0" />
 <title>Credit Card Page</title>
 <link rel="stylesheet" href="css/payment.css">
</head>
<body>
<div class="navbar">

  <a href="#" class="navbar-title">VoltCare</a>

  <div class="heading">

    <h1>Welcome, <span id="msg">${sessionScope.userName}</span></h1>

    <button class="btn"><a href="/logout">Logout</a></button>

  </div>

</div>

<nav>
 <ul>
<li><a href="/home">Home</a></li>
    <li><a href="/payBill">Pay Bill</a></li>
    <li><a href="/complaintregister">Register Complaint</a></li>
    <li><a href="/viewAllComplaints">Complaint Status</a></li>
    <li><a href="/consumer/feedbacks">Submit Feedback</a></li>
   </ul>

</nav>


<div class="container" id="container">
 <h1 style="margin-left:20px"><span id="type"></span> Card</h1>

 
<form action="makePayment" method="post">

  <input type="hidden" name="totalAmount" value="${totalAmount}" />

  <c:forEach var="billId" items="${billIds}">

    <input type="hidden" name="billIds" value="${billId}" />

  </c:forEach>



  <label for="cardName">Card Name:</label>

  <input type="text"  name="cardName" required />
  
      <c:if test="${not empty errorName}">

      <div class="error-message">${errorName}</div>
<br>
    </c:if>

   

  <label for="cardNumber">Card Number:</label>

  <input type="text" minlength="16" maxlength="16" name="cardNumber" required />
  
      <c:if test="${not empty errorNo}">

      <div class="error-message">${errorNo}</div>
<br>
    </c:if>

   

  <label for="cvv">CVV:</label>

  <input type="password" name="cvv" minlength="3" maxlength="3" class="cvv" placeholder="CVV" required />
   <c:if test="${not empty errorCvv}">

      <div class="error-message">${errorCvv}</div>
<br>
    </c:if>

   

  <label for="expiryDate">Expiry Date:</label>

  <input type="text" name="expiryDate" 
       id="inputExpDate" 
       placeholder="MM / YY" 
       maxlength='5' required />
        <c:if test="${not empty errorDate}">

      <div class="error-message">${errorDate}</div>
<br>
    </c:if>

   

 <center> <button type="submit" class="btn">Make Payment</button></center>

</form>

</div>
<script>

  const selectedPaymentMode = localStorage.getItem('paymentMode');

  if (selectedPaymentMode) {

    document.getElementById('type').textContent = selectedPaymentMode;

  }

</script>
<br>

<footer><p>Stay Connected, Stay Powered!!</p>
<br>
<p>&copy; 2024 All rights reserved.</p>
</footer>
</body>
</html>
