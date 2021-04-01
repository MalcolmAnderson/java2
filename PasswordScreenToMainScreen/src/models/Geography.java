package models;

import java.util.ArrayList;

public class Geography {

    private int division_ID;
    private String countryName;
    private String divisionName;
    private int country_ID;
    private static ArrayList<Geography> knownWorld;
    private static ArrayList<Country> countries;

    public Geography(int division_ID, String divisionName, String countryName, int country_ID) {
        this.division_ID = division_ID;
        this.countryName = countryName;
        this.divisionName = divisionName;
        this.country_ID = country_ID;
    }

    // client code is expected to run:
    //         Geography.setKnownWorld(DAOGeography.loadKnownWorld());
    // before using rest of knownWorld functionality
    public boolean isKnownWorldLoaded(){
        return knownWorld != null;
    }


    public static ArrayList<Geography> getDivisionsForCountryID(int country_ID) {
        ArrayList<Geography> divisions = new ArrayList<Geography>();

        knownWorld.forEach((current) -> {
            if(current.getCountryId() == country_ID){
                divisions.add(current);
            }
        });
        return divisions;
    }


    public static ArrayList<Geography> getKnownWorld() {
        return knownWorld;
    }
    public static void setKnownWorld(ArrayList<Geography> incomingKnownWorld) {
        knownWorld = incomingKnownWorld;
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

    public int getCountryId() {
        return country_ID;
    }
}
