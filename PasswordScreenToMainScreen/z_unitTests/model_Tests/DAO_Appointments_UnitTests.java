package model_Tests;

import main.Globals;
import models.Appointment;
import models.Appointments;
import models._ManageTestData;
import org.junit.jupiter.api.*;
import utils.Utils;
import utils.dataAccess.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DAO_Appointments_UnitTests {

    //@FXML private TableView appointmentsTableView = new TableView();

    Appointments appointments;
    DAOAppointments dao;

    static Utils utils;
    static LocalDateTime forcedValueForNow = LocalDateTime.of(1980, 01, 01, 00,00,00);


    @BeforeAll
    public static void runBeforeAll(){
        utils = new Utils();
        utils.setForcedNowValue(forcedValueForNow);
    }
    @AfterAll
    public static void runAfterAll(){
    }


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test public void shouldGetSQLString_InsertAppointment(){
        Appointment a = _ManageTestData.BuildTestData_Appointment(0);
        DAOAppointments dao = new DAOAppointments();
        String insertStatement = dao.createStatement_InsertAppointment(a);
        String expected = "INSERT INTO appointments (" +
                "Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, " +
                "Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) " +
                "VALUES (" +
                "'60', " +
                "'This is a title', " +
                "'This is a description', " +
                "'location', " +
                "'type', " +
                "'1964-03-14T23:15:48', '2765-04-15T02:17:52', '1980-01-01T08:00', " +
                "'-1', '1980-01-01T08:00', '-1', 1, -1, 2);";
        assertEquals(expected, insertStatement);
    }

    @Test public void shouldGetSQLString_UpdateAppointment(){
        assertEquals("1980-01-01T00:00", forcedValueForNow.toString());
        assertEquals("1980-01-01T00:00", utils.now().toString());

        Appointment a = _ManageTestData.BuildTestData_Appointment(0);
        DAOAppointments dao = new DAOAppointments();
        String expected = "UPDATE appointments SET Title = " +
                "'This is a title', Description = 'This is a description', " +
                "Location = 'location', Type = 'type', Start = '1964-03-14T23:15:48', " +
                "End  = '2765-04-15T02:17:52', Last_Update = '1980-01-01T08:00', " +
                "Last_Updated_By = '-1', Customer_ID = 1, User_ID = -1, " +
                "Contact_ID = 2 WHERE Appointment_ID = 60";
        String updateStatement = dao.createStatement_UpdateAppointment(a);
        assertEquals(expected, updateStatement);
    }

    @Test public void shouldGetGoodBetweenSelectStatement(){
        dao = new DAOAppointments();
        LocalDateTime start = LocalDateTime.of(2021, 04, 10, 0,0,0);
        LocalDateTime end = LocalDateTime.of(2021, 04, 15, 0,0,0);
        String expected = "SELECT * FROM appointments where Start >= '2021-04-10 07:00:00.0' AND End <= '2021-04-15T07:00';";
        String actual = dao.getBetweenSQLStatement(start, end);
        assertEquals(expected, actual, "SQL selection mistake.");
    }

    @Test public void shouldGetSQLString_DeleteByAppointmentId(){
        int appointmentId = 1;
        DAOAppointments dao = new DAOAppointments();
        String deleteStatement = dao.createStatement_DeleteAppointmentByAppointmentId(appointmentId);
        String expected = "DELETE FROM appointments WHERE Appointment_ID = 1";
        assertEquals(expected, deleteStatement);
    }

    @Test public void shouldGetSQLString_DeleteByCustomerId(){
        Appointment a = _ManageTestData.BuildTestData_Appointment(0);
        DAOAppointments dao = new DAOAppointments();
        String deleteStatement = dao.createStatement_DeleteAppointmentByCustomerId(a.getCustomer_Id());
        String expected = "DELETE FROM appointments WHERE Customer_ID = 1";
        assertEquals(expected, deleteStatement);
    }

}