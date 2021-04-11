package DataAccess;

import main.Globals;
import models.Appointment;
import models.Appointments;
import models._ManageTestData;
import org.junit.jupiter.api.*;
import utils.dataAccess.*;


import static org.junit.jupiter.api.Assertions.*;

public class DAO_Appointment_Tests {

    static DAOContacts daoContacts;
    static DAOAppointments dao;
    static DBQueryManager dbQM;
    static tableInfo a;
    static tableInfo c;



    @BeforeAll
    public static void runBeforeAll(){
        Globals.setUserName("DAO_Appointment_Tests");
        Globals.setUserId(1);
        DBConnection.startConnection();
        dbQM = new DBQueryManager();
        daoContacts = new DAOContacts(dbQM);
        daoContacts.selectAllContacts();
        dao = new DAOAppointments();
        dao.selectAllAppointments();
        a = new tableInfo_Appointments();
        c = new tableInfo_Customers();

    }
    @AfterAll
    public static void runAfterAll(){
        DBConnection.endConnection();
    }


    @BeforeEach
    void setUp() {
//        DBConnection.startConnection();
//        dao = new DAOAppointments();
    }

    @AfterEach
    void tearDown() {
//        DBConnection.endConnection();
    }

    private void RemoveTestAppointments(int begin, int end){
        for(int i = begin; i <= end; i++){
            dao.deleteAppointmentsByAppointmentId(i);
        }
    }

    @Test void shouldRefreshAppointments() {
        RemoveTestAppointments(40, 45);
        assertFalse(dbQM.recordExists(a.getTableName(), a.getPrimaryKeyName(), 40));
        assertFalse(dbQM.recordExists(a.getTableName(), a.getPrimaryKeyName(), 41));
        assertFalse(dbQM.recordExists(a.getTableName(), a.getPrimaryKeyName(), 42));
        assertFalse(dbQM.recordExists(a.getTableName(), a.getPrimaryKeyName(), 43));
        assertFalse(dbQM.recordExists(a.getTableName(), a.getPrimaryKeyName(), 44));
        assertFalse(dbQM.recordExists(a.getTableName(), a.getPrimaryKeyName(), 45));
        try {
            System.out.println("Begin shouldRefreshAppointments");
            Appointments appointments = new Appointments();
            System.out.println("begin assert statement");
            assertEquals(0, appointments.getAllAppointments().size());
            System.out.println("Begin dao.selectAllAppointments()");
            appointments = dao.selectAllAppointments();
//        appointments.setAllAppointments(dao.selectAllAppointments());
//        appointments.setAllAppointments(appointments.getAllAppointments());
            RemoveTestAppointments(40, 45);
            assertEquals(3, appointments.getAllAppointments().size());
            System.out.println("End shouldRefreshAppointments");
        } catch (Exception e){
            assertTrue(false, "An uncaught exception happened");
        }
    }


    @Test void shouldAddTestDataToAppointments(){
        System.out.println("Begin shouldAddTestDataToAppointments");
        RemoveTestAppointments(50, 55);
        assertTrue(dbQM.recordExists(a.getTableName(), a.getPrimaryKeyName(), 1));


        Appointments appointments = new Appointments();
        appointments = dao.selectAllAppointments();
        assertEquals(3, appointments.getAllAppointments().size());

        int currentSize = appointments.getAllAppointments().size();
        Appointments toBeAdded = _ManageTestData.BuildPlaceHolderData_Appointments();
        for (Appointment current : toBeAdded.getAllAppointments()) {
            appointments.addAppointment(current);
        }
        assertEquals(9, appointments.getAllAppointments().size());
        appointments = dao.selectAllAppointments();
        assertEquals(3, appointments.getAllAppointments().size());
        for (Appointment current : toBeAdded.getAllAppointments()) {
            dao.insertAppointment(current);
        }
        Appointment current = toBeAdded.getAllAppointments().get(0);
        System.out.println("Current Appointment Data");
        System.out.println(current.toString());
        dao.insertAppointment(current);
        appointments = dao.selectAllAppointments();
        assertEquals(9, appointments.getAllAppointments().size());
        assertFalse(true);
        System.out.println("End shouldAddTestDataToAppointments");
    }

    @Test void shouldCheckRecordExists(){
//        tableInfo a = new tableInfo_Appointments();
//        tableInfo c = new tableInfo_Customers();
//        DBQueryManager dbQM = new DBQueryManager();
        assertTrue(dbQM.recordExists(a.getTableName(), a.getPrimaryKeyName(), 1));
        assertTrue(dbQM.recordExists(a.getTableName(), c.getPrimaryKeyName(), 1));
    }


}
