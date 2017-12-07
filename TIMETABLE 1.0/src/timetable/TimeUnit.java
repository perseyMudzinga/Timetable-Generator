package timetable;

public class TimeUnit {
    private final ExamTime session;
    private final Course course;
    private Invigilator invigilator;
    private final Venue venue;
    private final String program;

    public TimeUnit(ExamTime session, Course course, Invigilator invigilator, Venue venue, String program) {
        this.session = session;
        this.course = course;
        this.invigilator = invigilator;
        this.venue = venue;
        this.program = program;
    }

    public ExamTime getSession() {
        return session;
    }

    public Course getCourse() {
        return course;
    }

    public Invigilator getInvigilator() {
        return invigilator;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getProgram() {
        return program;
    }

    public void setInvigilator(Invigilator invigilator) {
        this.invigilator = invigilator;
    }
    
}
