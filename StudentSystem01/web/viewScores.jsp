<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Score.StudentScore" %>
<%@ page import="Score.StudentScoreDAO" %>
<%@ page import="java.sql.SQLException" %>

<html>
<head>
  <title>查看成绩</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f7f7f7;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: column;
      align-items: center;
      min-height: 100vh;
    }

    h2 {
      margin-top: 20px;
      color: #333;
    }

    table {
      width: 80%;
      margin-top: 20px;
      border-collapse: collapse;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
      background-color: #ffffff;
    }

    th, td {
      border: 1px solid #ddd;
      padding: 12px 20px;
      text-align: left;
    }

    th {
      background-color: #f2f2f2;
      font-weight: bold;
    }

    tr:nth-child(even) {
      background-color: #f9f9f9;
    }

    tr:hover {
      background-color: #f1f1f1;
    }

    .form-container {
      margin-top: 20px;
      background-color: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    }

    .form-container input[type="text"] {
      padding: 8px;
      margin: 5px 0;
      width: 200px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    .form-container button {
      padding: 10px 20px;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }

    .form-container button:hover {
      background-color: #45a049;
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

<h2>学生成绩</h2>

<!-- 查询表单 -->
<div class="form-container">
  <form action="viewScores.jsp" method="get">
    <label for="name">姓名:</label>
    <input type="text" id="name" name="name" placeholder="请输入学生姓名">
    <label for="studentId">学号:</label>
    <input type="text" id="studentId" name="studentId" placeholder="请输入学号">
    <button type="submit">查询</button>
  </form>
</div>

<!-- 学生成绩数据表格 -->
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>姓名</th>
    <th>学号</th>
    <th>年龄</th>
    <th>性别</th>
    <th>成绩</th>
    <th>课程</th>
    <th>操作</th>
  </tr>
  </thead>
  <tbody>
  <%
    String nameParam = request.getParameter("name");
    String studentIdParam = request.getParameter("studentId");
    StudentScoreDAO scoreDAO = new StudentScoreDAO();
    List<StudentScore> scores = null;

    // 根据查询条件获取成绩
    if (nameParam != null && !nameParam.isEmpty()) {
      scores = scoreDAO.getStudentsByName(nameParam);
    } else if (studentIdParam != null && !studentIdParam.isEmpty()) {
      scores = scoreDAO.getStudentsByStudentId(studentIdParam);
    } else {
      scores = scoreDAO.getAllScores();
    }

    // 遍历成绩并显示
    for (StudentScore score : scores) {
  %>
  <tr>
    <td><%= score.getId() %></td>
    <td><%= score.getName() %></td>
    <td><%= score.getStudentId() %></td>
    <td><%= score.getAge() %></td>
    <td><%= score.getGender() %></td>
    <td><%= score.getScore() %></td>
    <td><%= score.getSubject() %></td>
    <td>
      <a href="editScore.jsp?id=<%= score.getId() %>">编辑</a> |
      <a href="deleteScore.jsp?id=<%= score.getId() %>" onclick="return confirm('确定要删除吗？');">删除</a>
    </td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>

<a href="main.jsp" class="back-button">返回首页</a>

</body>
</html>
