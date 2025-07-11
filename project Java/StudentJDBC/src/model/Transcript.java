package model; 

public class Transcript {
    private String subjectID;
    private String studentID;
    private double score;

private String gradeLetter; // A, B, C, D, F
private double credit;      // lấy từ Subject

 public Transcript() {
    }

    public Transcript(String subjectID, String studentID, double score) {
        this.subjectID = subjectID;
        this.studentID = studentID;
        this.score = score;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    public String getGradeLetter() {
    return gradeLetter;
}

public void setGradeLetter(String gradeLetter) {
    this.gradeLetter = gradeLetter;
}

public double getCredit() {
    return credit;
}

public void setCredit(double credit) {
    this.credit = credit;
}
} 