package utils.dataAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Customer;
import models.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DOACustomers {

    public static Customers selectAllCustomers(){
        Customers customers = new Customers();
        try{
            String sql = "SELECT * FROM customers;";

            String shouldWork = "SELECT Appointment_Id, Title, Description, Location, "
                    + "contacts.Contact_Name, Type, Start, End, customers.Customer_ID FROM appointments "
                    + "left join customers on "
                    + "appointments.Customer_ID = customers.Customer_ID "
                    + "left join contacts on "
                    + "appointments.Contact_ID = contacts.Contact_ID;";
            sql = shouldWork;
            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int appointmentId = rs.getInt("Appointment_Id");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String contactName = rs.getString("Contact_Name");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                int customerId = rs.getInt("Customer_ID");

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
