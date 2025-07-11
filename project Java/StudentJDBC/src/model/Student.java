package model;
//lớp đại diện cho Sinh Viên

import java.time.LocalDate;

public class Student {
    private String studentID;
    private String fullName;
    private String className;
    private LocalDate birthDate;
    private String gender;

    public Student(String studentID, String fullName, String className, LocalDate birthDate, String gender) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.className = className;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    // Getters
    public String getStudentID() { return studentID; }
    public String getFullName() { return fullName; }
    public String getClassName() { return className; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getGender() { return gender; }

    // Setters
    public void setStudentID(String studentID) { this.studentID = studentID; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setClassName(String className) { this.className = className; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public void setGender(String gender) { this.gender = gender; }

    @Override
    public String toString() {
        return studentID + " - " + fullName + " - " + className + " - " + birthDate + " - " + gender;
    }
}
