package model;

public class Subject {
    private String subjectID;
    private String subjectName;
    private int credits;

    public Subject(String subjectID, String subjectName, int credits) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.credits = credits;
    }

    // Getters
    public String getSubjectID() { return subjectID; }
    public String getSubjectName() { return subjectName; }
    public int getCredits() { return credits; }

    // Setters
    public void setSubjectID(String subjectID) { this.subjectID = subjectID; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
    public void setCredits(int credits) { this.credits = credits; }

    @Override
    public String toString() {
        return subjectID + " - " + subjectName + " - " + credits;
    }
}
