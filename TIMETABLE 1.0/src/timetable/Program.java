package timetable;

import java.util.List;

public class Program {
    private String programName;
    private double level;
    private final List<Course> courses;

    public Program(String programName, double level, List<Course> courses) {
        this.programName = programName;
        this.level = level;
        this.courses = courses;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public List<Course> getCourses() {
        return courses;
    }
    
}
