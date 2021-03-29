package DataAccess;

import models.Appointment;
import models.Appointments;
import models._ManageTestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.dataAccess.DAOAppointments;
import utils.dataAccess.DBConnection;

import static org.junit.jupiter.api.Assertions.*;

public class DAO_Appointment_Tests {

    DAOAppointments dao;

    @BeforeEach
    void setUp() {
        DBConnection.startConnection();
        dao = new DAOAppointments();
    }

    @AfterEach
    void tearDown() {
        DBConnection.endConnection();
    }

    @Test
    void shouldRefreshAppointments() {
        try {
            System.out.println("Begin shouldRefreshAppointments");
            Appointments appointments = new Appointments();
            System.out.println("begin assert statement");
            assertEquals(0, appointments.getAllAppointments().size());
            System.out.println("Begin dao.selectAllAppointments()");
            appointments = dao.selectAllAppointments();
//        appointments.setAllAppointments(dao.selectAllAppointments());
//        appointments.setAllAppointments(appointments.getAllAppointments());
            assertEquals(2, appointments.getAllAppointments().size());
            System.out.println("End shouldRefreshAppointments");
        } catch (Exception e){
            assertTrue(false, "An uncaught exception happened");
        }
    }
    
    @Test void shouldAddTestDataToAppointments(){
        System.out.println("Begin shouldAddTestDataToAppointments");
        Appointments appointments = new Appointments();
        appointments = dao.selectAllAppointments();
        int currentSize = appointments.getAllAppointments().size();
        Appointments toBeAdded = _ManageTestData.BuildPlaceHolderData_Appointments();
        for (Appointment current : toBeAdded.getAllAppointments()) {
            appointments.addAppointment(current);
        }
        assertEquals(8, appointments.getAllAppointments().size());
        appointments = dao.selectAllAppointments();
        assertEquals(2, appointments.getAllAppointments().size());
//        for (Appointment current : toBeAdded.getAllAppointments()) {
//            dao.insertAppointment(current);
//        }
        Appointment current = toBeAdded.getAllAppointments().get(0);
        System.out.println("Current Appointment Data");
        System.out.println(current.toString());
        dao.insertAppointment(current);
        appointments = dao.selectAllAppointments();
        assertEquals(8, appointments.getAllAppointments().size());
        System.out.println("End shouldAddTestDataToAppointments");
    }


}
