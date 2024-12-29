import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 使用 @WebServlet 注解来映射 servlet 到 URL "/RegisterServlet"
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 处理 GET 请求，转发到注册页面
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 转发请求到 register.jsp 页面
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    // 处理 POST 请求，处理用户注册逻辑
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取表单提交的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");

        // 检查密码和确认密码是否匹配
        if (!password.equals(confirmPassword)) {
            // 如果不匹配，设置错误消息并转发回注册页面
            request.setAttribute("message", "密码不匹配！");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // 加载 MySQL JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 建立数据库连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb", "root", "WPS5518836wps");
            // 准备 SQL 插入语句
            String sql = "INSERT INTO users (username, password, email, phone, birthday) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            // 设置 SQL 参数
            pstmt.setString(1, username);
            pstmt.setString(2, password); // 在实际应用中，应先对密码进行哈希处理再存储到数据库
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setDate(5, java.sql.Date.valueOf(birthday));
            // 执行插入操作
            int rowsInserted = pstmt.executeUpdate();
            // 检查是否有行被插入
            if (rowsInserted > 0) {
                // 如果成功，设置成功消息并转发到登录页面
                request.setAttribute("message", "注册成功，请返回登录界面进行登录.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            // 捕获并打印异常信息
            e.printStackTrace();
            // 设置错误消息并转发回注册页面
            request.setAttribute("message", "注册过程中出现错误： " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } finally {
            // 关闭 PreparedStatement 和 Connection
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
