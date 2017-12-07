package timetable;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class CreateTable {
    private final List<Venue> venues;
    private final List<Invigilator> invigilators;
    private final List<ExamTime> sessions;
    private final List<Program> programs;
    private final Random r = new Random();
    List<TimeUnit> timetable = new LinkedList<>();
    private final List<Date> mazuva;

    public CreateTable(List<Venue> venues, List<Invigilator> invigilators, List<ExamTime> sessions, List<Program> programs, List<Date> mazuva) {
        this.venues = venues;
        this.invigilators = invigilators;
        this.sessions = sessions;
        this.programs = programs;
        this.mazuva = mazuva;
    }
    
    public void addCourses(){
        
        
        for(Program p: programs){
            List<ExamTime> et = new LinkedList<>();
            et.addAll(sessions);
            Queue<Venue> vq = (Queue<Venue>) venues;
            Queue<Invigilator> iq = (Queue<Invigilator>) invigilators;
            
            for(Course cs: p.getCourses()){
                if(!et.isEmpty()){
                    int rn = r.nextInt(et.size());
                    ExamTime ext = et.get(rn);
                    et.remove(rn);

                    if(vq.isEmpty()){
                        vq = (Queue<Venue>) venues;
                    }
                    if(iq.isEmpty()){
                        iq.addAll((Queue<Invigilator>) invigilators);
                    }

                    Venue vn = vq.remove();
                    vq.add(vn);
                    Invigilator inv = iq.remove();
                    iq.add(inv);

                    TimeUnit tmtble = new TimeUnit(ext, cs, inv, vn, p.getProgramName()+" "+p.getLevel());
                    timetable.add(tmtble);
                }
                
            }
            et.clear();
        }
    }
    
    public Timetable getTimetable(){
        return new Timetable(timetable, mazuva);
    }
    
}
