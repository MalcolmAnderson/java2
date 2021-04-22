package model_Tests;

import main.Globals;
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

public class Appointments_Tests {

    //@FXML private TableView appointmentsTableView = new TableView();

    Appointments appointments;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void AppointmentConstructor_ShouldWork() {
        LocalDateTime start = LocalDateTime.of(2020, 04, 28, 9, 00);
        LocalDateTime end = LocalDateTime.of(2020, 04, 28, 10, 00);

        Appointment foo = new Appointment(1, "a", "b", "c", "d", start, end, 5, 4);
        assertEquals(1, foo.getId());
        assertEquals("a", foo.getTitle());
        assertEquals("b", foo.getDescription());
        assertEquals("c", foo.getLocation());
        assertEquals("d", foo.getType());
        assertEquals("2020-04-28T09:00", foo.getStart().toString());
        assertEquals("2020-04-28T10:00", foo.getEnd().toString());
        assertEquals(5, foo.getCustomer_Id());
        assertEquals(4, foo.getContact_Id());
    }

    @Test void BuildTestData_ShouldHave5Records(){
        Appointments foo = _ManageTestData.BuildPlaceHolderData_Appointments();
        assertEquals(6, foo.getAllAppointments().size());
    }

    @Test void AppointmentsShouldBeArrayListsOfAppointment(){
        Appointments appointments = _ManageTestData.BuildPlaceHolderData_Appointments();
//        ObservableList<Appointment> ol_appointments = foo.getOL_Appointments();
        List<Appointment> list = appointments.getAllAppointments();
        assertEquals(6, list.size());
//        System.out.println(list.toString());

    }

    @Test void CreateAppointmentObject(){
        int id = 70;
        String title = "This is a title";
        String description = "This is a description";
        String location = "location";
        String type = "type";
        LocalDateTime start = LocalDateTime.of(1964, 03, 14, 23, 15, 48);
        LocalDateTime end = LocalDateTime.of(2765, 04, 15, 02, 17, 52);
        int customerId = 1;
        int contactId = 2;
        Appointment a = new Appointment(id, title, description, location, type, start, end, customerId, contactId);
        assertEquals(id, a.getId());
        assertEquals(title, a.getTitle());
        assertEquals(description, a.getDescription());
        assertEquals(location, a.getLocation());
        assertEquals(type, a.getType());
        assertEquals(start, a.getStart());
        assertEquals(end, a.getEnd());
        assertEquals(customerId, a.getCustomer_Id());
        assertEquals(contactId, a.getContact_Id());
    }

}