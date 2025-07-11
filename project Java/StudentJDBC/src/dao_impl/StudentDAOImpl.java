package dao_impl;

import dao.StudentDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Student;

//phần thực hiện các phương thức chính 
//cốt phân ra là để tách biệt phần giao diện và phần thực hiện, giống như tạo file .h và .cpp trong c++

public class StudentDAOImpl implements StudentDAO {
    private final Connection conn; // ✅ thêm final vì conn không thay đổi

    public StudentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO Student (StudentID, FullName, ClassName, BirthDate, Gender) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getStudentID());
            stmt.setString(2, student.getFullName());
            stmt.setString(3, student.getClassName());
            stmt.setDate(4, Date.valueOf(student.getBirthDate()));
            stmt.setString(5, student.getGender());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            // e.printStackTrace(); // ❌ hạn chế dùng
        }
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE Student SET FullName = ?, ClassName = ?, BirthDate = ?, Gender = ? WHERE StudentID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getFullName());
            stmt.setString(2, student.getClassName());
            stmt.setDate(3, Date.valueOf(student.getBirthDate()));
            stmt.setString(4, student.getGender());
            stmt.setString(5, student.getStudentID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
        }
    }

    @Override
    public void deleteStudent(String studentId) {
        String sql = "DELETE FROM Student WHERE StudentID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
        }
    }

    @Override
    public Student getStudentById(String studentId) {
        String sql = "SELECT * FROM Student WHERE StudentID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getString("StudentID"),
                    rs.getString("FullName"),
                    rs.getString("ClassName"),
                    rs.getDate("BirthDate").toLocalDate(),
                    rs.getString("Gender")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving student: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(
                    rs.getString("StudentID"),
                    rs.getString("FullName"),
                    rs.getString("ClassName"),
                    rs.getDate("BirthDate").toLocalDate(),
                    rs.getString("Gender")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all students: " + e.getMessage());
        }
        return list;
    }
}
