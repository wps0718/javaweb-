<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ComMain.Student" %>
<%@ page import="ComMain.StudentDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="zh">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>学生管理系统 - 首页</title>
  <style>
    /* 页面整体样式 */
    body {
      font-family: 'Arial', sans-serif;
      background-color: #f4f7fc;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: column;
      min-height: 100vh;
    }

    .navbar {
      background-color: #333;
      overflow: hidden;
      padding: 12px 0;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .navbar a {
      float: left;
      display: block;
      padding: 12px 20px;
      color: white;
      text-align: center;
      text-decoration: none;
      font-size: 16px;
      transition: background-color 0.3s;
    }

    .navbar a:hover {
      background-color: #575757;
    }

    h2 {
      text-align: center;
      color: #333;
      font-size: 26px;
      margin: 30px 0;
    }

    /* 表格样式 */
    table {
      width: 90%;
      margin: 20px auto;
      border-collapse: collapse;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
      background-color: #ffffff;
      border-radius: 8px;
      overflow: hidden;
    }

    th, td {
      border: 1px solid #ddd;
      padding: 12px 20px;
      text-align: left;
      font-size: 14px;
    }

    th {
      background-color: #4CAF50;
      color: white;
      font-weight: bold;
    }

    tr:nth-child(even) {
      background-color: #f9f9f9;
    }

    tr:hover {
      background-color: #f1f1f1;
    }

    .pagination {
      display: flex;
      justify-content: center;
      list-style-type: none;
      padding: 0;
      margin: 20px 0;
    }

    .pagination a {
      color: #4CAF50;
      padding: 8px 16px;
      text-decoration: none;
      border: 1px solid #ddd;
      margin: 0 4px;
      border-radius: 4px;
      transition: background-color 0.3s;
    }

    .pagination a.active {
      background-color: #4CAF50;
      color: white;
      border: 1px solid #4CAF50;
    }

    .pagination a:hover:not(.active) {
      background-color: #ddd;
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
      display: block;
      width: 120px;
      margin: 0 auto;
      font-size: 16px;
    }

    .back-button:hover {
      background-color: #45a049;
    }

    .action-buttons a {
      margin-right: 10px;
      text-decoration: none;
      color: white;
      padding: 5px 12px;
      border-radius: 4px;
      transition: background-color 0.3s;
    }

    .action-buttons a.update {
      background-color: #2196F3;
    }

    .action-buttons a.update:hover {
      background-color: #0B7FFF;
    }

    .action-buttons a.delete {
      background-color: #f44336;
    }

    .action-buttons a.delete:hover {
      background-color: #e53935;
    }
  </style>
</head>
<body>

<!-- 导航条 -->
<div class="navbar">
  <a href="addStudent.jsp">新增学生</a>
  <a href="searchStudent.jsp">查询学生</a>
  <a href="editStudent.jsp">修改数据</a>
  <a href="viewScores.jsp">查看成绩</a> <!-- 新增查看成绩的按钮 -->
  <a href="LogoutServlet">Logout</a>
</div>

<h2>欢迎, ${username}!</h2>

<!-- 学生数据表格 -->
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Age</th>
    <th>Gender</th>
    <th>Grade</th>
    <th>Class</th>
    <th>College</th>
    <th>Student ID</th>
    <th>操作</th>
  </tr>
  </thead>
  <tbody>
  <%
    int pageNum = 1;
    int pageSize = 5;
    String pageParam = request.getParameter("pageNum");
    if (pageParam != null && !pageParam.isEmpty()) {
      pageNum = Integer.parseInt(pageParam);
    }

    StudentDAO studentDAO = new StudentDAO();
    List<Student> students = studentDAO.getStudentsByPage(pageNum, pageSize);
    int totalStudents = studentDAO.getTotalStudents();
    int totalPages = (int) Math.ceil((double) totalStudents / pageSize);

    for (Student student : students) {
  %>
  <tr>
    <td><%= student.getId() %></td>
    <td><%= student.getName() %></td>
    <td><%= student.getAge() %></td>
    <td><%= student.getGender() %></td>
    <td><%= student.getGrade() %></td>
    <td><%= student.getClass_() %></td>
    <td><%= student.getCollege() %></td>
    <td><%= student.getStudentId() %></td>
    <td class="action-buttons">
      <form action="DeleteStudentServlet" method="post" style="display:inline;">
        <input type="hidden" name="id" value="<%= student.getId() %>">
        <button type="submit" class="delete">删除</button>
      </form>
      <a href="updateStudent.jsp?id=<%= student.getId() %>" class="update">修改</a>

      <a href="editScore.jsp?id=<%= student.getId() %>" class="update">查看成绩</a>
    </td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>

<!-- 分页控件 -->
<div class="pagination">
  <%
    if (pageNum > 1) {
  %>
  <a href="?pageNum=<%= pageNum - 1 %>">&laquo;</a>
  <%
    }
    for (int i = 1; i <= totalPages; i++) {
      if (i == pageNum) {
  %>
  <a class="active" href="?pageNum=<%= i %>"><%= i %></a>
  <%
  } else {
  %>
  <a href="?pageNum=<%= i %>"><%= i %></a>
  <%
      }
    }
    if (pageNum < totalPages) {
  %>
  <a href="?pageNum=<%= pageNum + 1 %>">&raquo;</a>
  <%
    }
  %>
</div>

<!-- 返回按钮 -->
<a href="main.jsp" class="back-button">返回首页</a>

</body>
</html>
