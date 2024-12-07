
<!DOCTYPE html>

<html>

<head>

  <meta charset="UTF-8">

  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Register Consumer</title>

  <link rel="stylesheet" href="css/style.css" />

</head>

<body>

  <div class="navbar">

    <a href="#" class="navbar-title">VoltCare</a>

  </div>

  <div class="container">

   <form id="registrationForm" action="registerControl" method="post">

  <h2>Register</h2>



  <div class="section">

    <h3>Consumer Details</h3>



    <label for="consumerNumber">Consumer Number:</label>

    <input type="text" id="consumerNumber" name="consumerNumber" minlength="13" maxlength="13" value="${consumerNumber}" placeholder="Consumer Number" required>

    <c:if test="${not empty errorConsumerNumber}">

      <div class="error-message">${errorConsumerNumber}</div>

    </c:if>



    <label for="billNumber">Bill Number:</label>

    <input type="text" id="billNumber" name="billNumber"minlength="5" maxlength="5" value="${billNumber}" placeholder="Last 5 digits" required>

    <c:if test="${not empty errorBillNumber}">

      <div class="error-message">${errorBillNumber}</div>

    </c:if>

  </div>



  <div class="section">

    <h3>User Details</h3>



    <label for="title">Title:</label>

    <select id="title" name="title">

      <option value="">Please Select</option>

      <option value="Mr." ${title == 'Mr.' ? 'selected' : ''}>Mr.</option>

      <option value="Ms." ${title == 'Ms.' ? 'selected' : ''}>Ms.</option>

    </select>



    <label for="name">Name:</label>

    <input type="text" id="name" name="name" value="${name}" placeholder="Enter your Name" required>

    <c:if test="${not empty errorName}">

      <div class="error-message">${errorName}</div>

    </c:if>



    <label for="email">Email Id:</label>

    <input type="email" id="email" name="email" value="${email}" placeholder="Email address" required>

    <c:if test="${not empty errorEmail}">

      <div class="error-message">${errorEmail}</div>

    </c:if>



    <label for="mobile">Mobile Number:</label>

    <input type="text" id="mobile" name="mobile" minlength="10" maxlength="10" value="${mobile}" placeholder="Mobile Number" required>

    <c:if test="${not empty errorMobile}">

      <div class="error-message">${errorMobile}</div>

    </c:if>


<input  type="hidden" id="category" name="category" value="customer" required>
<!--     <label for="category">Category:</label> -->

   <%--  <select id="category" name="category">

      <option value="">Select Category</option>

      <option value="customer" ${category == 'customer' ? 'selected' : ''}>Customer</option>

      <option value="service-rep" ${category == 'service-rep' ? 'selected' : ''}>Service Representative</option>

    </select> --%>

  </div>



  <div class="section">

    <h3>Login Details</h3>



    <label for="userId" >User Id:</label>

    <input type="text"  name="userId" id="userId" value="${userId}" placeholder="UserId" required>

    <c:if test="${not empty errorUserId}">

      <div class="error-message">${errorUserId}</div>

    </c:if>



    <label for="password">Password:</label>

    <input type="password" name="password" id="password" required>



    <label for="confirmPassword">Confirm Password:</label>

    <input type="password" name="conpassword" id="confirmPassword" required>

    <c:if test="${not empty passwordMismatch}">

      <div class="error-message">${passwordMismatch}</div>

    </c:if>

  </div>



  <button type="reset" class="btn">Reset</button>

  <button type="submit" class="btn">Register</button>

</form>

    <p>If you already have an account? <a href="/login">Login</a></p>

  </div>

</body>

</html>

