package dbAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class DBCountries {

    public  static int getCountryColumnCount(){
        int columnCount = 0;
        try {
            String sql = "SELECT * FROM countries LIMIT 1";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
            System.out.println("Column Count = " + columnCount);
            for(int i = 1 ; i <= columnCount ; i++){
                //getting column name of index 'i'
                String colName = rsmd.getColumnName(i);
                //getting column's data type of index 'i'
                String colType = rsmd.getColumnTypeName(i);
                System.out.println("Column # " + i + " : \"" + colName+"\" is of type "+colType);
            }
        } catch (SQLException e){
            System.out.println("We gone boom");
            e.printStackTrace();
        }
        return columnCount;
    }

    public  static int getCountryCount(){
        int recordCount = 0;
        try {
            String sql = "SELECT count(*) FROM countries";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                recordCount = rs.getInt("count(*)");
            }
            System.out.println("Record Count = " + recordCount);
            return recordCount;
        } catch (SQLException e){
            System.out.println( e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public static ObservableList<Country> getAllCountries(){
        ObservableList<Country> clist = FXCollections.observableArrayList();

        try{
          String sql = "SELECT * FROM countries";
          PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

          ResultSet rs = ps.executeQuery();

          while(rs.next()){
              int countryId = rs.getInt("Country_ID");
              String countryName = rs.getString("Country");
              LocalDate createDate = rs.getDate("Create_Date").toLocalDate();
              LocalTime createTime =  rs.getTime("Create_Date").toLocalTime();
              LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
              Country C = new Country(countryId, countryName);
              // Display Record
              System.out.println(countryId + " | " + countryName + " | " + createDate + " | " + createTime + " | " + lastUpdate);
              clist.add(C);
          }

        } catch (SQLException throwables) {
            System.out.println( throwables.getMessage());
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
