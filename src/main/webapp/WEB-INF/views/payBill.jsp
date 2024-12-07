<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>

<html>

<head>

  <meta charset="ISO-8859-1">

  <title>Pay Bill</title>

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

</nav>







<div class="bills" style="margin-top:40px;">

  <h1>Pay Your Bills</h1>

  <form id="billform" action="processPayment" method="post">

    <table>

      <thead>

        <tr>	

          <th>Select</th>

          <th>Bill Description</th>

          <th>Amount</th>

          <th>Bill Date</th>

        </tr>

      </thead>

      <tbody>

        <c:forEach var="bill" items="${billList}">

          <tr>

             <td><input type="checkbox" name="billIds" value="${bill.billId}"class="billCheckbox" data-amount="${bill.billAmount}" /></td>

            <td>${bill.billDescription}</td>

            <td>$${bill.billAmount}</td>

            <td>${bill.billDate}</td>

          </tr>

        </c:forEach>

      </tbody>

    </table>



    <h3 class="h3">Total Amount: <span id="totalAmount">0</span>$</h3>



    <button type="submit" class="btn2">Proceed to Pay</button>

  </form>
  

</div>
 <form action="showhistory" ><button class="btn	">Bill History</button></form>


<script>

  const checkboxes = document.querySelectorAll('.billCheckbox');

  const totalAmountSpan = document.getElementById('totalAmount');

  const form = document.getElementById('billform');



  let totalAmount = 0;



  checkboxes.forEach(checkbox => {

    checkbox.addEventListener('change', () => {

      const amount = parseFloat(checkbox.getAttribute('data-amount'));

      if (checkbox.checked) {

        totalAmount += amount;

      } else {

        totalAmount -= amount;

      }

      totalAmountSpan.textContent = totalAmount.toFixed(2);

    });

  });



  form.addEventListener('submit', (e) => {

    const selectedCheckboxes = document.querySelectorAll('.billCheckbox:checked');

    if (selectedCheckboxes.length === 0) {

      e.preventDefault();

      alert('Please select at least one bill to proceed with payment.');

    }

  });

</script>
<footer>

  <p>Stay Connected, Stay Powered!!</p>

  <br>

  <p>&copy; 2024 All rights reserved.</p>

</footer>



</body>

</html>