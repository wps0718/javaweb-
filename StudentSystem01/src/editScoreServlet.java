import Score.StudentScoreDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/editScoreServlet")
public class editScoreServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(editScoreServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求和响应的字符编码为 UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String studentId = request.getParameter("studentId");
        String scoreParam = request.getParameter("score");
        String subject = request.getParameter("subject");

        // 记录接收到的参数
        LOGGER.info("Received parameters: id=" + idParam + ", name=" + name + ", studentId=" + studentId + ", score=" + scoreParam + ", subject=" + subject);

        // 检查输入的参数是否有效
        if (idParam != null && !idParam.isEmpty() && name != null && !name.isEmpty() && studentId != null && !studentId.isEmpty() && scoreParam != null && !scoreParam.isEmpty() && subject != null && !subject.isEmpty()) {
            try {
                // 解析 ID 和成绩
                int id = Integer.parseInt(idParam);
                double score = Double.parseDouble(scoreParam);

                // 使用 DAO 更新成绩
                StudentScoreDAO scoreDAO = new StudentScoreDAO();
                boolean updated = scoreDAO.updateScore(id, name, studentId, score, subject);  // 更新成绩

                // 根据更新结果进行页面跳转或错误提示
                if (updated) {
                    response.sendRedirect("viewScores.jsp");  // 更新成功，重定向到查看成绩页面
                } else {
                    request.setAttribute("error", "更新失败");
                    request.getRequestDispatcher("editScore.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                LOGGER.severe("无效的数字格式: " + e.getMessage());
                request.setAttribute("error", "无效的输入");
                request.getRequestDispatcher("editScore.jsp").forward(request, response);
            }
        } else {
            // 如果有字段为空，则返回错误信息
            request.setAttribute("error", "所有字段都必须填写");
            request.getRequestDispatcher("editScore.jsp").forward(request, response);
        }
    }
}



