package utils;

import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedQueryExample {

    Connection conn = DBConnection.getConnection();

    String insertStatement = "INSERT INTO countries "
            + "(Country, Create_Date, Created_By, Last_Updated_By) "
            + "VALUES (?, ?, ?, ?)";

    String updateStatement = "UPDATE countries SET Country = ?, Created_By = ? WHERE Country = ?";

    String deleteStatement = "DELETE FROM countries WHERE Country = ?";

    private String countryName = "Republic of Spade";
    private String createDate = "2016-02-24 04:20:00";
    private String createdBy = "admin";
    private String lastUpdatedBy = "admin";
    private String where_CountryName = "Latvia";


    public void DeleteAllUSSR() {
        System.out.println("Deleting all USSR rows");

        try {
            DBQuery.setPreparedStatement(conn, deleteStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1, "USSR");
            ps.execute();
            if(ps.getUpdateCount() > 0) { // rows were updated
                System.out.println(ps.getUpdateCount() + " row(s) impacted");
            } else{
                System.out.println("no rows changed.");
            }
        } catch (SQLException throwables) {
            // for now, swallow exception - never do this in production (99.95% of the time)
            throwables.printStackTrace();
        }
    }

    public void UpdateUSSRToJapan() {
        System.out.println("Adding USSR row");

        try {
            DBQuery.setPreparedStatement(conn, updateStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1, "Japan");
            ps.setString(2, createdBy);
            ps.setString(3, "USSR");
            ps.execute();
            if(ps.getUpdateCount() > 0) { // rows were updated
                System.out.println(ps.getUpdateCount() + " row(s) impacted");
            } else{
                System.out.println("no rows changed.");
            }
        } catch (SQLException throwables) {
            // for now, swallow exception - never do this in production (99.95% of the time)
            throwables.printStackTrace();
        }
    }

    public void AddUSSR() {
        System.out.println("Adding USSR row");

        try {
            DBQuery.setPreparedStatement(conn, insertStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1, "USSR");
            ps.setString(2, createDate);
            ps.setString(3, createdBy);
            ps.setString(4, lastUpdatedBy);
            ps.execute();
            if(ps.getUpdateCount() > 0) { // rows were updated
                System.out.println(ps.getUpdateCount() + " row(s) impacted");
            } else{
                System.out.println("no rows changed.");
            }
        } catch (SQLException throwables) {
            // for now, swallow exception - never do this in production (99.95% of the time)
            throwables.printStackTrace();
        }

    }


}
