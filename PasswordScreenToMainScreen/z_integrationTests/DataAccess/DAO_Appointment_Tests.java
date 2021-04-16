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
            RemoveTestAppointments(40, 45);
            appointments = dao.selectAllAppointments();
            assertEquals(3, appointments.getAllAppointments().size());
            System.out.println("End shouldRefreshAppointments");
        } catch (Exception e){
            assertTrue(false, "An uncaught exception happened");
        }
    }


    @Test void shouldAddTestDataToAppointments(){
        System.out.println("Begin shouldAddTestDataToAppointments");

        // remove any left over test data and confirm base line of 3 appointments
        RemoveTestAppointments(50, 55);
        assertTrue(dbQM.recordExists(a.getTableName(), a.getPrimaryKeyName(), 1));
        Appointments appointmentsDisconnectedFromDB = new Appointments();
        appointmentsDisconnectedFromDB = dao.selectAllAppointments();
        int currentSize = appointmentsDisconnectedFromDB.getAllAppointments().size();
        assertEquals(3, currentSize);

        // create test data (6 records) but do not commit
        Appointments toBeAdded = _ManageTestData.BuildPlaceHolderData_Appointments();
        for (Appointment current : toBeAdded.getAllAppointments()) {
            appointmentsDisconnectedFromDB.addAppointment(current);
        }
        assertEquals(9, appointmentsDisconnectedFromDB.getAllAppointments().size());
        // reload original 3 records from DB
        appointmentsDisconnectedFromDB = dao.selectAllAppointments();
        assertEquals(3, appointmentsDisconnectedFromDB.getAllAppointments().size());
        // commit test data to DB
        assertEquals(6, toBeAdded.getAllAppointments().size());
        for (Appointment current : toBeAdded.getAllAppointments()) {
            dao.insertOrUpdateAppointment(current);
        }
        appointmentsDisconnectedFromDB = dao.selectAllAppointments();
        assertEquals(9, appointmentsDisconnectedFromDB.getAllAppointments().size());
        // get a single record from test data
        Appointment current = toBeAdded.getAllAppointments().get(0);
        System.out.println("Original duplicate Appointment data");
        System.out.println(current.toString());
        // Confirm data (check old title)
        assertEquals("Bob #1", current.getTitle());
        int idOfUpdatedAppointment = current.getId();
        // modify data
        String newTitle = "Modified by integration test";
        current.setTitle(newTitle);
        assertEquals(newTitle, current.getTitle());
        System.out.println("Modified duplicate Appointment data");
        System.out.println(current.toString());
        // attempt to "add" updated record
        dao.insertOrUpdateAppointment(current);
        // reload data from DB
        appointmentsDisconnectedFromDB = dao.selectAllAppointments();
        // confirm size has not changed
        currentSize = appointmentsDisconnectedFromDB.getAllAppointments().size();
        assertEquals(9, currentSize, "Size should not have changed");
        // check modified data
        current = appointmentsDisconnectedFromDB.getAppointmentById(idOfUpdatedAppointment);
        if(current != null) {
            assertEquals(newTitle, current.getTitle());
        } else {
            fail("updated record not found in Appointments list");
        }
        RemoveTestAppointments(50, 55);
        appointmentsDisconnectedFromDB = dao.selectAllAppointments();
        assertEquals(3, appointmentsDisconnectedFromDB.getAllAppointments().size());
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
