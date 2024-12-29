<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ComMain.Student" %>
<%@ page import="ComMain.StudentDAO" %>
<html>
<head>
    <title>学生信息查询</title>
    <style>
        /* 页面基础样式 */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        h2 {
            text-align: center;
            color: #333;
            font-size: 24px;
            margin-top: 20px;
        }

        /* 查询表单样式 */
        form {
            background-color: #fff;
            width: 60%;
            margin: 0 auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        form label {
            font-weight: bold;
        }

        form input {
            width: calc(100% - 16px);
            padding: 8px;
            margin: 8px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }

        form button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        form button:hover {
            background-color: #45a049;
        }

        /* 表格样式 */
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            background-color: #ffffff;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px 20px;
            text-align: left;
            font-size: 14px;
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

        /* 提示样式 */
        .no-results {
            text-align: center;
            font-size: 18px;
            color: #d9534f;
        }

        /* 返回按钮样式 */
        .back-button {
            display: block;
            width: 150px;
            margin: 20px auto;
            text-align: center;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }

        .back-button:hover {
            background-color: #45a049;
        }

    </style>
</head>
<body>

<!-- 页面标题 -->
<h2>学生信息查询</h2>

<!-- 查询表单 -->
<form action="searchStudent.jsp" method="get">
    <label for="name">姓名:</label><input type="text" name="name" id="name" /><br>
    <label for="age">年龄:</label><input type="text" name="age" id="age" /><br>
    <label for="gender">性别:</label><input type="text" name="gender" id="gender" /><br>
    <label for="grade">年级:</label><input type="text" name="grade" id="grade" /><br>
    <label for="class">班级:</label><input type="text" name="class" id="class" /><br>
    <label for="college">学院:</label><input type="text" name="college" id="college" /><br>
    <label for="studentId">学号:</label><input type="text" name="student_id" id="studentId" /><br>
    <button type="submit">查询</button>
</form>

<hr>

<!-- 显示查询结果 -->
<%
    // 获取查询表单的参数
    String name = request.getParameter("name");
    String age = request.getParameter("age");
    String gender = request.getParameter("gender");
    String grade = request.getParameter("grade");
    String className = request.getParameter("class");
    String college = request.getParameter("college");
    String studentId = request.getParameter("student_id");

    // 调用  类进行查询
    StudentDAO studentDAO = new StudentDAO();
    List<Student> students = studentDAO.searchStudents(name, age, gender, grade, className, college, studentId);
%>

<!-- 学生信息表格 -->
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
        <th>年级</th>
        <th>班级</th>
        <th>学院</th>
        <th>学号</th>
    </tr>
    </thead>
    <tbody>
    <%
        // 如果查询结果不为空，显示学生信息
        if (students != null && !students.isEmpty()) {
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
    </tr>
    <%
        }
    } else {
    %>
    <tr><td colspan="8" class="no-results">没有找到匹配的学生信息。</td></tr>
    <%
        }
    %>
    </tbody>
</table>

<!-- 返回首页按钮 -->
<a href="main.jsp" class="back-button">返回首页</a>

</body>
</html>
