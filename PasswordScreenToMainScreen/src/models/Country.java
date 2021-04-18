package models;

import java.util.ArrayList;

public class Country {

    private String countryName;
    private int country_ID;
    private static ArrayList<Country> countries;

    public Country(int country_ID, String countryName ) {
        this.countryName = countryName;
        this.country_ID = country_ID;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getCountryId() {
        return country_ID;
    }
}
