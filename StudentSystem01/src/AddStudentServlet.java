import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/usersdb?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "WPS5518836wps";  // Update the password here

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求编码为 UTF-8
        request.setCharacterEncoding("UTF-8");

        // 获取表单数据
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
        String grade = request.getParameter("grade");
        String class_ = request.getParameter("class");
        String college = request.getParameter("college");
        String studentId = request.getParameter("student_id");

        // 数据库连接
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "INSERT INTO students (name, age, gender, grade, class, college, student_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, Integer.parseInt(age));
            pstmt.setString(3, gender);
            pstmt.setString(4, grade);
            pstmt.setString(5, class_);
            pstmt.setString(6, college);
            pstmt.setString(7, studentId);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                // 成功插入数据，重定向到显示学生信息页面
                response.sendRedirect("main.jsp");
            } else {
                // 插入失败，返回错误信息
                response.getWriter().println("学生信息添加失败！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("数据库连接失败！" + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
