package timetable;

import java.util.Date;

public class ExamTime {
    private final Date examDate;
    private final String session;

    public ExamTime(Date examDate, String session) {
        this.examDate = examDate;
        this.session = session;
    }

    public Date getExamDate() {
        return examDate;
    }

    public String getSession() {
        return session;
    }
}
