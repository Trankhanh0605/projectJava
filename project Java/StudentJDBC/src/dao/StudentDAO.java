package dao;

import java.util.List;
import model.Student;

public interface StudentDAO {
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(String studentId);
    Student getStudentById(String studentId);
    List<Student> getAllStudents();
}

//khai bao interface để định nghĩa phương thức cho các thao tac 
