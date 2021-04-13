package DataAccess;

import main.Globals;
import models.Appointment;
import models.Appointments;
import models._ManageTestData;
import org.junit.jupiter.api.*;
import utils.dataAccess.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class _LoadAppointment_Tests {


    static DAOContacts daoContacts;
    static DAOAppointments dao;
    static DBQueryManager dbQM;
    static tableInfo a;
    static tableInfo c;


    @BeforeAll
    public static void runBeforeAll() {
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
    public static void runAfterAll() {
        DBConnection.endConnection();
    }


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Disabled
    @Test
    void shouldAddTestDataToAppointments() {
        System.out.println("Begin shouldAddTestDataToAppointments");

        Appointments appointmentsDisconnectedFromDB = new Appointments();
        appointmentsDisconnectedFromDB = dao.selectAllAppointments();

        // create test data (6 records) but do not commit
        Appointments toBeAdded = _ManageTestData.BuildPlaceHolderData_Appointments();
        for (Appointment current : toBeAdded.getAllAppointments()) {
            appointmentsDisconnectedFromDB.addAppointment(current);
        }
        // commit test data to DB
        for (Appointment current : toBeAdded.getAllAppointments()) {
            dao.insertOrUpdateAppointment(current);
        }
        appointmentsDisconnectedFromDB = dao.selectAllAppointments();
        assertEquals(9, appointmentsDisconnectedFromDB.getAllAppointments().size());
        // get a single record from test data
        System.out.println("End shouldAddTestDataToAppointments");
    }
}