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

<div class="reg"><form action="/register"><button >Register</button></form>
<form action="/login"><button >Login</button></form>
</div>

</div>
 
  <nav>
   <ul>
    <li><a href="/">Home</a></li>
    <li><a href="/login">Pay Bill</a></li>
   <li><a href="/login">Register Complaint</a></li>
    <li><a href="/login">Complaint Status</a></li>
    
   </ul>
  </nav>	
 <div class="message" >
   
   <form action="logoutControl" method="post">
  
</form>
  </div> 
 
  <div class="home">
  <h2>Welcome To VoltCare Electricity Management Service.</h2>
  </div>
<footer><p>Stay Connected, Stay Powered!!</p>
<br>
<p>&copy; 2024 All rights reserved.</p>
</footer>
 </body>
</html>