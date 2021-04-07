package DataAccess;

import models.Country;
import models.Geography;
import org.junit.jupiter.api.*;
import utils.dataAccess.DAOGeography;
import utils.dataAccess.DBConnection;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class DAO_Geography_Tests
{

    @BeforeAll static void runOnceBeforeAllHaveStarted(){
        System.out.println("begin beforeAll");
        DBConnection.startConnection();
        Geography.setKnownWorld(DAOGeography.loadKnownWorld());
        System.out.println("end beforeAll");
    }
    @AfterAll static void runOnceAfterAllAreDone(){
        System.out.println("begin afterAll");
        DBConnection.endConnection();
        System.out.println("end afterAll");
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test void shouldGetUSWhenPassingAlaska() {
        System.out.println("Begin shouldGetUSWhenPassingAlaska");
        Geography geography = Geography.getGeographyByDivisionId(54);
        System.out.println(geography.getDivisionId());
        assertEquals(54,geography.getDivisionId());
        assertEquals("U.S", geography.getCountryName());
        assertEquals("Alaska", geography.getDivisionName());
        assertEquals(1,geography.getCountryId());
    }

    @Test void shouldGetAllStatesWhenPassingUS() {
        System.out.println("Begin shouldGetAllStatesWhenPassingUS");
        ArrayList<Geography> divisions = Geography.getDivisionsByCountry_ID(1);
        assertEquals(51, divisions.size());
    }

    @Test void shouldPopulateGeography_KnownWorld(){
        System.out.println("Begin shouldPopulateGeography_KnownWorld");
        assertTrue(Geography.isKnownWorldLoaded());
        ArrayList<Geography> knownWorldCopy = Geography.getKnownWorld();
        assertEquals(68, knownWorldCopy.size());
    }

    @Test public void shouldGetAllCountries(){
        System.out.println("Begin shouldGetAllCountries");
        assertTrue(Geography.isKnownWorldLoaded());
        ArrayList<Country> countryList = DAOGeography.getCountries();
        assertEquals(3, countryList.size());
    }


}