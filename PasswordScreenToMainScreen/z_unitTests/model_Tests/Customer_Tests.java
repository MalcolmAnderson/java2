package model_Tests;


import models.Customer;
import models.Customers;
import models.Geography;
import models._ManageTestData;
import org.junit.jupiter.api.*;
import utils.dataAccess.DAOGeography;
import utils.dataAccess.DBConnection;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
public class Customer_Tests {

    Customer customer;
    Customers customers = new Customers();

    @BeforeAll public static void runBeforeAll(){
        DBConnection.startConnection();
        Geography.setKnownWorld(DAOGeography.loadKnownWorld());
        assertEquals(68, Geography.getKnownWorld().size());
    }
    @AfterAll
    public static void runAfterAll(){
        DBConnection.endConnection();
    }

    @BeforeEach
    void setUp() {
        customer = null;
    }

    @AfterEach void tearDown() {
    }

    @Test void shouldWork() {
        Customer customer = _ManageTestData.BuildTestData_Customers(22);
        customers.addCustomer(customer);
        assertEquals(1, customers.getCustomers().size());
    }

    @Test void shouldGetFirstCustomer(){
        Customer customer = _ManageTestData.BuildTestData_Customers(23);
        customers.addCustomer(customer);
        assertEquals(1, customers.getCustomers().size());
        assertEquals(23, customer.getCustomer_ID());
        assertEquals("Tom Slytherin", customer.getCustomer_Name());
        assertEquals("2782 Alberta Street, Portland", customer.getAddress());
        assertEquals("98601", customer.getPostal_Code());
        assertEquals("503-908-1875", customer.getPhone());
        LocalDateTime createDate =  LocalDateTime.of(2021, 02, 23, 02, 11, 22);
        assertEquals(createDate, customer.getCreate_Date());
        assertEquals("Unit Tests", customer.getCreated_By());
        LocalDateTime lastUpdate =  LocalDateTime.of(2021, 02, 23, 02, 11, 22);
        assertEquals(lastUpdate, customer.getLast_Update());
        assertEquals("Unit Tests", customer.getLast_Updated_By());
        assertEquals(36, customer.getDivision_ID());


//        assertEquals(three.getEmail(), customer.getEmail());
//        assertEquals("class models.Customer", customer.getClass().toString());
//        assertEquals(6, customer.getId());
//        assertEquals("Malcolm MacLucas", customer.getName());
//        assertEquals("1135 SE Kamiaken Street, Pullman WA 99163", customer.getAddress());
//        assertEquals("malcolm.b.anderson@gmail.com", customer.getEmail());


    }


}
