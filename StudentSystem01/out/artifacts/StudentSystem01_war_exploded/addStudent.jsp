<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增学生</title>
    <style>
        /* 设置表单样式 */
        form {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            background-color: #f4f4f9;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        label {
            font-weight: bold;
        }

        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .form-group {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

<h2 style="text-align: center;">新增学生信息</h2>
<form method="post" action="AddStudentServlet" accept-charset="UTF-8">
    <div class="form-group">
        <label for="name">姓名:</label>
        <input type="text" id="name" name="name" required>
    </div>
    <div class="form-group">
        <label for="age">年龄:</label>
        <input type="text" id="age" name="age" required>
    </div>
    <div class="form-group">
        <label for="gender">性别:</label>
        <input type="text" id="gender" name="gender" required>
    </div>
    <div class="form-group">
        <label for="grade">年级:</label>
        <input type="text" id="grade" name="grade" required>
    </div>
    <div class="form-group">
        <label for="class">班级:</label>
        <input type="text" id="class" name="class" required>
    </div>
    <div class="form-group">
        <label for="college">学院:</label>
        <input type="text" id="college" name="college" required>
    </div>
    <div class="form-group">
        <label for="student_id">学号:</label>
        <input type="text" id="student_id" name="student_id" required>
    </div>

    <input type="submit" value="提交">
</form>

</body>
</html>
