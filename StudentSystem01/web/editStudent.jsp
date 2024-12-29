<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Score.StudentScore" %>
<%@ page import="Score.StudentScoreDAO" %> <!-- 修改这里 -->
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="zh">
<head>
  <meta charset="UTF-8">
  <title>编辑学生信息</title>
  <style>
    /* 页面整体样式 */
    body {
      font-family: 'Arial', sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: column;
      min-height: 100vh;
    }

    .navbar {
      background-color: #333;
      overflow: hidden;
      padding: 10px 0;
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
      font-size: 24px;
      margin-top: 20px;
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

    .form-container {
      width: 80%;
      margin: 20px auto;
      background-color: white;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      box-sizing: border-box;
    }

    .form-container input,
    .form-container select {
      width: 100%;
      padding: 8px;
      margin-bottom: 12px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    .student-info-table {
      width: 100%;
      margin-top: 20px;
      border-collapse: collapse;
    }

    .student-info-table th, .student-info-table td {
      padding: 10px;
      border: 1px solid #ddd;
      text-align: center;
    }

    .student-info-table th {
      background-color: #f2f2f2;
    }

    .student-info-table td {
      background-color: #f9f9f9;
    }

    .action-buttons a {
      margin-left: 10px;
      padding: 8px 12px;
      background-color: #2196F3;
      color: white;
      border-radius: 4px;
      text-decoration: none;
    }

    .action-buttons a:hover {
      background-color: #0B7FFF;
    }
  </style>
</head>
<body>

<!-- 导航条 -->
<div class="navbar">
  <a href="addStudent.jsp">新增学生</a>
  <a href="searchStudent.jsp">查询学生</a>
  <a href="editStudent.jsp">修改数据</a>
  <a href="viewScores.jsp">查看成绩</a>
  <a href="LogoutServlet">Logout</a>
</div>

<h2>编辑学生信息</h2>

<!-- 学生查询表单 -->
<div class="form-container">
  <form action="editStudent.jsp" method="get">
    <label for="searchByName">姓名：</label>
    <input type="text" id="searchByName" name="name" placeholder="输入姓名进行查询" value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>">

    <label for="searchByStudentId">学号：</label>
    <input type="text" id="searchByStudentId" name="studentId" placeholder="输入学号进行查询" value="<%= request.getParameter("studentId") != null ? request.getParameter("studentId") : "" %>">

    <button type="submit">查询</button>
  </form>
</div>

<%
  String nameParam = request.getParameter("name");
  String studentIdParam = request.getParameter("studentId");
  List<StudentScore> students = null;

  // 查询逻辑：根据姓名或学号查询
  if ((nameParam != null && !nameParam.isEmpty()) || (studentIdParam != null && !studentIdParam.isEmpty())) {
    try {
      StudentScoreDAO studentDAO = new StudentScoreDAO();

      // 根据学号或姓名进行查询
      if (studentIdParam != null && !studentIdParam.isEmpty()) {
        students = studentDAO.getStudentsByStudentId(studentIdParam);
      }
      if (students == null || students.isEmpty()) {
        if (nameParam != null && !nameParam.isEmpty()) {
          students = studentDAO.getStudentsByName(nameParam);
        }
      }
    } catch (Exception e) {
      request.setAttribute("error", "查询出错: " + e.getMessage());
    }
  }

  if (students == null || students.isEmpty()) {
%>
<p class="error-message">未找到匹配的学生信息。</p>
<%
} else {
%>

<!-- 学生信息表格 -->
<table class="student-info-table">
  <thead>
  <tr>
    <th>学号</th>
    <th>姓名</th>
    <th>年龄</th>
    <th>性别</th>
    <th>年级</th>
    <th>班级</th>
    <th>考试科目</th>
    <th>分数</th>
    <th>操作</th>
  </tr>
  </thead>
  <tbody>
  <%
    for (StudentScore student : students) {
  %>
  <tr>
    <td><%= student.getStudentId() %></td>
    <td><%= student.getName() %></td>
    <td><%= student.getAge() %></td>
    <td><%= student.getGender() %></td>
    <td><%= student.getGrade() %></td>
    <td><%= student.getClassName() %></td>
    <td><%= student.getSubject() %></td>
    <td><%= student.getScore() %></td>
    <td class="action-buttons">
      <a href="updateStudent.jsp?id=<%= student.getId() %>">修改信息</a>
    </td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>

<%
  }
%>

<!-- 错误信息显示 -->
<c:if test="${not empty error}">
  <p style="color: red;">${error}</p>
</c:if>

<!-- 返回按钮 -->
<a href="main.jsp" class="back-button">返回首页</a>

</body>
</html>



