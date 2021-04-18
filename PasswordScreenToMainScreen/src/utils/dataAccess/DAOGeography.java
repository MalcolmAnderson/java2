package utils.dataAccess;

import models.Contact;
import models.Country;
import models.Geography;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOGeography {

    private DBQueryManager dbQM = new DBQueryManager();

    public static ArrayList<Country> getCountries() {
        ArrayList<Country> countryList = new ArrayList<Country>();
        ResultSet rs = null;
        Country current = null;
        String sql = String.format(
                "SELECT * FROM countries" );
        rs = getResultSetFromQueryString(sql);
        try {
            while (rs.next()) {
                String countryName = rs.getString("Country");
                int country_id = rs.getInt("Country_ID");
                current = new Country(
                        country_id,
                        countryName
                );
                countryList.add(current);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            System.exit(-1);
        }
        return countryList;
    }

    private static ResultSet getResultSetFromQueryString(String sql) {
        ResultSet rs = null;
        try{
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
//            return rs;
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            System.exit(-1);
        }
        return rs;
    }

    public static ArrayList<Geography> loadKnownWorld() {
        ArrayList<Geography> divisions = new ArrayList<Geography>();
        ResultSet rs = null;
        Geography geography = null;
        String sql = String.format(
                "SELECT fld.Division_ID, fld.Division, c.Country, c.Country_ID FROM first_level_divisions as fld" +
                        " left join countries as c on fld.COUNTRY_ID = c.Country_ID" );
        try{
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            System.exit(-1);
        }
        try {
            while (rs.next()) {
                int division_id = rs.getInt("Division_ID");
                String division_Name = rs.getString("Division");
                String country = rs.getString("Country");
                int country_id = rs.getInt("Country_ID");
                geography = new Geography(
                        division_id,
                        division_Name,
                        country,
                        country_id
                );
                divisions.add(geography);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            System.exit(-1);
        }
        return divisions;

    }

}
