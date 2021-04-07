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
import utils.dataAccess.DAOCustomers;
import utils.dataAccess.DAOGeography;
import utils.dataAccess.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DAOCustomerTimeZoneChange {

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


//        // delete test customer
//        dao.deleteCustomerByID(22);
//        // check that customer count has decreased by 1
//        customers = dao.selectAllCustomers();
//        assertEquals(customerCount, customers.getCustomers().size(), "failed to delete customer");
    }

}
