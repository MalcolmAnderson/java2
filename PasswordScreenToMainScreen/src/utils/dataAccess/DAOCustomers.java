package utils.dataAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Appointment;
import models.Customer;
import models.Customers;
import utils.Utils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DAOCustomers {


    public static Customers selectAllCustomers(){
        Utils utils = new Utils();
        Customers customers = new Customers();

        // TODO Replace all this appointment code with CUSTOMER code.

        try{
            String sql = "SELECT * FROM customers;";

            String shouldWork = "SELECT Customer_ID, Customer_Name, Address, Postal_Code"
                    + ", Phone, c.Create_Date as Create_Date, c.Created_By, c.Last_Update"
                    + ", c.Last_Updated_By, c.Division_ID, d.Division, tree.Country "
                    + "FROM customers as c "
                    + "left join first_level_divisions as d on "
                    + "c.Division_ID = d.Division_ID "
                    + "left join countries as tree on "
                    + "d.Country_ID = tree.Country_ID;";

//            sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code"
//                    + ", Phone, c.Create_Date as Create_Date, c.Created_By, c.Last_Update"
//                    + ", c.Last_Updated_By, c.Division_ID, d.Division, tree.Country "
//                    + "FROM customers as c "
//                    + "left join first_level_divisions as d on "
//                    + "c.Division_ID = d.Division_ID "
//                    + "left join countries as tree on "
//                    + "d.Country_ID = tree.Country_ID "
//                    + "limit 1;";



            sql = shouldWork;
            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println("       getting Column names for customer");
                int customer_ID = rs.getInt("Customer_ID");
                String customer_Name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postal_Code = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                LocalDateTime create_Date = utils.TimeStamp_to_LocalDateTime(rs.getTimestamp("Create_Date"));
                String created_By = rs.getString("Created_By");
                LocalDateTime last_Update = utils.TimeStamp_to_LocalDateTime(rs.getTimestamp("Last_Update"));
                String last_Updated_By = rs.getString("Last_Updated_By");
                int division_ID = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                String country = rs.getString("Country");

                Customer current = new Customer(
                    customer_ID,
                    customer_Name,
                    address,
                    postal_Code,
                    phone,
                    create_Date,
                    created_By,
                    last_Update,
                    last_Updated_By,
                    division_ID,
                    division,
                    country);
                customers.addCustomer(current);
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
