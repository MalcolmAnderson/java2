package DataAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Customer;
import models.Customers;
import models.Geography;
import models._ManageTestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.dataAccess.DAOGeography;
import utils.dataAccess.DBConnection;
import utils.dataAccess.DAOCustomers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
//import java.sql.PreparedStatement;
//import java.sql.*;


import static org.junit.jupiter.api.Assertions.*;


public class DAO_Customer_Tests {
    boolean keepRecords = true;
    DAOCustomers dao;

    @BeforeEach
    void setUp() {
        DBConnection.startConnection();
        dao = new DAOCustomers();
        if(! Geography.isKnownWorldLoaded()){
            Geography.setKnownWorld(DAOGeography.loadKnownWorld());
        }
    }

    @AfterEach
    void tearDown() {
        DBConnection.endConnection();
    }

    @Test void shouldAddCustomer(){
        System.out.println("Begin shouldAddCustomer");
        Customer customer = _ManageTestData.BuildTestData_Customers(22);
        customer.setLast_Update(LocalDateTime.of(2021, 02, 28, 14, 00, 00));
        assertEquals("New Jersey", customer.getDivisionName());
        assertEquals("U.S", customer.getCountryName());
        assertEquals(29, customer.getDivision_ID());
        Customers customers = dao.selectAllCustomers();
        // if test customer exists, delete it
        if(customers.customerExists(22)){
            dao.deleteCustomerByID(22);
            customers = dao.selectAllCustomers();
            if(customers.customerExists(22)){
                System.out.println("Failed to delete customer 22");
            }
        }
        // get customer count
        int customerCount = customers.getCustomers().size();
        // add test customer
        dao.insertOrUpdateCustomer(customer);
        customers = dao.selectAllCustomers();
        // check if customer count has increased by 1
        assertEquals(customerCount + 1, customers.getCustomers().size(), "failed to add customer");
        // delete test customer
        if(! keepRecords) {
            dao.deleteCustomerByID(22);
            // check that customer count has decreased by 1
            customers = dao.selectAllCustomers();
            assertEquals(customerCount, customers.getCustomers().size(), "failed to delete customer");
        }
    }
    @Test void shouldUpdateCustomer(){
        System.out.println("Begin shouldUpdateCustomer");

        // Setup
        int cust_id = 23;
        Customer customer = _ManageTestData.BuildTestData_Customers(cust_id);
        Customers customers = dao.selectAllCustomers();
        // if test customer exists, delete it
        if(customers.customerExists(cust_id)){
            dao.deleteCustomerByID(cust_id);
            customers = dao.selectAllCustomers();
            if(customers.customerExists(cust_id)){
                System.out.println("Failed to delete customer " + cust_id);
            }
        }
        // get customer count
        int customerCount = customers.getCustomers().size();
        // add test customer
        dao.insertOrUpdateCustomer(customer);

        customers = dao.selectAllCustomers();
        // check if customer count has increased by 1
        assertEquals(customerCount + 1, customers.getCustomers().size(), "failed to add customer");
        String currentCustName = customer.getCustomer_Name();
        assertEquals("Tom Slytherin", currentCustName);

        // Perform Test
        // make and save changes to the test customer
        customer.setDivision_ID(customer.getDivision_ID()+1);
        String newCustName = "To Protect The Innocent";
        customer.setCustomer_Name(newCustName);
        dao.insertOrUpdateCustomer(customer);

        // Validate
        // check if customer count has stayed the same
        customers = dao.selectAllCustomers();
        assertEquals(customerCount + 1, customers.getCustomers().size(), "update should not have changed the count");
        // find the customer and insure changes were made
        Customer updatedCustomer = customers.getCustomerByID(cust_id);
        assertEquals(newCustName, updatedCustomer.getCustomer_Name(), "customer info was not updated");

        // Cleanup
        // delete test customer
        if(! keepRecords) {
            dao.deleteCustomerByID(cust_id);
            // check that customer count has decreased by 1
            customers = dao.selectAllCustomers();
            assertEquals(customerCount, customers.getCustomers().size(), "failed to delete customer");
        }
    }

    @Test void shouldGetCustomers() throws SQLException {
        System.out.println("Begin shouldGetCustomers");
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
//            System.out.println("       getting Column names for customer");
            int size = rsmd.getColumnCount();
//            System.out.println("ResultSet column count: " + size);
            // Note: columns is apparently 1 based
            for (int i = 1; i <= size; i++) {
                System.out.println(rsmd.getColumnName(i));
            }
        }
    }

    @Test void shouldRefreshCustomers() {
        System.out.println("Begin shouldRefreshCustomers");
        Customers customers = new Customers();
        assertEquals(0, customers.getCustomers().size());

        customers = dao.selectAllCustomers();
        if(keepRecords) {
            assertEquals(5, customers.getCustomers().size());
        } else {
            assertEquals(3, customers.getCustomers().size());
        }
        ObservableList<Customer> olCustomers = FXCollections.observableArrayList();
        assertEquals(0, olCustomers.size());
        olCustomers.setAll(customers.getCustomers());
        if(keepRecords) {
            assertEquals(5, customers.getCustomers().size());
        } else {
            assertEquals(3, customers.getCustomers().size());
        }
    }

}
