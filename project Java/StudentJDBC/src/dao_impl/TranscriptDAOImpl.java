package dao_impl;

import dao.TranscriptDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Transcript;


public class TranscriptDAOImpl implements TranscriptDAO {
    private final Connection conn;

    public TranscriptDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Transcript> getAllTranscripts() {
        List<Transcript> list = new ArrayList<>();
        String sql = "SELECT * FROM Transcript";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Transcript(
                    rs.getString("SubjectID"),
                    rs.getString("StudentID"),
                    rs.getDouble("Score")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all transcripts: " + e.getMessage());
            e.printStackTrace(System.err);
        }
        return list;
    }

    @Override
    public Transcript getTranscript(String studentID, String subjectID) {
        String sql = "SELECT * FROM Transcript WHERE StudentID = ? AND SubjectID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentID);
            stmt.setString(2, subjectID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Transcript(
                    rs.getString("SubjectID"),
                    rs.getString("StudentID"),
                    rs.getDouble("Score")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching transcript: " + e.getMessage());
            e.printStackTrace(System.err);
        }
        return null;
    }

    @Override
    public boolean addTranscript(Transcript transcript) {
        String sql = "INSERT INTO Transcript (SubjectID, StudentID, Score) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transcript.getSubjectID());
            stmt.setString(2, transcript.getStudentID());
            stmt.setDouble(3, transcript.getScore());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding transcript: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        }
    }

    @Override
    public boolean updateTranscript(Transcript transcript) {
        String sql = "UPDATE Transcript SET Score = ? WHERE StudentID = ? AND SubjectID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, transcript.getScore());
            stmt.setString(2, transcript.getStudentID());
            stmt.setString(3, transcript.getSubjectID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating transcript: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        }
    }

    @Override
    public boolean deleteTranscript(String studentID, String subjectID) {
        String sql = "DELETE FROM Transcript WHERE StudentID = ? AND SubjectID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentID);
            stmt.setString(2, subjectID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting transcript: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        }
    }
    @Override
public List<Transcript> getTranscriptsByStudentId(String studentId) {
    List<Transcript> list = new ArrayList<>();
    String sql = "SELECT t.StudentID, t.SubjectID, t.Score, s.Credit " +
                 "FROM Transcript t JOIN Subject s ON t.SubjectID = s.SubjectID " +
                 "WHERE t.StudentID = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, studentId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Transcript t = new Transcript();
            t.setStudentID(rs.getString("StudentID"));
            t.setSubjectID(rs.getString("SubjectID"));
            double score = rs.getDouble("Score");
            t.setScore(score);
            double credit = rs.getDouble("Credit");
            t.setCredit(credit);

            // chuyển điểm sang A/B/C...
            String grade = convertToLetterGrade(score);
            t.setGradeLetter(grade);

            list.add(t);
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi lấy bảng điểm: " + e.getMessage());
    }

    return list;
}

private String convertToLetterGrade(double score) {
    if (score >= 8.5) return "A";
    else if (score >= 7.0) return "B";
    else if (score >= 5.5) return "C";
    else if (score >= 4.0) return "D";
    else return "F";
}


}
