package models;

import java.util.ArrayList;

/** Used to hold all countries in the system. */
public class Country {

    private String countryName;
    private int country_ID;
    private static ArrayList<Country> countries;

    /** Constructor for Country.
     * @param country_ID - Country Id for country object
     * @param countryName - Country Name for country object */
    public Country(int country_ID, String countryName ) {
        this.countryName = countryName;
        this.country_ID = country_ID;
    }

    /** Getter for Country Name.
     * @return String - returns country Name */
    public String getCountryName() {
        return countryName;
    }

    /** Getter for Country ID.
     * @return int - returns the id of the current Country object */
    public int getCountryId() {
        return country_ID;
    }
}
