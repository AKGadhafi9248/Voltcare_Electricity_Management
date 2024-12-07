
<!DOCTYPE html>

<html>

<head>

  <meta charset="UTF-8">

  <link rel="stylesheet" href="css/style.css" />

  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Login Consumer</title>

  <style>

    .error-message {

      color: red;

      font-size: 14px;

    }

  </style>

</head>

<body>

  <div class="navbar">

    <a href="#" class="navbar-title">VoltCare</a>

  </div>

  <div class="loginContainer">

    <form id="loginForm" action="login" method="post">

      <h2>Login</h2>

      <label for="userId">User Id:</label>

      <input type="text" name="userId" id="userId" required>

      <!-- Display User ID Error -->

     <c:if test="${not empty errorUser}">

  <div class="error-message">${errorUser}</div>

</c:if>

      <label for="password">Password:</label>

      <input type="password" name="password" id="password" required>

      <!-- Display Password Error -->

     <c:if test="${not empty erMessage}">

  <div class="error-message">${erMessage}</div>

</c:if>
<center>
      <button type="submit" class="btn">Login</button>
</center>
    </form>

    <p>If you already have an account? <a href="/register">Register</a></p>

  </div>

</body>

</html>



