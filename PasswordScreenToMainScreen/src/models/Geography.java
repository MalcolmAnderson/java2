package models;

import java.util.ArrayList;

public class Geography {

    private int division_ID;
    private String countryName;
    private String divisionName;
    private int country_ID;
    private static ArrayList<Geography> knownWorld;
    private static ArrayList<Country> countries;

    public  Geography(){
        this.division_ID = 1;
        this.countryName = "";
        this.divisionName = "";
        this.country_ID = 1;
    }
    public Geography(int division_ID, String divisionName, String countryName, int country_ID) {
        this.division_ID = division_ID;
        this.countryName = countryName;
        this.divisionName = divisionName;
        this.country_ID = country_ID;
    }

    // client code is expected to run:
    //         Geography.setKnownWorld(DAOGeography.loadKnownWorld());
    // before using rest of knownWorld functionality
    public static boolean isKnownWorldLoaded(){
        if(knownWorld == null){
            return false;
        }
        return knownWorld.size() > 5;
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

    public static Geography getGeographyByDivisionId(int divisionId) {
        Geography retVal = null;
        ExitIfKnownWorldNotLoaded();
        for(int i = 0; i < knownWorld.size(); i++){
            if(divisionId == knownWorld.get(i).division_ID){
                retVal = knownWorld.get(i);
                break;
            }
        }
        if(retVal == null){
            System.out.println("Geography.getGeographyByDivisionId - Division_ID : " + divisionId + " was not found in knownWorld");
            System.exit(-1);
        }
        return retVal;
    }

    private static void ExitIfKnownWorldNotLoaded() {
        if(!isKnownWorldLoaded()){
            System.out.println("KnownWorld must be loaded before this operation can be performed");
            System.exit(-1);
        }
    }

    public static ArrayList<Geography> getDivisionsByCountry_ID(int country_ID) {
        ExitIfKnownWorldNotLoaded();
        ArrayList<Geography> divisions = new ArrayList<Geography>();
        for(int i = 0; i < knownWorld.size(); i++){
            if(knownWorld.get(i).getCountryId() == country_ID){
                divisions.add(knownWorld.get(i));
            }
        }
        if(divisions.size() < 1){
            System.out.println("Geography.getDivisionsByCountry_ID - Country_ID " + country_ID + " not found, exiting program.");
            System.exit(-1);
        }
        return divisions;
    }


    public int getDivisionId() {
        return division_ID;
    }

    public String getCountryName() {
        System.out.println("getCountryName == null is:  " + countryName == null);
        System.out.println("getCountryName countryName == " + countryName);
        return countryName;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public int getCountryId() {
        return country_ID;
    }
}
