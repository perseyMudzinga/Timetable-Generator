package timetable;

import java.util.Date;
import java.util.List;

public class Timetable {
    private final List<TimeUnit> timetable;
    private final List<Date> dates;
    private double fittiness;

    public Timetable(List<TimeUnit> timetable, List<Date> dates) {
        this.timetable = timetable;
        this.dates = dates;
    }

    public List<TimeUnit> getTimetable() {
        return timetable;
    }

    public List<Date> getDates() {
        return dates;
    }

    public double getFittiness() {
        return fittiness;
    }

    public void setFittiness(double fittiness) {
        this.fittiness = fittiness;
    }
}
