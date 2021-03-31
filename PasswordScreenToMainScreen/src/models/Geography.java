package models;

public class Geography {

    private int division_ID;
    private String countryName;
    private String divisionName;

    public Geography(int division_ID, String divisionName, String countryName) {
        this.division_ID = division_ID;
        this.countryName = countryName;
        this.divisionName = divisionName;
    }

    public int getDivisionId() {
        return division_ID;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getDivisionName() {
        return divisionName;
    }
}
