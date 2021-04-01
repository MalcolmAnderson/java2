package model_Tests;

import models.*;
import org.junit.jupiter.api.*;


import models.Customers;
import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeEach;
import utils.dataAccess.DAOGeography;
import utils.dataAccess.DBConnection;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class Geography_Tests {


    static ArrayList<Geography> localWorld;

    @BeforeAll public static void runBeforeAll(){
        DBConnection.startConnection();
        localWorld = DAOGeography.loadKnownWorld();
        assertEquals(68, localWorld.size());
    }
    @AfterAll public static void runAfterAll(){
//        DBConnection.endConnection();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test public void shouldGetAllStates(){
        ArrayList<Geography> states = new ArrayList<Geography>();
        localWorld.forEach((current) -> {
            if(current.getCountryId() == 1){
                states.add(current);
            }
        });
        assertEquals(68, localWorld.size());
        assertEquals(51, states.size());
    }

    @Test public void shouldGetAllProvinces(){
        assertEquals(null, Geography.getKnownWorld());
        Geography.setKnownWorld(localWorld);
        assertEquals(68, Geography.getKnownWorld().size());
        ArrayList<Geography> provinces = new ArrayList<Geography>();
        provinces = Geography.getDivisionsForCountryID(3);
        assertEquals(13, provinces.size());
    }
}