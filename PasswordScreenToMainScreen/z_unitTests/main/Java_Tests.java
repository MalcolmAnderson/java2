package main;

import models.Customer;
import models.Geography;
import models._ManageTestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;
import utils.dataAccess.DAOCustomers;
import utils.dataAccess.DAOGeography;
import utils.dataAccess.DBConnection;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Java_Tests {
    DAOCustomers dao;
    Utils utils;

    @BeforeEach
    void setUp() {
        DBConnection.startConnection();
        dao = new DAOCustomers();
        if(! Geography.isKnownWorldLoaded()){
            Geography.setKnownWorld(DAOGeography.loadKnownWorld());
        }
        utils = new Utils();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void stringFormatShouldWork() {

        Customer current = _ManageTestData.BuildTestData_Customers(22);
        LocalDateTime lastUpdate = current.getLast_Update();
        assertEquals("2021-02-23 02:11:22-07:00",  lastUpdate.toString());
        String sqlStatement = String.format(
                "UPDATE customers SET"
                        + " Customer_Name = '%s', Address = '%s',"
                        + " Postal_Code = '%s', Phone = '%s',  Last_Update = '%s',"
                        + " Last_Updated_By = '%s', Division_ID = '%s' WHERE Customer_ID = %s;",
                current.getCustomer_Name(),
                current.getAddress(), current.getPostal_Code(),
                current.getPhone(), lastUpdate.toString(),
                Globals.getUserName(),
                current.getDivision_ID(), current.getCustomer_ID());

        assertEquals("UPDATE customers SET Customer_Name = 'Eric Estrada', Address = '1919 Main Street, Los Angeles', Postal_Code = '90031', Phone = '323-908-1875',  Last_Update = '2021-02-23 02:11:22-07:00', Last_Updated_By = 'userName not set', Division_ID = '29' WHERE Customer_ID = 22;", sqlStatement);
    }

}
