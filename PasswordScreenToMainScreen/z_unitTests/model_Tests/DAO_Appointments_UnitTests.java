package model_Tests;

import models.Appointment;
import models.Appointments;
import models._ManageTestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.dataAccess.DAOAppointments;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DAO_Appointments_UnitTests {

    //@FXML private TableView appointmentsTableView = new TableView();

    Appointments appointments;

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
        String expected = "INSERT INTO appointments (\n" +
                "Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date,  \n" +
                "Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)  \n" +
                "VALUES (\n" +
                "'4', \n" +
                "'Bob  part1', \n" +
                "'An initial visit to determine needs', \n" +
                "'Safeway Starbucks', \n" +
                "'buyer', \n" +
                "'2021-04-15 09:00', '2021-04-15 10:00', '2021-03-27 22:16:26', \n" +
                "'Test', '2021-03-27 22:16:26.543598700', 'Test', 1, 2, 3);";
        assertTrue(false);
    }

    @Test public void shouldGetSQLString_UpdateAppointment(){
        Appointment a = _ManageTestData.BuildTestData_Appointment(0);
        DAOAppointments dao = new DAOAppointments();
        String updateStatement = dao.createStatement_UpdateAppointment(a);
        assertTrue(false);
    }

    @Test public void shouldGetSQLString_DeleteByAppointmentId(){
//        Appointment a = _ManageTestData.BuildTestData_Appointment(0);
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