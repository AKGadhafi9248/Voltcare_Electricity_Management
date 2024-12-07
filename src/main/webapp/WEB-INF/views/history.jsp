<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
 <head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Home</title>
  <link rel="stylesheet" href="css/style2.css" />
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
 <div class="message" >
   
   <form action="logoutControl" method="post">
  
</form>
  </div> 
<!--  <div class="news-ticker">
   <p>Welcome to Electricity Management.</p>
  </div>  -->
  
<h1>Payment History</h1>

<br>

<table border="1">

  <tr>

    <th>Consumer Number</th>

    <th>Bill Date</th>

    <th>Bill Description</th>

    <th>Bill Amount</th>

    <th>Payment Date</th>

  </tr>



  <!-- Iterate over the billHistory list passed from the controller -->

  <c:forEach var="history" items="${billHistory}">

    <tr>

      <td>${history.consumerNumber}</td>

      <td>${history.billDate}</td>

      <td>${history.billDescription}</td>

      <td>${history.billAmount}</td>

      <td>${history.paymentDate}</td>

    </tr>

  </c:forEach>



</table>



<form action="/home" method="get">

  <button type="submit" class="btn2">Back to Home</button>

</form>
<footer><p>Stay Connected, Stay Powered!!</p>
<br>
<p>&copy; 2024 All rights reserved.</p>
</footer>
 </body>
</html>