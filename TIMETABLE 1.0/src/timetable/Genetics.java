package timetable;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Genetics {
    private final List<Timetable> population = new LinkedList<>();
    private final List<Timetable> newPop = new LinkedList<>();
    private final List<Double> percs = new LinkedList<>();
    public Genetics(List<Timetable> population) {
        this.population.clear();
        this.newPop.clear();
        this.percs.clear();
        for(Timetable tb: population){
            checkFittiness(tb);
        }
        getAverage();
        mutation();
    }
    
    public final void mutation(){
        for(int i = 0; i < 3; i++){
            Timetable tm = newPop.get(new Random().nextInt(newPop.size()));
            for(int j = 0; j < 5; j++){
                TimeUnit t = tm.getTimetable().get(new Random().nextInt(tm.getTimetable().size()));
                TimeUnit t2 = tm.getTimetable().get(new Random().nextInt(tm.getTimetable().size()));
                Invigilator in = t.getInvigilator();
                Invigilator in2 = t2.getInvigilator();
                t2.setInvigilator(in);
                t.setInvigilator(in2);
            }
        }
    }
    
    public final void checkFittiness(Timetable tb){
        goupingByDate(tb);
    }
    
    private void getAverage(){
        double sum = 0;
        double average;
        for(double d: percs){
            sum+=d;
        }
        average = sum / percs.size();
        for(Timetable tb: population){
            if(tb.getFittiness() >= average){
                newPop.add(tb);
            }
        }
    }
    
    public void crossOver(){
        
    }
    
    public void goupingByDate(Timetable tb){
        List<Date> dates = tb.getDates();
        int total = dates.size();
        double tp = 0;
        double tablePercentage;
        
        Collections.sort(dates);
        
        for(Date d: dates){
            double dayPerc;
            List<TimeUnit> tUs = new LinkedList<>();
            for(TimeUnit tu: tb.getTimetable()){
                if(tu.getSession().getExamDate().toString().equals(d.toString())){
                    tUs.add(tu);
                }
            }
            dayPerc = (checkCourse(tUs)+checkVenue(tUs)+checkInvigilator(tUs))/3;
            tp += dayPerc;
        }
        tablePercentage = tp / total;
        tb.setFittiness(tablePercentage);
        population.add(tb);
        percs.add(tablePercentage);
    }
    
    private double checkCourse(List<TimeUnit> tUs){
        try{
            int total = tUs.size();
            Set<String> allCourses = new LinkedHashSet<>();
            for(TimeUnit tus: tUs){
                allCourses.add(tus.getProgram()+tus.getSession().getSession());
            }
            return (allCourses.size() / total);
        }catch(ArithmeticException a){
            return 1;
        }
    }
    
    private double checkVenue(List<TimeUnit> tUs){
        try{
            int total = tUs.size();
            Set<String> allVenues = new LinkedHashSet<>();
            for(TimeUnit tus: tUs){
                allVenues.add(tus.getSession().getSession()+tus.getVenue().getRoomNumber());
            }
            return (allVenues.size()/total);
        }catch(ArithmeticException a){
            return 1;
        }
    }
    
    private double checkInvigilator(List<TimeUnit> tUs){
        try{
            int total = tUs.size();
            Set<String> allIltr = new LinkedHashSet<>();
            for(TimeUnit tus: tUs){
                allIltr.add(tus.getSession().getSession()+tus.getInvigilator().getName());
            }
            return (allIltr.size()/total);
        }catch(ArithmeticException a){
            return 1;
        }
    }

    public List<Timetable> getNewPop() {
        return newPop;
    }
}
