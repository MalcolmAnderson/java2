package models;


import models.Customers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;
import utils.dataAccess.DOACustomers;

import static org.junit.jupiter.api.Assertions.*;
public class CustomerTests {

    Customers customers;

    @BeforeEach
    void setUp() {
        customers = new Customers();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldWork() {
        Customers customers = ManageTestData.BuildPlaceHolder_Customers();
        assertEquals(5, customers.getCustomers().size());

    }
}
