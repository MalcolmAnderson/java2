package z_integrationTests.DataAccess;

import models.Appointments;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.dataAccess.DAOAppointments;
import utils.dataAccess.DBConnection;

import static org.junit.jupiter.api.Assertions.*;

public class DAO_Appointment_tests {

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
    void shouldRefreshCustomers() {
        Appointments appointments = new Appointments();
        assertEquals(0, appointments.getAllAppointments().size());
        appointments = dao.selectAllAppointments();
//        appointments.setAllAppointments(dao.selectAllAppointments());
//        appointments.setAllAppointments(appointments.getAllAppointments());
        assertEquals(2, appointments.getAllAppointments().size());
    }

}
