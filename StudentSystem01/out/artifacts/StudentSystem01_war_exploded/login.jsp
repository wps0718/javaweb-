<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .login-container {
            width: 300px;
            margin: 100px auto;
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
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
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
        .register-link {
            display: block;
            text-align: center;
            margin-top: 20px;
        }
        .register-link a {
            color: #337ab7;
            text-decoration: none;
        }
        .register-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>登录页面</h2>
    <form action="LoginServlet" method="post">
        用户名: <input type="text" name="username" required><br><br>
        密  码: <input type="password" name="password" required><br><br>
        <input type="submit" value="登录">
    </form>
    <div class="register-link">
        <a href="register.jsp">点击跳转注册页面</a>
    </div>
    <p>${message}</p>
</div>
</body>
</html>
