<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>

  <title>All Complaints</title>

  <!-- <style>

    table {

      width: 100%;

      border-collapse: collapse;

    }

    th, td {

      padding: 8px 12px;

      border: 1px solid #ddd;

      text-align: center;

    }

    th {

      background-color: #f2f2f2;

    }

  </style> -->

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
    <li><a href="/admin">Home</a></li>
    <li><a href="/active-complaints">Active Complains</a></li>
    <li><a href="/viewComplaints">Complaint Status</a></li>
     <li><a href="/addConsumerBill">Add Consumer Bill</a></li>
       <li><a href="/admin/feedbacks">View Feedbacks</a></li>
   </ul>
  </nav>
  <h2>All Complaints</h2>

  <table>

    <tr>

      <th>Complaint ID</th>

      <th>Consumer Number</th>

      <th>Complaint Type</th>

      <th>Category</th>

      <th>Contact Person</th>

      <th>Landmark</th>

      <th>Problem Description</th>

      <th>Mobile</th>

      <th>Address</th>

      <th>Complaint Date</th>

      <th>Status</th>

<!--       <th>Actions</th> -->

    </tr>

    <c:forEach var="complaint" items="${complaints}">

      <tr>

        <td>${complaint.complaintId}</td>

        <td>${complaint.consumerNumber}</td>

        <td>${complaint.complaintType}</td>

        <td>${complaint.category}</td>

        <td>${complaint.contactPerson}</td>

        <td>${complaint.landmark}</td>

        <td>${complaint.problemDescription}</td>

        <td>${complaint.mobile}</td>

        <td>${complaint.address}</td>

        <td>${complaint.complaintDate}</td>

        <td>${complaint.status}</td>

      <!--   <td> -->

         <%--  <form method="post" action="updateComplaintStatus">

            <input type="hidden" name="complaintId" value="${complaint.complaintId}" />

            <button type="submit" name="status" value="Pending">Pending</button>

            <button type="submit" name="status" value="Inactive">Inactive</button>

          </form> --%>

      <!--   </td> -->

      </tr>

    </c:forEach>

  </table>

</body>
<footer>

  <p>Stay Connected, Stay Powered!!</p>

  <br>

  <p>&copy; 2024 All rights reserved.</p>

</footer>

</html>