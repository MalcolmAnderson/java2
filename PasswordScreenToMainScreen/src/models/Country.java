package models;

import java.util.ArrayList;

/** Used to hold all countries in the system. */
public class Country {

    private String countryName;
    private int country_ID;
    private static ArrayList<Country> countries;

    /** Constructor for Country. */
    public Country(int country_ID, String countryName ) {
        this.countryName = countryName;
        this.country_ID = country_ID;
    }

    /** Getter for Country Name. */
    public String getCountryName() {
        return countryName;
    }

    /** Getter for Country ID. */
    public int getCountryId() {
        return country_ID;
    }
}
