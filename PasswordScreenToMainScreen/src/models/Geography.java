package models;

import java.util.ArrayList;

/** Geography class is based on a division Id, and contains both division and country information. */
public class Geography {

    private int division_ID;
    private String countryName;
    private String divisionName;
    private int country_ID;
    private static ArrayList<Geography> knownWorld;
    private static ArrayList<Country> countries;

    /** Blank constructor */
    public  Geography(){
        this.division_ID = 1;
        this.countryName = "";
        this.divisionName = "";
        this.country_ID = 1;
    }
    /** Normal Constructor */
    public Geography(int division_ID, String divisionName, String countryName, int country_ID) {
        this.division_ID = division_ID;
        this.countryName = countryName;
        this.divisionName = divisionName;
        this.country_ID = country_ID;
    }

    /**
     * client code is expected to run:
     *         Geography.setKnownWorld(DAOGeography.loadKnownWorld());
     * before using rest of knownWorld functionality
     * returns true if known world has more than 5 objects in it (GB)
     *
     */
    public static boolean isKnownWorldLoaded(){
        if(knownWorld == null){
            return false;
        }
        return knownWorld.size() > 5;
    }

    /** Returns a list of Geography objects for the country selected */
    public static ArrayList<Geography> getDivisionsForCountryID(int country_ID) {
        ArrayList<Geography> divisions = new ArrayList<Geography>();

        knownWorld.forEach((current) -> {
            if(current.getCountryId() == country_ID){
                divisions.add(current);
            }
        });
        return divisions;
    }

    /** Returns the knownworld object */
    public static ArrayList<Geography> getKnownWorld() {
        return knownWorld;
    }
    /** Sets the known world object.  Expected to be set by a DAO. */
    public static void setKnownWorld(ArrayList<Geography> incomingKnownWorld) {
        knownWorld = incomingKnownWorld;
    }

    /** Returns a division object based on division Id. Failing to find a division id is considered a data error. */
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

    /** crashes the system if known world has not yet been loaded. */
    private static void ExitIfKnownWorldNotLoaded() {
        if(!isKnownWorldLoaded()){
            System.out.println("KnownWorld must be loaded before this operation can be performed");
            System.exit(-1);
        }
    }

    /** returns all divisions from known world based on the country Id. */
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

    /** returns the division Id of a single geo object. */
    public int getDivisionId() {
        return division_ID;
    }

    /** returns the country name from a single geo object. */
    public String getCountryName() {
//        System.out.println("getCountryName == null is:  " + countryName == null);
//        System.out.println("getCountryName countryName == " + countryName);
        return countryName;
    }

    /** returns the division name from a single geo object. */
    public String getDivisionName() {
        return divisionName;
    }

    /** returns the country id from a single geo object. */
    public int getCountryId() {
        return country_ID;
    }
}
