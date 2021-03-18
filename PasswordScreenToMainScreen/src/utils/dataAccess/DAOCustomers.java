package utils.dataAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Appointment;
import models.Customer;
import models.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DAOCustomers {

    public static Customers selectAllCustomers(){
        Customers customers = new Customers();

        // TODO Replace all this appointment code with CUSTOMER code.

        try{
            String sql = "SELECT * FROM customers;";

            String shouldWork = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, "
                    + "Phone, c.Create_Date, c.Created_By, c.Last_Update, "
                    + "c.Last_Updated_By, c.Division_ID, d.Division tree.Country"
                    + "FROM customers as c "
                    + "left join first_level_divisions as d  on "
                    + "c.Division_ID = d.Division_ID "
                    + "left join countries as tree on "
                    + "d.Country_ID = tree.Country_ID;";

            sql = shouldWork;
            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int customer_ID = rs.getInt("Customer_ID");
                String customer_Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String location = rs.getString("Location");
                String contactName = rs.getString("Contact_Name");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                int customerId = rs.getInt("Customer_ID");
                String customer_Name = rs.getString("Customer_Name");

                Appointment current = new Appointment(appointmentId,
                        title,
                        description,
                        location,
                        contactName,
                        type,
                        LocalDateTime start = utils.TimeStamp_to_LocalDateTime(rs.getTimestamp("Start"));
                LocalDateTime end = utils.TimeStamp_to_LocalDateTime(rs.getTimestamp("End"));

                LocalDateTime.of(2021, 04, 15, 9, 00),
                        LocalDateTime.of(2021, 04, 15, 10, 00),
                        customerId,
                        customer_Name);


                System.out.println(appointmentId + ", " + title + ", " + description + ", " + customerId  + ", " + contactName);
            }

        } catch (SQLException throwables) {
            System.out.println( throwables.getMessage());
            throwables.printStackTrace();
        }
        return customers;
    }

    public ArrayList<Customer> SelectAllCustomers() {
        return new ArrayList<Customer>();
    }
}
