<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
 <head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Admin</title>
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
 <li><a href="/admin">Home</a></li>
    <li><a href="/active-complaints">Active Complains</a></li>
    <li><a href="/viewComplaints">Complaint Status</a></li>
     <li><a href="/addConsumerBill">Add Consumer Bill</a></li>
     <li><a href="/admin/feedbacks">View Feedbacks</a></li>
   </ul>
  </nav>
 <div class="message" >
   
   
  </div> 
 <div class="news-ticker">
   <p>Welcome to Electricity Management.</p>
  </div> 
  <div class="home">
  <h2>Welcome to Admin Page.</h2>
  </div>
<footer><p>Stay Connected, Stay Powered!!</p>
<br>
<p>&copy; 2024 All rights reserved.</p>
</footer>
 </body>
</html>