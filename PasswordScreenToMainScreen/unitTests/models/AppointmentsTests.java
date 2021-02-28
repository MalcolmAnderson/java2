package models;

import javafx.collections.ObservableList;
import main.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentsTests {


    Appointments appointments;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void AddTestDataShouldAdd_5_Rows() {
        appointments = ManageTestData.BuildPlaceHolderData();
        ObservableList<Appointment> olAppointments = appointments.getAllAppointments();
        assertEquals(5, olAppointments.size());
    }
}