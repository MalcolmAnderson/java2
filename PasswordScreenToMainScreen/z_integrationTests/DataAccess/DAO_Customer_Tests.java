package DataAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Customer;
import models.Customers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.dataAccess.DBConnection;
import utils.dataAccess.DAOCustomers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
//import java.sql.PreparedStatement;
//import java.sql.*;


import static org.junit.jupiter.api.Assertions.*;


public class DAO_Customer_Tests {

    DAOCustomers dao;

    @BeforeEach
    void setUp() {
        DBConnection.startConnection();
        dao = new DAOCustomers();
    }

    @AfterEach
    void tearDown() {
        DBConnection.endConnection();
    }

    @Test void shouldGetCustomers() throws SQLException {
        String sql = "SELECT * FROM customers;";

        sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code"
                + ", Phone, c.Create_Date as Create_Date, c.Created_By, c.Last_Update"
                + ", c.Last_Updated_By, c.Division_ID, d.Division, tree.Country "
                + "FROM customers as c "
                + "left join first_level_divisions as d on "
                + "c.Division_ID = d.Division_ID "
                + "left join countries as tree on "
                + "d.Country_ID = tree.Country_ID "
                + "limit 1;";



        String shouldWork = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, "
                + "Phone, c.Create_Date as Create_Date, c.Created_By, c.Last_Update, "
                + "c.Last_Updated_By, c.Division_ID, d.Division tree.Country "
                + "FROM customers as c "
                + "left join first_level_divisions as d on "
                + "c.Division_ID = d.Division_ID "
                + "left join countries as tree on "
                + "d.Country_ID = tree.Country_ID "
                + "limit 1;";

//        sql = shouldWork;

        System.out.println("sql statement");
        System.out.println(sql);
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        while(rs.next()) {
            System.out.println("       getting Column names for customer");
            int size = rsmd.getColumnCount();
            System.out.println("ResultSet column count: " + size);
            // Note: columns is apparently 1 based
            for (int i = 1; i <= size; i++) {
                System.out.println(rsmd.getColumnName(i));
            }
        }
    }

    @Test void shouldRefreshCustomers() {
        Customers customers = new Customers();
        assertEquals(0, customers.getCustomers().size());
        customers = dao.selectAllCustomers();
        assertEquals(3, customers.getCustomers().size());
        ObservableList<Customer> olCustomers = FXCollections.observableArrayList();
        assertEquals(0, olCustomers.size());
        olCustomers.setAll(customers.getCustomers());
        assertEquals(3, olCustomers.size());
    }

}
