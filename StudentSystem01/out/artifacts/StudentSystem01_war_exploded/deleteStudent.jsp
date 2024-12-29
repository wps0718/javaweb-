<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ComMain.StudentDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Delete Student</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f7f7f7;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      height: 100vh;
    }

    .container {
      background-color: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      text-align: center;
    }

    button {
      padding: 10px 20px;
      background-color: #f44336;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    button:hover {
      background-color: #e53935;
    }

    .back-button {
      padding: 10px 20px;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 4px;
      text-decoration: none;
      margin-top: 20px;
      text-align: center;
    }

    .back-button:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>

<c:set var="studentId" value="${param.id}" />

<div class="container">
  <h2>Are you sure you want to delete this student?</h2>
  <form action="DeleteStudentServlet" method="post">
    <input type="hidden" name="id" value="${studentId}">
    <button type="submit">Yes, Delete</button>
  </form>
  <a href="main.jsp" class="back-button">No, Go Back</a>
</div>

</body>
</html>
