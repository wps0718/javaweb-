<%--
  Created by IntelliJ IDEA.
  User: 17337
  Date: 2024/12/28
  Time: 0:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Score.StudentScoreDAO" %>

<html>
<head>
  <title>删除成绩</title>
</head>
<body>

<%
  String idParam = request.getParameter("id");
  if (idParam != null && !idParam.isEmpty()) {
    try {
      int id = Integer.parseInt(idParam);
      StudentScoreDAO scoreDAO = new StudentScoreDAO();
      boolean deleted = scoreDAO.deleteScore(id);  // 删除成绩
      if (deleted) {
        out.println("<p>成绩已删除。</p>");
      } else {
        out.println("<p>删除失败，请稍后重试。</p>");
      }
    } catch (NumberFormatException e) {
      out.println("<p>无效的成绩 ID。</p>");
    }
  } else {
    out.println("<p>没有提供成绩 ID。</p>");
  }
%>

<a href="viewScores.jsp">返回查看成绩</a>

</body>
</html>
