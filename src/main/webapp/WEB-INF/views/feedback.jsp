
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>

<html lang="en">

<head>

  <meta charset="UTF-8">

  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Consumer Feedback</title>
  <link rel="stylesheet" href="css/feedback.css">

</head>
<style>
.message{
	display:flex;
	flex-direction:row;
	justify-content:flex-start;
	align-items:center;
	margin-left:700px;
	margin-bottom:35px;
}
.message h1 {
margin:0;
 cursor:pointer;
 font-size: 25px;
 color: #333;
 font-weight: bold;
 margin-right:20px;
 display:inline-block;
}
.message button{
	margin-left:20px;
}.navbar {

  width: 100%;

  height: auto; /* Allow height to adjust based on content */

  background-color: #463a58 ;

  display: flex;

  align-items: center;

  justify-content: space-between; /* Space out title and heading */

  padding: 10px 20px; /* Add padding for spacing */

  box-shadow: 0 17px 19px rgba(0, 0, 0, 0.1);

  border-radius: 0px;

}
footer {
 position: fixed;
 bottom: 0;
 width: 100%;
 height:12%;
 background-color: #E6D9A2;
 color: #000; 
 text-align: center;
 padding: 10px 0;
 font-size: 14px;
 margin-top:-350px;

}



.navbar-title {

  font-size: 24px;

  font-weight: bold;

  color: #ffffff;

  text-decoration: none;

}



.navbar-title:hover {

  font-size: 26px;

  color: #85A98F
}



.heading {

  display: flex;

  align-items: center;

  gap: 10px; /* Space between elements */

}



.heading h1 {

  font-size: 20px;

  color: #ffffff;

  margin: 0;

  overflow: hidden; /* Ensure text stays within bounds */

  text-overflow: ellipsis; /* Add ellipsis for long names */

  white-space: nowrap; /* Prevent wrapping */

  max-width: 250px; /* Adjust as needed for your layout */

}



#msg {

  font-weight: bold;

  color: #FEECB3; /* Highlight the name in a different color */

}
table {

 width: 100%;

 border-collapse: collapse;

 margin: 20px 0;

 background-color: #ffffff;

 border-radius: 10px;

 box-shadow: 0 17px 30px rgba(64, 62, 62, 0.7);

}



thead {

 background-color: #8967B3;

 color: white;

}



th, td {

 padding: 15px;

 text-align: left;

 border: 1px solid #cccccc;

}



th {

 font-size: 18px;

 font-weight: bold;

}



tbody tr:hover {

 background-color: #f2f2f2;

}



textarea, select, input[type="text"] {

 width: 90%;

 padding: 8px;

 margin-top: 5px;

 border: 1px solid #cccccc;

 border-radius: 4px;

 background-color: #F5F5F5;

 color: #333;

}



button[type="submit"] {

 background-color: #FABC3F;

 color: #000000;

 padding: 10px 20px;

 border: none;

 border-radius: 5px;

 cursor: pointer;

 font-weight: bold;

 transition: background-color 0.3s ease, color 0.3s ease;

}

.btn {
  background-color: #FABC3F;
  color: #000000;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 20px;
  width: 100px; 
  height:40px;
  
}
.btn:hover {
  background-color: #8967bb  ;
  color:white;
  font-size:15px;
}

.btn a{
color:#000;
text-decoration:none;
}

button[type="submit"]:hover {

 background-color: #8967bb;

 color: black;

}
</style>
<body>
<div class="navbar">

  <a href="#" class="navbar-title">VoltCare</a>

  <div class="heading">

    <h1>Welcome, <span id="msg">${sessionScope.userName}</span></h1>

    <button class="btn"><a href="/logout">Logout</a></button>

  </div>

</div>

  <center><h1 >Feedback Form</h1></center>



  <c:if test="${empty activeFeedbacks}">

    <p>No active feedback available at this time.</p>

  </c:if>



  <c:if test="${not empty activeFeedbacks}">

    <table border="1" cellpadding="10" cellspacing="0">

      <thead>

        <tr>

          <th>Feedback ID</th>

          <th>Complaint ID</th>

          <th>Description</th>

          <th>Rating (1-5)</th>

          <th>Submit Feedback</th>

        </tr>

      </thead>

      <tbody>

        <c:forEach var="feedback" items="${activeFeedbacks}">

          <tr>

            <td>${feedback.feedbackId}</td>

            <td>${feedback.complaintId}</td>

            <td>

              <form id="feedbackForm_${feedback.feedbackId}" method="post" action="/consumer/submitFeedback">

                <input type="hidden" name="feedbackId" value="${feedback.feedbackId}">

                <textarea name="commandDescription" placeholder="Enter your feedback" required></textarea>

            </td>

            <td>

                <select name="rating" required>

                  <option value="1">1</option>

                  <option value="2">2</option>

                  <option value="3">3</option>

                  <option value="4">4</option>

                  <option value="5">5</option>

                </select>

            </td>

            <td>

                <button type="submit">Submit</button>

              </form>

            </td>

          </tr>

        </c:forEach>

      </tbody>

    </table>

  </c:if>



  <c:if test="${not empty message}">

    <p>${message}</p>

  </c:if>
<footer><p>Stay Connected, Stay Powered!!</p>
<p>&copy; 2024 All rights reserved.</p>
</footer>
</body>


</html>

