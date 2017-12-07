package timetable;

import java.util.Date;

public class Exam {
    private Course course;
    private Date date;
    private String session;

    public Exam(Course course, Date date, String session) {
        this.course = course;
        this.date = date;
        this.session = session;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
