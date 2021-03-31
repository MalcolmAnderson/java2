package DataAccess;

import models.Geography;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.dataAccess.DAOContacts;
import utils.dataAccess.DAOGeography;
import utils.dataAccess.DBConnection;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DAO_Geography_Tests {

    DAOGeography dao;

    @BeforeEach
    void setUp() {
        DBConnection.startConnection();
        dao = new DAOGeography();
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
    }

    @Test void shouldGetAllStatesWhenPassingUS() {
        ArrayList<String> division = dao.getDivisionsByCountry_ID(1);
        assertEquals(50, division.size());
    }
}