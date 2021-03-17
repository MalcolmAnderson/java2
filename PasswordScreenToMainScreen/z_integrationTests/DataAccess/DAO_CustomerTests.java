package DataAccess;

import models.Customer;
import models.Customers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;
import utils.dataAccess.DBConnection;
import utils.dataAccess.DOACustomers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class DAO_CustomerTests {

    DOACustomers dao;

    @BeforeEach
    void setUp() {
        DBConnection.startConnection();
        dao = new DOACustomers();
    }

    @AfterEach
    void tearDown() {
        DBConnection.endConnection();
    }

    @Test
    void shouldRefreshCustomers() {
        Customers customers = new Customers();
        assertEquals(0, customers.getCustomers().size());
        ArrayList<Customer> al = dao.SelectAllCustomers();
        customers.setAllCustomers(al);
        assertEquals(3, customers.getCustomers().size());

    }
}
