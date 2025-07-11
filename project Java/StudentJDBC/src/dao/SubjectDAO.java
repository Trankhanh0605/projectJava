package dao;

import java.util.List;
import model.Subject;

public interface SubjectDAO {
    /**
     * Thêm một môn học mới vào cơ sở dữ liệu
     * @param subject đối tượng Subject cần thêm
     */
    void addSubject(Subject subject);

    /**
     * Cập nhật thông tin môn học
     * @param subject đối tượng Subject đã được chỉnh sửa
     */
    void updateSubject(Subject subject);

    /**
     * Xóa môn học dựa trên mã môn học
     * @param subjectId mã định danh của môn học cần xóa
     */
    void deleteSubject(String subjectId);

    /**
     * Lấy thông tin chi tiết một môn học theo mã
     * @param subjectId mã định danh của môn học
     * @return đối tượng Subject tương ứng hoặc null nếu không tìm thấy
     */
    Subject getSubjectById(String subjectId);

    /**
     * Lấy danh sách tất cả các môn học
     * @return List các đối tượng Subject
     */
    List<Subject> getAllSubjects();
}
