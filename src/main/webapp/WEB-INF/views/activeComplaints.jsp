<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>

  <meta charset="UTF-8">

  <title>Active Complaints</title>

  <style>

    table { width: 100%; border-collapse: collapse; }

    th, td { padding: 8px; border: 1px solid #ddd; text-align: left; }

    th { background-color: #f4f4f4; }

    button { padding: 5px 10px; margin: 5px; }

  </style>

</head>
  <link rel="stylesheet" href="css/style2.css" />

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

  <h1 >Active Complaints</h1>

  <c:if test="${not empty message}">

    <p style="color: green;">${message}</p>

  </c:if>

  <table>

    <thead>

      <tr>

        <th>Complaint ID</th>

        <th>Consumer Number</th>

        <th>Complaint Type</th>

        <th>Category</th>

        <th>Contact Person</th>

        <th>Description</th>

        <th>Mobile</th>

        <th>Complaint Date</th>

        <th>Status</th>

        <th>Actions</th>

      </tr>

    </thead>

    <tbody>

      <c:forEach var="complaint" items="${complaints}">

        <tr>

          <td>${complaint.complaintId}</td>

          <td>${complaint.consumerNumber}</td>

          <td>${complaint.complaintType}</td>

          <td>${complaint.category}</td>

          <td>${complaint.contactPerson}</td>

          <td>${complaint.problemDescription}</td>

          <td>${complaint.mobile}</td>

          <td>${complaint.complaintDate}</td>

          <td>${complaint.status}</td>

          <td>

            <form id ="pending"  action="set-pending/${complaint.complaintId}" method="post">

              <button type="submit">Set Pending</button>

            </form>

            <form id ="inactive" action="set-inactive/${complaint.complaintId}" method="post">

              <button type="submit">Set Resolved</button>

            </form>

          </td>

        </tr>

      </c:forEach>

    </tbody>

  </table>

</body>

</html>