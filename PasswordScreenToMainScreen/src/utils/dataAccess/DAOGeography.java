package utils.dataAccess;

import models.Contact;
import models.Geography;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOGeography {
    public Geography getGeographyByDivisionId(int divisionId) {
        ResultSet rs = null;
        Geography geography = null;
        String sql = String.format(
                "SELECT fld.Division_ID, fld.Division, c.Country FROM first_level_divisions as fld" +
                        " left join countries as c on fld.COUNTRY_ID = c.Country_ID" +
                        " where fld.Division_ID = '%s'", divisionId);
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
                geography = new Geography(
                        division_id,
                        division_Name,
                        country
                );
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            System.exit(-1);
        }
        return geography;
    }
}
