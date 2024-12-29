import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {

    // 数据库连接信息
    private static final String URL = "jdbc:mysql://localhost:3306/usersdb";
    private static final String USER = "root";
    private static final String PASSWORD = "WPS5518836wps";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("id");

        response.setContentType("text/html;charset=UTF-8");

        if (studentId == null || studentId.isEmpty()) {
            response.getWriter().println("<html><body><p>无效的学生ID，请重试。</p><a href='main.jsp'>返回首页</a></body></html>");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM students WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, Integer.parseInt(studentId));
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    response.getWriter().println("<html><body><p>学生信息删除成功！</p><a href='main.jsp'>返回首页</a></body></html>");
                } else {
                    response.getWriter().println("<html><body><p>未找到学生记录，删除失败。</p><a href='main.jsp'>返回首页</a></body></html>");
                }
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().println("<html><body><p>发生错误：" + e.getMessage() + "</p><a href='main.jsp'>返回首页</a></body></html>");
        }
    }
}
