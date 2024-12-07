<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>

<html lang="en">



<style>

  #consumerList {

    list-style-type: none;

    padding: 0;

    margin: 0;

    max-height: 100px;

    overflow-y: auto;

    border: 1px solid #ccc;

    display: none; /* Hidden initially */

    position: absolute;

    background-color: white;

    width: 30%;

    z-index: 999;

  }



  #consumerList li {

    padding: 8px;

    cursor: pointer;

  }



  #consumerList li:hover {

    background-color: #f0f0f0;

  }

label {

 font-weight: bold;

 margin-bottom: 5px; /* Add space between label and input */

}


input {

 width: 170px;

 padding: 10px;

 margin-bottom: 20px; /* Space between fields */

 border-radius: 5px;

 border: 1px solid #ccc;

 font-size: 16px;

}


  #consumerSearch {

    width: 100%;

    padding: 8px;

    margin-bottom: 8px;

  }

</style>



<head>

  <meta charset="UTF-8" />

  <meta http-equiv="X-UA-Compatible" content="IE=edge" />

  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <title>Add Consumer Bill</title>

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

  <div class="home">

    <h2>Add Consumer Bill</h2>



    <!-- Add Consumer Bill Form -->

    <form action="/addConsumerBill" method="post">

      <label for="consumerSearch">Select Consumer:</label>

      <input type="text"  style="width:300px;" id="consumerSearch" placeholder="Search Consumer Number" onkeyup="filterConsumers()" />



      <ul id="consumerList">

        <c:forEach var="consumer" items="${consumers}">

          <li onclick="selectConsumer('${consumer.consumerNumber}')">

            ID: ${consumer.consumerNumber}

          </li>

        </c:forEach>

      </ul>



 <input type="hidden" id="consumerNumber" name="consumerNumber"  /> 



      <label for="billDescription">Bill Description:</label>

      <input type="text" id="billDescription" name="billDescription" required />



      <label for="billAmount">Bill Amount:</label>

      <input type="number" id="billAmount" name="billAmount" required />



      <label for="billDate" class="form-label">Bill Date:</label>

      <input type="date" id="billDate" name="billDate" class="form-control" required />



      <button type="submit" class="btn">Submit Bill</button>

    </form>

  </div>



  <span style="color:green">${message}</span>

  <footer>

    <p>Stay Connected, Stay Powered!!</p>

    <br />

    <p>&copy; 2024 All rights reserved.</p>

  </footer>



  <script>

    function filterConsumers() {

      const searchInput = document.getElementById('consumerSearch').value.toLowerCase();

      const consumerList = document.getElementById('consumerList');

      const consumers = consumerList.getElementsByTagName('li');

      let hasMatches = false;



      // Loop through the list and filter based on search input

      for (let i = 0; i < consumers.length; i++) {

        const consumerNumber = consumers[i].innerText.toLowerCase();

        if (consumerNumber.includes(searchInput)) {

          consumers[i].style.display = 'block'; // Show matching items

          hasMatches = true;

        } else {

          consumers[i].style.display = 'none'; // Hide non-matching items

        }

      }



      // If there are matching results, show the suggestion box, otherwise hide it

      if (hasMatches && searchInput.length > 0) {

        consumerList.style.display = 'block';

      } else {

        consumerList.style.display = 'none';

      }

    }



    function selectConsumer(consumerNumber) {

      // Set the input field and the hidden field with the selected consumer number

      document.getElementById('consumerSearch').value = consumerNumber;

      document.getElementById('consumerNumber').value = consumerNumber;



      // Hide the list after selecting a consumer

      document.getElementById('consumerList').style.display = 'none';

    }

  </script>

</body>



</html>