package timetable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public final class Data {
    private final List<Program> programs = new LinkedList<>();
    private final DatabaseConnection dbc;
    private Connection conn;
    List<Date> dates = new LinkedList<>();
    List<Date> mazuva = new LinkedList<>();
    List<ExamTime> examT = new LinkedList<>();
    private int numOfDays = 0;
    private Date initDate = null;
    
    public Data(String hostAddress, int portNumber, String userName, String pass, String databaseName){
        dbc = new DatabaseConnection(hostAddress, portNumber, userName, pass, databaseName);
        
    }
    
    public void connectToDatabase() throws SQLException{
        conn = dbc.connect();
    }
    public void closeDb() throws SQLException{
        this.conn.close();
    }
    
    public List<Program> getPrograms(){
        List<Program> progs = new LinkedList<>();
        
        try {
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from students");
            while(rs.next()){
                Program program;
                List<Course> css = new LinkedList<>();
                ResultSet r = getCourses(rs.getString("id"));
                
                while(r.next()){
                    css.add(new Course(r.getString("courseCode"), r.getString("courseName")));
                }
                if(css.size() > 0){
                    program = new Program(rs.getString("program"), Double.parseDouble(rs.getString("level")), css);
                    progs.add(program);
                }
                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return progs;
    }
    
    private ResultSet getCourses(String id) throws SQLException{
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("select * from exams INNER JOIN courses ON courses.courseCode=exams.courseCode where exams.groupId='"+id+"'");
    }
    
    public List<Venue> getVenues(){
        List<Venue> venues = new LinkedList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from venues");
            while(rs.next()){
                venues.add(new Venue(rs.getString("roomNumber"), rs.getString("location")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return venues;
    }
    
    public List<Invigilator> getInvigilators(){
        List<Invigilator> invigilators = new LinkedList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from invigilator");
            while(rs.next()){
                invigilators.add(new Invigilator(rs.getString("name")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return invigilators;
    }
    
    public Date genNextDate(Date date){
        Date d = date;
        SimpleDateFormat dt = new SimpleDateFormat("E");
        Date dat = null;
        
        while(true){
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            SimpleDateFormat year = new SimpleDateFormat("Y");
            SimpleDateFormat month = new SimpleDateFormat("M");
            SimpleDateFormat day = new SimpleDateFormat("d");

            int datet = Integer.parseInt(day.format(d))+1;
            String strDate = year.format(d)+"-"+month.format(d)+"-"+datet;
            
            try {
                dat = ft.parse(strDate);
            } catch (ParseException ex) {
                System.out.println(ex);
            }
            
            if(dt.format(dat).toLowerCase().substring(0, 3).equals("sun") || dt.format(dat).toLowerCase().substring(0, 3).equals("sat")){
                d = dat;
            }else{
                break;
            }
            
        }
        return dat;
    }
    
    public List<Date> getDates(Date date){
        List<Date> datess = new LinkedList<>();
        Date da = date;
        datess.add(da);
        for(int i = 0; i < this.numOfDays; i++){
            Date dtt = genNextDate(da);
            datess.add(dtt);
            da = dtt;
        }
        return datess;
    }
    
    public void attachSessions(){
        String[] sessions = {"Morning", "Afternoon"};
        examT.clear();
        mazuva.clear();
        for(Date d: getDates(this.initDate)){
            mazuva.add(d);
            for(String s: sessions){
                ExamTime et = new ExamTime(d, s);
                examT.add(et);
            }
        }
    }
    
    public List<Date> getRawDates(){
        return mazuva;
    }
    
    public List<ExamTime> getSessions(){
        return examT;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public void setDateString(String dateString) {
        int day = Integer.parseInt(dateString.substring(0, 2));
        int month = Integer.parseInt(dateString.substring(3, 5))-1;
        int year = Integer.parseInt(dateString.substring(6, dateString.length()));
        System.out.println(new Date(year, month, day));
        this.initDate = new Date(year, month, day);
    }
}
