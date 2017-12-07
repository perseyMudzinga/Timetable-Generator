package timetable;

public class Venue {
    private String roomNumber;
    private String venueLocation;
    private Exam curentExam;

    public Venue(String roomNumber, String venueLocation) {
        this.roomNumber = roomNumber;
        this.venueLocation = venueLocation;
    }

    public Exam getCurentExam() {
        return curentExam;
    }

    public void setCurentExam(Exam curentExam) {
        this.curentExam = curentExam;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getVenueLocation() {
        return venueLocation;
    }

    public void setVenueLocation(String venueLocation) {
        this.venueLocation = venueLocation;
    }
    
    
}
