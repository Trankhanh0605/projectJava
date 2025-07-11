package service;

import dao.TranscriptDAO;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.Student;
import model.Transcript;

public class XuLyBangDiem {
    /**
     * In bảng điểm chi tiết của một sinh viên
     */
    public static void inBangDiemChiTiet(String studentId, TranscriptDAO dao) {
        List<Transcript> list = dao.getTranscriptsByStudentId(studentId);
        System.out.println("\n📋 Bảng điểm sinh viên " + studentId + ":");
        System.out.printf("%-6s %-6s %-6s %-3s\n", "Môn", "Điểm", "Chữ", "TC");
        for (Transcript t : list) {
            System.out.printf("%-6s %-6.1f %-6s %-3.0f\n",
                t.getSubjectID(), t.getScore(), t.getGradeLetter(), t.getCredit());
        }
    }

    /**
     * Sắp xếp và in danh sách sinh viên theo GPA giảm dần
     */
    public static void sapXepVaInTheoGPA(List<Student> students, TranscriptDAO dao) {
        // Tính GPA cho từng sinh viên
        List<StudentGPA> list = new ArrayList<>();
        for (Student s : students) {
            double gpa = XuLyGPA.tinhGPA(s.getStudentID(), dao);
            list.add(new StudentGPA(s, gpa));
        }
        // Sắp xếp giảm dần
        list.sort(Comparator.comparingDouble(StudentGPA::getGpa).reversed());

        // In kết quả
        System.out.println("\n========== DANH SÁCH SINH VIÊN THEO GPA GIẢM DẦN ==========");
        System.out.printf("%-6s %-20s %-4s\n", "Mã", "Họ tên", "GPA");
        for (StudentGPA sg : list) {
            System.out.printf("%-6s %-20s %-4.2f\n",
                sg.getStudent().getStudentID(), sg.getStudent().getFullName(), sg.getGpa());
        }
    }

    /**
     * In Top 5 sinh viên có GPA cao nhất
     */
    public static void inTop5GPA(List<Student> students, TranscriptDAO dao) {
        List<StudentGPA> list = new ArrayList<>();
        for (Student s : students) {
            double gpa = XuLyGPA.tinhGPA(s.getStudentID(), dao);
            list.add(new StudentGPA(s, gpa));
        }
        list.sort(Comparator.comparingDouble(StudentGPA::getGpa).reversed());

        System.out.println("\n========== TOP 5 SINH VIÊN CÓ GPA CAO NHẤT ==========");
        System.out.printf("%-6s %-20s %-4s\n", "Mã", "Họ tên", "GPA");
        int top = Math.min(5, list.size());
        for (int i = 0; i < top; i++) {
            StudentGPA sg = list.get(i);
            System.out.printf("%-6s %-20s %-4.2f\n",
                sg.getStudent().getStudentID(), sg.getStudent().getFullName(), sg.getGpa());
        }
    }

    // Lớp trợ giúp lưu Student và GPA
    private static class StudentGPA {
        private final Student student;
        private final double gpa;

        public StudentGPA(Student student, double gpa) {
            this.student = student;
            this.gpa = gpa;
        }
        public Student getStudent() {
            return student;
        }
        public double getGpa() {
            return gpa;
        }
    }
}
