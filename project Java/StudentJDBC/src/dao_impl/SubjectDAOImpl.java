package dao_impl;

import dao.SubjectDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Subject;

public class SubjectDAOImpl implements SubjectDAO {
    private final Connection conn;

    public SubjectDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addSubject(Subject subject) {
        String sql = "INSERT INTO Subject (SubjectID, SubjectName, Credit) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, subject.getSubjectID());
            stmt.setNString(2, subject.getSubjectName());
            stmt.setInt(3, subject.getCredits());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding subject: " + e.getMessage());
        }
    }

    @Override
    public void updateSubject(Subject subject) {
        String sql = "UPDATE Subject SET SubjectName = ?, Credit = ? WHERE SubjectID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setNString(1, subject.getSubjectName());
            stmt.setInt(2, subject.getCredits());
            stmt.setString(3, subject.getSubjectID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating subject: " + e.getMessage());
        }
    }

    @Override
    public void deleteSubject(String subjectId) {
        String sql = "DELETE FROM Subject WHERE SubjectID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, subjectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting subject: " + e.getMessage());
        }
    }

    @Override
    public Subject getSubjectById(String subjectId) {
        String sql = "SELECT * FROM Subject WHERE SubjectID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, subjectId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Subject(
                    rs.getString("SubjectID"),
                    rs.getNString("SubjectName"),
                    rs.getInt("Credit")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving subject: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM Subject";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Subject(
                    rs.getString("SubjectID"),
                    rs.getNString("SubjectName"),
                    rs.getInt("Credit")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all subjects: " + e.getMessage());
        }
        return list;
    }
}
