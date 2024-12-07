<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
 <meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title>Payment Page</title>
 <link rel="stylesheet" href="css/style2.css">
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
 </ul>
</nav>

<br><br>

<div class="container">

 <h1>Payment Details</h1>
 <div class="ins">
  <div id="billSummary"></div><br>

 <p>Total Bill Amount: $<span >${totalAmount}</span></p><br>
<h3 class="h3">Total Payable Amount: $<span id="totalPayable">${totalAmount +30 }</span></h3><br>
  <p>PG Charge: $<span id="pgCharge">30</span></p><br>
  <label for="paymentMode">Mode of Payment:</label>
<select id="paymentMode" onchange="localStorage.setItem('paymentMode', this.value)">

 

  <option value="Debit">Debit</option>

  <option value="Credit">Credit</option>

</select><br><br>
 </div>

 <form  action="makePayment" method="get"><button class="btn2" id="payNowBtn">Pay Now</button>
 </form>
 <form action ="payBill">
 <button class="btn2" id="backToHomeBtn" href="/payBill">Back to Bill</button>
 </form>
</div>
<footer>
 <p>Stay Connected, Stay Powered!!</p>
 <br>
 <p>&copy; 2024 All rights reserved.</p>
</footer>
<script>
 window.onload = function() {
  const total = localStorage.getItem("totalAmount");
  document.getElementById('totalBill').innerText = total ? total : 0;
  const pgCharge = 30;
  const totalPayable = total ? parseInt(total) + pgCharge : pgCharge;
  document.getElementById('totalPayable').innerText = totalPayable;
  localStorage.setItem("totalPayable",totalPayable);
  const savedPaymentMode = localStorage.getItem('paymentMode');
  if (savedPaymentMode) {
   document.getElementById('paymentMode').value = savedPaymentMode;
  }
 };
 document.getElementById('paymentMode').addEventListener('change', function() {
  const selectedPaymentMode = this.value;
  localStorage.setItem('paymentMode', selectedPaymentMode);
 });
 document.getElementById('payNowBtn').addEventListener('click', function() {
	 window.location.href="card.jsp";
 });
 document.getElementById('backToHomeBtn').addEventListener('click', function() {
	  window.location.href="payBill.jsp";
	 });
</script>
</body>
</html>