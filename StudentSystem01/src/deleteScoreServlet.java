import Score.StudentScoreDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteScoreServlet")
public class deleteScoreServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                StudentScoreDAO scoreDAO = new StudentScoreDAO();
                boolean deleted = scoreDAO.deleteScore(id);  // 删除成绩

                if (deleted) {
                    response.sendRedirect("viewScores.jsp");  // 删除成功，重定向到查看成绩页面
                } else {
                    response.getWriter().write("删除失败，请稍后重试。");
                }
            } catch (NumberFormatException e) {
                response.getWriter().write("无效的成绩 ID。");
            }
        } else {
            response.getWriter().write("没有提供成绩 ID。");
        }
    }
}
