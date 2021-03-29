package model_Tests;


import models.Customer;
import models.Customers;
import models._ManageTestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
public class Customer_Tests {

    Customers customers;

    @BeforeEach
    void setUp() {
        customers = new Customers();
    }

    @AfterEach void tearDown() {
    }

    @Test void shouldWork() {
        Customers customers = _ManageTestData.BuildPlaceHolder_Customers();
        assertEquals(1, customers.getCustomers().size());
    }

    @Test void shouldGetFirstCustomer(){
        Customers customers = _ManageTestData.BuildPlaceHolder_Customers();
        Customer customer = customers.getCustomers().get(0);
        assertEquals(1, customers.getCustomers().size());
        assertEquals(1, customer.getCustomer_ID());
        assertEquals("Daddy Warbucks", customer.getCustomer_Name());
        assertEquals("1919 Boardwalk, Trenton", customer.getAddress());
        assertEquals("01291", customer.getPostal_Code());
        assertEquals("869-908-1875", customer.getPhone());
        LocalDateTime createDate =  LocalDateTime.of(2021, 02, 23, 02, 11, 22);
        assertEquals(createDate, customer.getCreate_Date());
        assertEquals("script", customer.getCreated_By());
        LocalDateTime lastUpdate =  LocalDateTime.of(2021, 02, 23, 02, 11, 22);
        assertEquals(lastUpdate, customer.getLast_Update());
        assertEquals("script", customer.getLast_Updated_By());
        assertEquals(29, customer.getDivision_ID());


//        assertEquals(three.getEmail(), customer.getEmail());
//        assertEquals("class models.Customer", customer.getClass().toString());
//        assertEquals(6, customer.getId());
//        assertEquals("Malcolm MacLucas", customer.getName());
//        assertEquals("1135 SE Kamiaken Street, Pullman WA 99163", customer.getAddress());
//        assertEquals("malcolm.b.anderson@gmail.com", customer.getEmail());


    }


}
