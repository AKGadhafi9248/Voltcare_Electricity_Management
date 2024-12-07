<!DOCTYPE html>

<html>

<head>

  <meta charset="UTF-8">

  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Registration Success</title>

  <link rel="stylesheet" href="/css/style.css" />

</head>

<body>
<div class="navbar">

    <a href="#" class="navbar-title">VoltCare</a>

  </div>

  <div class="success">

    <h1 style="color:green">Consumer Registration Successful!</h1>

    <p>Customer Id : <span><%= request.getAttribute("customerId") %></span></p>

    <p>Customer Name : <span><%= request.getAttribute("consumerName") %></span></p>

    <p>Customer Email : <span><%= request.getAttribute("consumerEmail") %></span></p>

    <a href="/login">Back to Login</a>

  </div>

</body>

</html>

