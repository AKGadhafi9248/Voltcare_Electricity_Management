<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>

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
 <div class="news-ticker">
   <p>Welcome to Electricity Management.</p>
  </div> 
  <div class="home">
  <h2>Your One-Stop Solution for Electricity Management.</h2>
  </div>
<footer><p>Stay Connected, Stay Powered!!</p>
<br>
<p>&copy; 2024 All rights reserved.</p>
</footer>
 </body>
</html>