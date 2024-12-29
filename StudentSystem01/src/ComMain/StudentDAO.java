package ComMain;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class StudentDAO {
    // 数据库连接信息
    private static final String URL = "jdbc:mysql://localhost:3306/usersdb";
    private static final String USER = "root";
    private static final String PASSWORD = "WPS5518836wps"; // 请根据实际情况更新数据库密码

    // 添加学生
    public int addStudent(Student student) {
        int result = 0; // 用于记录执行的结果
        Connection conn = null; // 数据库连接
        PreparedStatement pstmt = null; // PreparedStatement 用于执行 SQL

        try {
            // 加载 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = getConnection(URL, USER, PASSWORD); // 建立数据库连接

            // 插入学生数据的 SQL 语句
            String sql = "INSERT INTO students (name, age, gender, grade, class, college, student_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getName()); // 设置学生姓名
            pstmt.setInt(2, student.getAge()); // 设置学生年龄
            pstmt.setString(3, student.getGender()); // 设置学生性别
            pstmt.setString(4, student.getGrade()); // 设置学生年级
            pstmt.setString(5, student.getClass_()); // 设置学生班级
            pstmt.setString(6, student.getCollege()); // 设置学生学院
            pstmt.setString(7, student.getStudentId()); // 设置学生学号

            result = pstmt.executeUpdate(); // 执行插入操作
        } catch (Exception e) {
            e.printStackTrace(); // 捕获异常并打印错误信息
        } finally {
            try {
                // 关闭数据库连接和 statement
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result; // 返回操作结果
    }

    // 获取学生列表（分页）
    public List<Student> getStudentsByPage(int pageNum, int pageSize) {
        List<Student> students = new ArrayList<>(); // 存储查询到的学生列表
        Connection conn = null; // 数据库连接
        PreparedStatement pstmt = null; // PreparedStatement 用于执行 SQL
        ResultSet rs = null; // 结果集，用于存储查询结果

        try {
            // 加载 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = getConnection(URL, USER, PASSWORD); // 建立数据库连接

            // 计算分页偏移量
            int offset = (pageNum - 1) * pageSize;

            // 查询语句，使用 LIMIT 和 OFFSET 来实现分页
            String sql = "SELECT * FROM students LIMIT ? OFFSET ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pageSize); // 设置每页显示的学生数
            pstmt.setInt(2, offset); // 设置偏移量

            rs = pstmt.executeQuery(); // 执行查询

            // 遍历结果集，构建学生对象
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setGender(rs.getString("gender"));
                student.setGrade(rs.getString("grade"));
                student.setClass_(rs.getString("class"));
                student.setCollege(rs.getString("college"));
                student.setStudentId(rs.getString("student_id"));
                students.add(student); // 将学生对象加入列表
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // 捕获异常并打印错误信息
        } finally {
            try {
                // 关闭数据库连接和相关资源
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return students; // 返回查询到的学生列表
    }

    // 获取总学生数量（用于分页）
    public int getTotalStudents() {
        int total = 0; // 学生总数
        Connection conn = null; // 数据库连接
        PreparedStatement pstmt = null; // PreparedStatement 用于执行 SQL
        ResultSet rs = null; // 结果集，用于存储查询结果

        try {
            // 加载 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = getConnection(URL, USER, PASSWORD); // 建立数据库连接

            // 查询学生总数的 SQL 语句
            String sql = "SELECT COUNT(*) FROM students";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(); // 执行查询

            if (rs.next()) {
                total = rs.getInt(1); // 获取总学生数量
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // 捕获异常并打印错误信息
        } finally {
            try {
                // 关闭数据库连接和相关资源
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return total; // 返回总学生数量
    }

    // 根据多个条件查询学生
    public List<Student> searchStudents(String name, String age, String gender, String grade, String className, String college, String studentId) {
        List<Student> students = new ArrayList<>(); // 存储查询到的学生列表
        Connection conn = null; // 数据库连接
        PreparedStatement pstmt = null; // PreparedStatement 用于执行 SQL
        ResultSet rs = null; // 结果集，用于存储查询结果

        try {
            // 加载 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = getConnection(URL, USER, PASSWORD); // 建立数据库连接

            // 构建动态 SQL 查询语句
            StringBuilder sql = new StringBuilder("SELECT * FROM students WHERE 1=1");

            List<Object> params = new ArrayList<>(); // 存储查询参数

            // 根据传入的查询条件动态构建 SQL 语句
            if (name != null && !name.isEmpty()) {
                sql.append(" AND name LIKE ?");
                params.add("%" + name + "%"); // 模糊查询姓名
            }
            if (age != null && !age.isEmpty()) {
                sql.append(" AND age = ?");
                params.add(Integer.parseInt(age)); // 精确查询年龄
            }
            if (gender != null && !gender.isEmpty()) {
                sql.append(" AND gender = ?");
                params.add(gender); // 精确查询性别
            }
            if (grade != null && !grade.isEmpty()) {
                sql.append(" AND grade LIKE ?");
                params.add("%" + grade + "%"); // 模糊查询年级
            }
            if (className != null && !className.isEmpty()) {
                sql.append(" AND class LIKE ?");
                params.add("%" + className + "%"); // 模糊查询班级
            }
            if (college != null && !college.isEmpty()) {
                sql.append(" AND college LIKE ?");
                params.add("%" + college + "%"); // 模糊查询学院
            }
            if (studentId != null && !studentId.isEmpty()) {
                sql.append(" AND student_id LIKE ?");
                params.add("%" + studentId + "%"); // 模糊查询学号
            }

            pstmt = conn.prepareStatement(sql.toString()); // 创建 PreparedStatement

            // 设置查询参数
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            rs = pstmt.executeQuery(); // 执行查询

            // 遍历结果集，构建学生对象
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setGender(rs.getString("gender"));
                student.setGrade(rs.getString("grade"));
                student.setClass_(rs.getString("class"));
                student.setCollege(rs.getString("college"));
                student.setStudentId(rs.getString("student_id"));
                students.add(student); // 将学生对象加入列表
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // 捕获异常并打印错误信息
        } finally {
            try {
                // 关闭数据库连接和相关资源
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return students; // 返回查询到的学生列表
    }

    // 根据 ID 获取学生信息
    public Student getStudentById(int id) {
        Student student = null; // 存储学生对象
        Connection conn = null; // 数据库连接
        PreparedStatement stmt = null; // PreparedStatement 用于执行 SQL
        ResultSet rs = null; // 结果集，用于存储查询结果

        try {
            String sql = "SELECT * FROM students WHERE id = ?"; // 查询学生信息的 SQL
            conn = getConnection(URL, USER, PASSWORD); // 建立数据库连接
            stmt = conn.prepareStatement(sql); // 创建 PreparedStatement
            stmt.setInt(1, id); // 设置查询的学生 ID
            rs = stmt.executeQuery(); // 执行查询

            // 如果查询到学生信息，返回学生对象
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setGender(rs.getString("gender"));
                student.setGrade(rs.getString("grade"));
                student.setClass_(rs.getString("class"));
                student.setCollege(rs.getString("college"));
                student.setStudentId(rs.getString("student_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 捕获异常并打印错误信息
        } finally {
            try {
                // 关闭数据库连接和相关资源
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return student; // 返回学生对象
    }

    // 更新学生信息
    public boolean updateStudent(Student student) {
        boolean result = false; // 用于记录更新是否成功
        Connection conn = null; // 数据库连接
        PreparedStatement pstmt = null; // PreparedStatement 用于执行 SQL

        try {
            // 加载 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = getConnection(URL, USER, PASSWORD); // 建立数据库连接

            // 更新学生信息的 SQL 语句
            String sql = "UPDATE students SET name = ?, age = ?, gender = ?, grade = ?, class = ?, college = ?, student_id = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getName()); // 设置学生姓名
            pstmt.setInt(2, student.getAge()); // 设置学生年龄
            pstmt.setString(3, student.getGender()); // 设置学生性别
            pstmt.setString(4, student.getGrade()); // 设置学生年级
            pstmt.setString(5, student.getClass_()); // 设置学生班级
            pstmt.setString(6, student.getCollege()); // 设置学生学院
            pstmt.setString(7, student.getStudentId()); // 设置学生学号
            pstmt.setInt(8, student.getId()); // 设置学生 ID

            int rowsAffected = pstmt.executeUpdate(); // 执行更新
            if (rowsAffected > 0) {
                result = true; // 更新成功
            }
        } catch (Exception e) {
            e.printStackTrace(); // 捕获异常并打印错误信息
        } finally {
            try {
                // 关闭数据库连接和相关资源
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result; // 返回更新结果
    }

    public boolean editStudent(Student student) {
        String sql = "UPDATE students SET name = ?, age = ?, gender = ?, grade = ?, college = ?, class_ = ?, studentId = ? WHERE id = ?";

        // 数据库连接的URL、用户名和密码
        String url = "jdbc:mysql://localhost:3306/usersdb";  // 替换为你的数据库URL
        String username = "root";  // 替换为你的数据库用户名
        String password = "WPS5518836wps";  // 替换为你的数据库密码

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getGender());
            ps.setString(4, student.getGrade());
            ps.setString(5, student.getCollege());
            ps.setString(6, student.getClass_());
            ps.setString(7, student.getStudentId());
            ps.setInt(8, student.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 删除学生信息
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?"; // 删除学生的 SQL 语句
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id); // 设置要删除的学生 ID

            int affectedRows = stmt.executeUpdate(); // 执行删除
            return affectedRows > 0; // 返回删除是否成功
        } catch (SQLException e) {
            e.printStackTrace(); // 捕获异常并打印错误信息
        }
        return false; // 删除失败
    }

    public Student getStudentByName(String name) {
        Student student = null;
        String query = "SELECT * FROM students WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); // Using DriverManager.getConnection
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setGender(rs.getString("gender"));
                student.setClass_(rs.getString("class_"));
                student.setCollege(rs.getString("college"));
                student.setStudentId(rs.getString("student_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public Student getStudentByStudentId(String studentId) {
        Student student = null;
        String query = "SELECT * FROM students WHERE student_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); // Use DriverManager.getConnection
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setGender(rs.getString("gender"));
                student.setClass_(rs.getString("class_"));
                student.setCollege(rs.getString("college"));
                student.setStudentId(rs.getString("student_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }
}
