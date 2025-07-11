import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TranscriptDAO;
import dao_impl.StudentDAOImpl;
import dao_impl.SubjectDAOImpl;
import dao_impl.TranscriptDAOImpl;
import java.sql.*;
import java.util.*;
import model.Student;
import model.Subject;
import model.Transcript;
import service.XuLyBangDiem;
import service.XuLyGPA;
import util.DBConnection;

public class App {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("✅ Kết nối thành công!");

            StudentDAO studentDAO = new StudentDAOImpl(conn);
            SubjectDAO subjectDAO = new SubjectDAOImpl(conn);
            TranscriptDAO transcriptDAO = new TranscriptDAOImpl(conn);

            List<Student> students = studentDAO.getAllStudents();
            List<Subject> subjects = subjectDAO.getAllSubjects();
            List<Transcript> transcripts = transcriptDAO.getAllTranscripts();

            // Hiển thị danh sách sinh viên
            System.out.println("\n📚 Danh sách sinh viên:");
            for (Student s : students) {
                System.out.println("Mã: " + s.getStudentID() +
                        ", Họ tên: " + s.getFullName() +
                        ", Lớp: " + s.getClassName() +
                        ", Ngày sinh: " + s.getBirthDate() +
                        ", Giới tính: " + s.getGender());
            }

            // Hiển thị danh sách môn học
            System.out.println("\n📘 Danh sách môn học:");
            for (Subject sub : subjects) {
                System.out.println("Mã: " + sub.getSubjectID() +
                        ", Tên: " + sub.getSubjectName() +
                        ", Số tín chỉ: " + sub.getCredits());
            }

            // Bảng điểm toàn bộ
            System.out.println("\n========== BẢNG ĐIỂM TOÀN BỘ ==========\n");
            System.out.printf("%-6s %-6s %-5s\n", "SVID", "MHID", "Điểm");
            for (Transcript t : transcripts) {
                System.out.printf("%-6s %-6s %-5.1f\n",
                        t.getStudentID(), t.getSubjectID(), t.getScore());
            }

            // GPA và điểm chữ
            System.out.println("\n========== GPA VÀ ĐIỂM CHỮ CỦA TỪNG SINH VIÊN ==========\n");
            for (Student s : students) {
                String id = s.getStudentID();
                List<Transcript> personal = transcriptDAO.getTranscriptsByStudentId(id);
                if (personal.isEmpty()) {
                    System.out.printf("%s - %s: (Chưa có bảng điểm)\n", id, s.getFullName());
                    continue;
                }
                System.out.printf("\nSinh viên: %s - %s\n", id, s.getFullName());
                System.out.printf("%-6s %-6s %-6s %-3s\n", "Môn", "Điểm", "Chữ", "TC");
                double totalWeighted = 0;
                double totalCredits = 0;
                for (Transcript t : personal) {
                    System.out.printf("%-6s %-6.1f %-6s %-3.0f\n",
                            t.getSubjectID(), t.getScore(), t.getGradeLetter(), t.getCredit());
                    totalWeighted += t.getScore() * t.getCredit();
                    totalCredits += t.getCredit();
                }
                double gpa = totalCredits > 0 ? (totalWeighted / totalCredits) : 0;
                System.out.printf("=> GPA: %.2f\n", gpa);
            }

            // Sắp xếp theo GPA giảm dần
            XuLyBangDiem.sapXepVaInTheoGPA(students, transcriptDAO);

            // In Top 5 sinh viên có GPA cao nhất
            XuLyBangDiem.inTop5GPA(students, transcriptDAO);

            // Liệt kê sinh viên bị cảnh cáo học vụ (GPA < 1.0)
            XuLyGPA.inDanhSachCanhCao(students, transcriptDAO, 5.0);

            // Dùng Scanner trong try-with-resources
            try (Scanner scanner = new Scanner(System.in)) {
                // Thống kê phổ điểm môn học
                System.out.print("\nNhập mã môn học để thống kê điểm chữ: ");
                String subjectId = scanner.nextLine();
                XuLyGPA.thongKePhoDiemTheoMon(subjectId, students, transcriptDAO);

                // Hiển thị điểm và GPA của sinh viên theo lớp
                System.out.print("\nNhập tên lớp để xem bảng điểm: ");
                String tenLop = scanner.nextLine();
                XuLyGPA.inDiemTheoLopHoacMon(students, transcriptDAO, tenLop, true);

                // Hiển thị điểm và GPA của sinh viên học một môn cụ thể
                System.out.print("\nNhập mã môn học để xem bảng điểm: ");
                String maMon = scanner.nextLine();
                XuLyGPA.inDiemTheoLopHoacMon(students, transcriptDAO, maMon, false);
            }

        } catch (SQLException e) {
            System.err.println("❌ Lỗi SQL: " + e.getMessage());
        }
    }
}
