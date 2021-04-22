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

import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


    @Test void CreateTimeStampShouldWork(){
        LocalDateTime one = LocalDateTime.of(1964, 3, 14, 23, 42, 56);
        LocalDateTime two = LocalDateTime.of(1971, 5, 26, 9, 56, 27);
        assertEquals("1964-03-14T23:42:56", one.toString());
    }

    @Test
    void stringFormatShouldWork() {

        Customer current = _ManageTestData.BuildTestData_Customers(22);
        LocalDateTime lastUpdate = current.getLast_Update();
        assertEquals("2021-02-23T02:11:22",  lastUpdate.toString());
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

        assertEquals("UPDATE customers SET Customer_Name = 'Eric Estrada', Address = '1919 Main Street, Los Angeles', Postal_Code = '90031', Phone = '323-908-1875',  Last_Update = '2021-02-23T02:11:22', Last_Updated_By = 'userName not set', Division_ID = '4' WHERE Customer_ID = 22;", sqlStatement);
    }

    @Test public void getDirectoryAndFiles(){
        String userDirectory = FileSystems.getDefault()
                .getPath("utils/localization")
                .toAbsolutePath()
                .toString();
        assertEquals("D:\\dev\\WGU\\java2\\C195-Java2-Prod\\utils\\localization", userDirectory);
//        assertTrue(userDirectory.endsWith("."));
    }

    @Test public void anotherTest(){
        String rbPath = "utils.localization/Nat";
        ResourceBundle rb = ResourceBundle.getBundle(rbPath, Locale.getDefault());
        String foo = rb.getString("Phone");
        assertEquals("Phone", foo);
    }

    @Test public void whatCountsAsBefore10(){
        LocalTime lateLimit = LocalTime.of(22, 0,1);
        LocalTime lt = LocalTime.of(22, 0, 0);
        boolean ltIsBefore10 = lt.isBefore(lateLimit);
        assertTrue(ltIsBefore10);
    }

    @Test public void usingAHashMap(){
        HashMap<String, Integer> capitalCities = new HashMap<String, Integer>();

        // Add keys and values (Country, City)
        String city = "England";
        capitalCities.put(city, 1);
        capitalCities.put("Germany", 1);
        capitalCities.put("Norway", 1);
        capitalCities.put("USA", 1);
        int ec = capitalCities.get("England") + 1;
        capitalCities.put("England", ++ec);
        System.out.println(capitalCities.get(city));
        System.out.println(capitalCities.get("foo"));
        System.out.println(null == capitalCities.get("foo"));
        System.out.println(capitalCities);
    }

    @Test void anonymousClassShouldWork_1(){
        class ZooGiftShop{
            abstract class SaleTodayOnly{
                abstract int dollarsOff();
            }
            public int admission(int basePrice){
                SaleTodayOnly sale = new SaleTodayOnly() {
                    @Override
                    int dollarsOff() {return 3;}
                }; // pay attention to this semicolon
                return basePrice - sale.dollarsOff();
            }
        }
        ZooGiftShop zoo = new ZooGiftShop();
        assertEquals(7, zoo.admission(10));
    }
    public static class ZooGiftShop_2 {
        interface SaleTodayOnly { // internal interfaces can only exist inside a static class
            int dollarsOff();
        }
        public int admission(int basePrice){
            SaleTodayOnly sale = new SaleTodayOnly() {
                public int dollarsOff() {return 4;}
            }; // pay attention to this semicolon
            return basePrice - sale.dollarsOff();
        }
    }

    @Test void anonymousClassShouldWork_2(){
        ZooGiftShop_2 zoo = new ZooGiftShop_2();
        assertEquals(6, zoo.admission(10));
    }

}
