import ComMain.Student;
import ComMain.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/editStudentServlet")
public class editStudentServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(editStudentServlet.class.getName());

    // 查询逻辑
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 转发回 JSP 页面进行展示
        request.getRequestDispatcher("editStudent.jsp").forward(request, response);
    }

    // 修改逻辑
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取表单数据
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String gender = request.getParameter("gender");
            String grade = request.getParameter("grade");
            String class_ = request.getParameter("class_");
            String college = request.getParameter("college");

            // 参数验证
            if (name == null || name.trim().isEmpty() || gender == null || gender.trim().isEmpty()) {
                throw new IllegalArgumentException("姓名和性别不能为空！");
            }

            // 创建 Student 对象并设置属性
            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setAge(age);
            student.setGender(gender);
            student.setGrade(grade);  // 新增年级字段
            student.setClass_(class_);
            student.setCollege(college);

            // 更新数据库
            StudentDAO studentDAO = new StudentDAO();
            boolean isUpdated = studentDAO.editStudent(student);

            if (isUpdated) {
                response.sendRedirect("main.jsp?message=修改成功");
            } else {
                throw new Exception("更新失败");
            }
        } catch (NumberFormatException e) {
            // 捕获并处理格式错误
            LOGGER.severe("输入格式错误: " + e.getMessage());
            request.setAttribute("error", "无效的输入，请检查格式！");
            request.getRequestDispatcher("editStudent.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            // 捕获并处理业务逻辑错误，如姓名和性别不能为空
            LOGGER.severe("输入错误: " + e.getMessage());
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("editStudent.jsp").forward(request, response);
        } catch (Exception e) {
            // 捕获其他所有异常并处理
            LOGGER.severe("更新学生信息时出错: " + e.getMessage());
            request.setAttribute("error", "更新失败，请稍后重试！");
            request.getRequestDispatcher("editStudent.jsp").forward(request, response);
        }
    }
}
