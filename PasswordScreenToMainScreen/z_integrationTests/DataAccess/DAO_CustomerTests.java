package z_integrationTests.DataAccess;

import models.Customers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.dataAccess.DBConnection;
import utils.dataAccess.DAOCustomers;

import static org.junit.jupiter.api.Assertions.*;


public class DAO_CustomerTests {

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

    @Test
    void shouldRefreshCustomers() {
        Customers customers = new Customers();
        assertEquals(0, customers.getCustomers().size());
        //customers = dao.
        customers = dao.selectAllCustomers();
        assertEquals(3, customers.getCustomers().size());

    }
}
