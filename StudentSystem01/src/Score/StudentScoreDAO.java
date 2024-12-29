package Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentScoreDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/usersdb"; // 更新为你的数据库详情
    private static final String JDBC_USER = "root"; // 数据库用户名
    private static final String JDBC_PASSWORD = "WPS5518836wps"; // 数据库密码
    private static final Logger LOGGER = Logger.getLogger(StudentScoreDAO.class.getName());

    // 获取所有学生成绩
    public List<StudentScore> getAllScores() {
        List<StudentScore> scores = new ArrayList<>();
        String sql = "SELECT id, name, student_id, age, gender,grade, score, subject FROM student_scores"; // 替换为你的表名

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                StudentScore studentScore = new StudentScore(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("student_id"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("grade"),
                        rs.getString("className"),
                        rs.getString("subject"),
                        rs.getDouble("score")
                );
                scores.add(studentScore);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching all student scores: ", e);
        }

        return scores;
    }

    // 根据姓名查询学生信息
    public List<StudentScore> getStudentsByName(String name) {
        List<StudentScore> students = new ArrayList<>();
        String sql = "SELECT id, name, student_id, age, gender, grade, className, subject,score FROM student_scores WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    StudentScore studentScore = new StudentScore(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("student_id"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("grade"),
                            rs.getString("className"),
                            rs.getString("subject"),
                            rs.getDouble("score")
                    );
                    students.add(studentScore);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching students by name: ", e);
        }
        return students;
    }

    // 根据学号查询学生信息
    public List<StudentScore> getStudentsByStudentId(String studentId) {
        List<StudentScore> students = new ArrayList<>();
        String sql = "SELECT id, name, student_id, age, gender, grade, className, subject,score FROM student_scores WHERE student_id = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    StudentScore studentScore = new StudentScore(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("student_id"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("grade"),
                            rs.getString("className"),
                            rs.getString("subject"),
                            rs.getDouble("score")
                    );
                    students.add(studentScore);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching students by student ID: ", e);
        }

        return students;
    }

    // 获取单个学生成绩通过 ID
    public StudentScore getScoreById(int id) {
        String sql = "SELECT id, name, student_id, age, gender, score, subject, grade, className FROM student_scores WHERE id = ?";
        StudentScore score = null;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                score = new StudentScore(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("student_id"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("grade"),
                        rs.getString("className"),
                        rs.getString("subject"),
                        rs.getDouble("score")
                );
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching score by ID: ", e);
        }

        return score;
    }

    // 更新学生成绩
    public boolean updateScore(int id, String name, String studentId, double score, String subject) {
        String sql = "UPDATE student_scores SET name = ?, student_id = ?, score = ?, subject = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, studentId);
            stmt.setDouble(3, score);
            stmt.setString(4, subject);
            stmt.setInt(5, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating score: ", e);
            return false;
        }
    }

    // 删除学生成绩
    public boolean deleteScore(int id) {
        String sql = "DELETE FROM student_scores WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting score: ", e);
            return false;
        }
    }
}