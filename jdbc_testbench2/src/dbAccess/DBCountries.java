package dbAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBCountries {
    public static ObservableList<Country> getAllCountries(){
        ObservableList<Country> clist = FXCollections.observableArrayList();

        try{
          String sql = "SELECT * FROM countries";

          PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

          ResultSet rs = ps.executeQuery();

          while(rs.next()){
              int countryId = rs.getInt("Country_ID");
              String countryName = rs.getString("Country");
              Country C = new Country(countryId, countryName);
              clist.add(C);
          }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return clist;
    }

    public static void checkDateConversion(){
        System.out.println("Create Date Test");
        String sql = "select Create_Date from countries";
        try{
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Timestamp ts = rs.getTimestamp("Create_Date");
                System.out.println("CD: " + ts.toLocalDateTime().toString());
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
