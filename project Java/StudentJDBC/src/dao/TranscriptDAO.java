package dao;

import java.util.List;
import model.Transcript;

public interface TranscriptDAO {
    List<Transcript> getAllTranscripts();
    Transcript getTranscript(String studentID, String subjectID);
    boolean addTranscript(Transcript transcript);
    boolean updateTranscript(Transcript transcript);
    boolean deleteTranscript(String studentID, String subjectID);
    List<Transcript> getTranscriptsByStudentId(String studentId);
}


