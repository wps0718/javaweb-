<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Score.StudentScore" %>
<%@ page import="Score.StudentScoreDAO" %>

<html>
<head>
  <title>编辑学生成绩（editScore）</title>
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

    .form-container {
      margin-top: 20px;
      background-color: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    }

    .form-container input[type="text"], .form-container input[type="number"], .form-container input[type="submit"] {
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

<h2>编辑学生成绩（editScore）</h2>

<%
  String idParam = request.getParameter("id");
  StudentScoreDAO scoreDAO = new StudentScoreDAO();
  StudentScore score = null;

  if (idParam != null && !idParam.isEmpty()) {
    try {
      int id = Integer.parseInt(idParam);
      score = scoreDAO.getScoreById(id);  // 根据ID获取成绩信息
    } catch (NumberFormatException e) {
      out.println("无效的成绩 ID。");
    }
  }

  if (score == null) {
%>
<p>未找到该成绩信息。</p>
<%
} else {
%>

<!-- 编辑成绩表单 -->
<div class="form-container">
  <form action="editScoreServlet" method="post">
    <input type="hidden" name="id" value="<%= score.getId() %>">

    <label for="name">姓名:</label>
    <input type="text" id="name" name="name" value="<%= score.getName() %>" required><br><br>

    <label for="studentId">学号:</label>
    <input type="text" id="studentId" name="studentId" value="<%= score.getStudentId() %>" required><br><br>

    <label for="score">成绩:</label>
    <input type="number" id="score" name="score" value="<%= score.getScore() %>" required><br><br>

    <label for="subject">课程:</label>
    <input type="text" id="subject" name="subject" value="<%= score.getSubject() %>" required><br><br>

    <button type="submit">更新成绩</button>
  </form>
</div>

<%
  }
%>

<a href="viewScores.jsp" class="back-button">返回查看成绩</a>
<!-- 返回按钮 -->
<a href="main.jsp" class="back-button">返回首页</a>

</body>
</html>



