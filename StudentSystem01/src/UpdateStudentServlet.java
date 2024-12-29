import ComMain.Student;
import ComMain.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
    private StudentDAO dao = new StudentDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 设置请求编码
        response.setContentType("text/html;charset=UTF-8"); // 设置响应编码

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String grade = request.getParameter("grade");
        String class_ = request.getParameter("class_");
        String college = request.getParameter("college");
        String studentId = request.getParameter("studentId");

        Student student = new Student(id, name, age, gender, grade, class_, college, studentId, "", 0.0); // 假设科目和分数为空
        boolean updated = dao.updateStudent(student);  // 调用更新方法

        if (updated) {
            response.sendRedirect("main.jsp");  // 更新成功，跳转到 main.jsp
        } else {
            request.setAttribute("message", "Failed to update student.");
            request.getRequestDispatcher("error.jsp").forward(request, response);  // 更新失败，转发到 error.jsp
        }
    }
}
