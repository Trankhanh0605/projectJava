package service;

import dao.TranscriptDAO;
import java.util.*;
import model.Student;
import model.Transcript;

public class XuLyGPA {
    /**
     * Tính và trả về GPA theo công thức
     */
    public static double tinhGPA(String studentId, TranscriptDAO dao) {
        List<Transcript> list = dao.getTranscriptsByStudentId(studentId);
        double tongDiemNhanTC = 0;
        double tongTC = 0;
        for (Transcript t : list) {
            tongDiemNhanTC += t.getScore() * t.getCredit();
            tongTC += t.getCredit();
        }
        return (tongTC > 0) ? tongDiemNhanTC / tongTC : 0;
    }

    /**
     * Liệt kê và in danh sách sinh viên bị cảnh cáo học vụ (GPA < threshold)
     */
    public static void inDanhSachCanhCao(List<Student> students, TranscriptDAO dao, double threshold) {
        System.out.println("\n========== SINH VIÊN CẢNH CÁO HỌC VỤ (GPA < " + threshold + ") ==========");
        System.out.printf("%-6s %-20s %-4s%n", "Mã", "Họ tên", "GPA");
        for (Student s : students) {
            double gpa = tinhGPA(s.getStudentID(), dao);
            if (gpa < threshold) {
                System.out.printf("%-6s %-20s %-4.2f%n", s.getStudentID(), s.getFullName(), gpa);
            }
        }
    }

    /**
     * Thống kê phổ điểm của một môn học theo điểm chữ
     */
    public static void thongKePhoDiemTheoMon(String subjectId, List<Student> students, TranscriptDAO dao) {
        Map<String, Integer> count = new HashMap<>();
        int total = 0;
        // Lấy mỗi sinh viên, kiểm tra transcript cá nhân
        for (Student s : students) {
            List<Transcript> personal = dao.getTranscriptsByStudentId(s.getStudentID());
            for (Transcript t : personal) {
                if (subjectId.equals(t.getSubjectID())) {
                    String grade = t.getGradeLetter();
                    count.put(grade, count.getOrDefault(grade, 0) + 1);
                    total++;
                }
            }
        }

        System.out.println("\n========== THỐNG KÊ PHỔ ĐIỂM MÔN " + subjectId + " ==========");
        if (total == 0) {
            System.out.println("Chưa có sinh viên nào học môn này.");
            return;
        }
        for (String grade : Arrays.asList("A", "B", "C", "D", "F")) {
            int num = count.getOrDefault(grade, 0);
            double pct = num * 100.0 / total;
            System.out.printf("Điểm %s: %d sinh viên (%.2f%%)%n", grade, num, pct);
        }
    }

    /**
     * In điểm và GPA cho sinh viên của một lớp hoặc một môn học cụ thể
     */
    public static void inDiemTheoLopHoacMon(List<Student> students, TranscriptDAO dao, String maLopOrMon, boolean theoLop) {
        System.out.println("\n========== DANH SÁCH SINH VIÊN THEO " + (theoLop ? "LỚP " : "MÔN HỌC ") + maLopOrMon + " ==========");
        for (Student s : students) {
            boolean match = theoLop ? s.getClassName().equalsIgnoreCase(maLopOrMon) : false;
            List<Transcript> list = dao.getTranscriptsByStudentId(s.getStudentID());
            if (!theoLop) {
                match = false;
                for (Transcript t : list) {
                    if (t.getSubjectID().equalsIgnoreCase(maLopOrMon)) {
                        match = true;
                        break;
                    }
                }
            }
            if (match) {
                System.out.printf("%s - %s\n", s.getStudentID(), s.getFullName());
                for (Transcript t : list) {
                    if (!theoLop || (theoLop && !t.getGradeLetter().isEmpty())) {
                        if (!theoLop && !t.getSubjectID().equalsIgnoreCase(maLopOrMon)) continue;
                        System.out.printf("  Môn: %s | Điểm: %.1f | Chữ: %s | TC: %.0f\n",
                                t.getSubjectID(), t.getScore(), t.getGradeLetter(), t.getCredit());
                    }
                }
                double gpa = tinhGPA(s.getStudentID(), dao);
                System.out.printf("  => GPA: %.2f\n", gpa);
            }
        }
    }
}
