<%--
  Created by IntelliJ IDEA.
  User: 17337
  Date: 2024/12/26
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Register</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 20px;
      color: #333;
    }
    .register-container {
      max-width: 400px;
      margin: 50px auto;
      background-color: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h2 {
      text-align: center;
      color: #333;
    }
    form {
      margin-top: 20px;
    }
    .form-group {
      margin-bottom: 15px;
    }
    .form-group label {
      display: block;
      margin-bottom: 5px;
    }
    .form-group input {
      width: 100%;
      padding: 10px;
      border: 1px solid #ddd;
      border-radius: 4px;
      box-sizing: border-box;
    }
    input[type="submit"] {
      width: 100%;
      padding: 10px;
      border: none;
      background-color: #5cb85c;
      color: white;
      border-radius: 4px;
      cursor: pointer;
    }
    input[type="submit"]:hover {
      background-color: #4cae4c;
    }
    p {
      text-align: center;
      color: red;
    }
    a {
      display: block;
      text-align: center;
      margin-top: 20px;
      color: #337ab7;
      text-decoration: none;
    }
    a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="register-container">
  <h2>注册页面</h2>
  <form action="RegisterServlet" method="post">
    <div class="form-group">
      <label for="username">用户名:</label>
      <input type="text" id="username" name="username" required>
    </div>
    <div class="form-group">
      <label for="password">密码:</label>
      <input type="password" id="password" name="password" required>
    </div>
    <div class="form-group">
      <label for="confirmPassword">再次确认密码:</label>
      <input type="password" id="confirmPassword" name="confirmPassword" required>
    </div>
    <div class="form-group">
      <label for="birthday">出生日期:</label>
      <input type="date" id="birthday" name="birthday">
    </div>
    <div class="form-group">
      <label for="email">邮箱:</label>
      <input type="email" id="email" name="email" required>
    </div>
    <div class="form-group">
      <label for="phone">电话号码:</label>
      <input type="tel" id="phone" name="phone">
    </div>
    <input type="submit" value="Register">
  </form>
  <a href="login.jsp">Already have an account? Login here</a>
  <p>${message}</p>
</div>
</body>
</html>




