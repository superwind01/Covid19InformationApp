package model;

public class Place{
    private int id;
    private String date;
    private String time;
    private String locations;

    public Place(int id, String date, String time, String locations) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.locations = locations;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
