package model;

import java.util.ArrayList;

public class ModelCommon {
    private ArrayList<Location> locations = new ArrayList<>();
    private ArrayList<OverViewInfo> overview = new ArrayList<>();
    private Today today;
    private Total total;

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public ArrayList<OverViewInfo> getOverview() {
        return overview;
    }

    public void setOverview(ArrayList<OverViewInfo> overview) {
        this.overview = overview;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }
}
