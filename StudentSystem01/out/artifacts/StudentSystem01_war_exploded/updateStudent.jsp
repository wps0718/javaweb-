<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ComMain.Student" %>
<%@ page import="ComMain.StudentDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Update Student</title>
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

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }

        input[type="text"], input[type="number"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
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

<%-- 获取请求中的 'id' 参数 --%>
<%
    String studentIdParam = request.getParameter("id");
    if (studentIdParam == null || studentIdParam.isEmpty()) {
        // 如果没有 'id' 参数，跳转到错误页面
        response.sendRedirect("error.jsp");
        return;
    }

    int studentId = 0;
    try {
        studentId = Integer.parseInt(studentIdParam); // 安全解析 id
    } catch (NumberFormatException e) {
        // 如果解析失败，跳转到错误页面
        System.out.println("Invalid student ID format: " + studentIdParam);
        response.sendRedirect("error.jsp");
        return;
    }

    // 根据 id 获取学生信息
    StudentDAO studentDAO = new StudentDAO();
    Student student = studentDAO.getStudentById(studentId);
    if (student == null) {
        // 如果学生信息不存在，跳转到错误页面
        System.out.println("Student not found with ID: " + studentId);
        response.sendRedirect("error.jsp");
        return;
    }
%>

<!-- 显示学生信息的表单 -->
<form action="UpdateStudentServlet" method="post">
    <input type="hidden" name="id" value="<%= student.getId() %>">

    <label for="name">姓名:</label>
    <input type="text" id="name" name="name" value="<%= student.getName() %>" required>

    <label for="age">Age:</label>
    <input type="number" id="age" name="age" value="<%= student.getAge() %>" required>

    <label for="gender">Gender:</label>
    <input type="text" id="gender" name="gender" value="<%= student.getGender() %>" required>

    <label for="grade">Grade:</label>
    <input type="text" id="grade" name="grade" value="<%= student.getGrade() %>" required>

    <label for="class_">Class:</label>
    <input type="text" id="class_" name="class_" value="<%= student.getClass_() %>" required>

    <label for="college">College:</label>
    <input type="text" id="college" name="college" value="<%= student.getCollege() %>" required>

    <label for="studentId">Student ID:</label>
    <input type="text" id="studentId" name="studentId" value="<%= student.getStudentId() %>" required>

    <button type="submit">Update</button>
</form>

<!-- 返回主页面的按钮 -->
<a href="main.jsp" class="back-button">Back to Main Page</a>

</body>
</html>
