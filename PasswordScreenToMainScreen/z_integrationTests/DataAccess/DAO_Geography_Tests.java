package DataAccess;

import models.Country;
import models.Geography;
import org.junit.jupiter.api.*;
import utils.dataAccess.DAOGeography;
import utils.dataAccess.DBConnection;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DAO_Geography_Tests {

    private static DAOGeography dao;

    @BeforeAll static void runOnceBeforeAllHaveStarted(){
        System.out.println("begin beforeAll");
        DBConnection.startConnection();
        dao = new DAOGeography();
        ArrayList<Geography> knownWorld = Geography.getKnownWorld();
        assertEquals(null, knownWorld);
        knownWorld = DAOGeography.loadKnownWorld();
        assertEquals(68, knownWorld.size());
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
        Geography geography = dao.getGeographyByDivisionId(54);
        System.out.println(geography.getDivisionId());
        assertEquals(54,geography.getDivisionId());
        assertEquals("U.S", geography.getCountryName());
        assertEquals("Alaska", geography.getDivisionName());
        assertEquals(1,geography.getCountryId());
    }

    @Test void shouldGetAllStatesWhenPassingUS() {
        ArrayList<Geography> divisions = dao.getDivisionsByCountry_ID(1);
        assertEquals(51, divisions.size());
    }

    @Test void shouldPopulateGeography_KnownWorld(){
        ArrayList<Geography> knownWorld = Geography.getKnownWorld();
        assertEquals(null, knownWorld);
        Geography.setKnownWorld(DAOGeography.loadKnownWorld());
        ArrayList<Geography> knownWorldCopy = Geography.getKnownWorld();
        assertEquals(68, knownWorldCopy.size());
    }

    @Test public void shouldGetAllCountries(){
        ArrayList<Geography> knownWorld = Geography.getKnownWorld();
        assertEquals(null, Geography.getKnownWorld());
        Geography.setKnownWorld(knownWorld);
        ArrayList<Country> countryList = DAOGeography.getCountries();
        assertEquals(3, countryList.size());
    }


}