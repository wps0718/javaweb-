import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 使用 @WebServlet 注解来映射 servlet 到 URL "/LoginServlet"
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 处理 GET 请求，转发到登录页面
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 转发请求到 login.jsp 页面
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    // 处理 POST 请求，处理用户登录逻辑
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取表单提交的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 加载 MySQL JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 建立数据库连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb", "root", "WPS5518836wps");
            // 准备 SQL 查询语句
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            pstmt = conn.prepareStatement(sql);
            // 设置 SQL 参数
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            // 执行查询操作
            rs = pstmt.executeQuery();

            // 检查结果集是否有数据
            if (rs.next()) {
                // 如果存在匹配的用户，创建会话并设置用户名属性
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                // 重定向到 main.jsp 页面
                response.sendRedirect("main.jsp");
            } else {
                // 如果不存在匹配的用户，设置错误消息并转发回登录页面
                request.setAttribute("message", "用户名或密码无效！");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            // 捕获并打印异常信息
            e.printStackTrace();
            // 设置错误消息并转发回登录页面
            request.setAttribute("message", "登录过程中出现错误: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } finally {
            // 关闭 ResultSet、PreparedStatement 和 Connection
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
