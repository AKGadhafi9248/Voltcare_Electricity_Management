<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">

<head>

  <meta charset="UTF-8">

  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Download Receipt</title>
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
  <br>
  <div class="container">
  <h1 style="color:green">Payment Successful!</h1>
  <br>

  <p>Your payment has been processed successfully.</p>
<br>
  <p>Total Amount: $ ${sessionScope.totalfinal}</p>
<br>
  <p>Date: ${date}</p>
<br>


  <!-- Link to download the receipt -->

<a href="${receiptFilePath}">Download Receipt</a>

<br>

  <form action="/showhistory" method="get">

    <button class="btn2" type="submit">View History</button>

  </form>
</div>
</body>

</html>