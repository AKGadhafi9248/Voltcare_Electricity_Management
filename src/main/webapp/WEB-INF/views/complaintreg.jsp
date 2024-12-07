	<!DOCTYPE html>
	
	<html lang="en">
	
	
	
	<head>
	
	  <meta charset="UTF-8">
	
	  <meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	  <title>Register Complaint</title>
	
	  <link rel="stylesheet" href="css/style.css" />
	
	</head>
	
	
	<body>
	
	  <div class="navbar">
	
	    <a href="#" class="navbar-title">VoltCare</a>
	
	  </div>
	   <%-- <div class="heading">

    <h1>Welcome, <span id="msg">${sessionScope.userName}</span></h1>

    <button class="btn"><a href="/logout">Logout</a></button>

  </div>
	   <nav>
   <ul>
    <li><a href="/home">Home</a></li>
    <li><a href="/payBill">Pay Bill</a></li>
   <li><a href="/complaintregister">Register Complaint</a></li>
    <li><a href="/viewAllComplaints">Complaint Status</a></li>
   </ul>
  </nav>	
	 --%>
	
	<br>
	  <div class="container">
	
	    <form id="complaintForm" action="complaintregister" method="post" >
	
	      <h2>Register Complaint</h2>
	
	
	
	      <div class="section">
	
	        <h3>Complaint Details</h3>
	
	
	
	        <label for="complaintType">Complaint Type:</label>
	
	        <select id="complaintType" name="complaintType" required>
	
	          <option value="">Please Select</option>
	
	          <option value="Electricity">Electricity</option>
	
	          <option value="Billing">Billing</option>
	
	          <option value="Service">Service</option>
	
	          <option value="Others">Others</option>
	
	        </select>
	
	
	
	        <label for="category">Category:</label>
	
	        <select id="category" name="category" required>
	
	          <option value="">Select Category</option>
	
	          <option value="customer">Voltage</option>
	
	          <option value="service-rep">Street Light</option>
	
	        </select>
	
	
	
	        <label for="contactPerson">Contact Person:</label>
	
	        <input type="text" id="contactPerson" name="contactPerson" placeholder="Enter Contact Person's Name" required>
	<c:if test="${not empty errorName2}">

      <div class="error-message">${errorName2}</div>

    </c:if>
	
	
	        <label for="landmark">Landmark:</label>
	
	        <input type="text" id="landmark" name="landmark" placeholder="Enter Landmark" required>
	        
	        
	
       
	        <label for="consumerNumber" >Consumer Number: <span style="color:#8967B3;font-weight:bold;">${con_num}</span> </label>
	
	        <input type="hidden" id="consumerNumber" value="${con_num}" name="consumerNumber" placeholder="Consumer Number" required>
	
	
	
	        <label for="problemDescription">Problem Description:</label>
	
	        <textarea id="problemDescription" name="problemDescription" placeholder="Describe the problem" rows="4" required></textarea>
	
	
	
	        <label for="mobile">Mobile Number:</label>
	
	        <input type="text" id="mobile" name="mobile" minlength="10" maxlength="10" placeholder="Mobile Number" required>
	    <c:if test="${not empty errorMobile2}">

      <div class="error-message">${errorMobile2}</div>

    </c:if>
	
	
	        <label for="address">Address:</label>
	
	        <textarea  id="address" name="address" placeholder="Enter your address" rows="3" required></textarea>
	
	      </div>
	
	
	
	      <div class="section">
	
	        <button type="reset" class="btn">Reset</button>
	
	        <button type="submit" class="btn" >Submit</button>
	<center><button type="button" class="btn" onclick="window.history.back();">Go Back</button></center>
	        
	
	      </div>
	
	
	
	    </form>
	
	
	
	 
	
	  </div>
	
	</body>
	
	
	
	</html>