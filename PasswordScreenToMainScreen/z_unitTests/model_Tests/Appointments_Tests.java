package model_Tests;

import models.Appointment;
import models.Appointments;
import models._ManageTestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Appointments_Tests {

    //@FXML private TableView appointmentsTableView = new TableView();

    Appointments appointments;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test void CreateTimeStampShouldWork(){
        LocalDateTime one = LocalDateTime.of(1964, 3, 14, 23, 42, 56);
        LocalDateTime two = LocalDateTime.of(1971, 5, 26, 9, 56, 27);
        assertEquals("1964-03-14T23:42:56", one.toString());
    }

    @Test
    void AppointmentConstructor_ShouldWork() {
        LocalDateTime start = LocalDateTime.of(2020, 04, 28, 9, 00);
        LocalDateTime end = LocalDateTime.of(2020, 04, 28, 10, 00);

        Appointment foo = new Appointment(1, "a", "b", "c", "d", "e", start, end, 5, "Daddy Warbuck's Annie");
        assertEquals(1, foo.getId());
        assertEquals("a", foo.getTitle());
        assertEquals("b", foo.getDescription());
        assertEquals("c", foo.getLocation());
        assertEquals("d", foo.getContact_Name());
        assertEquals("e", foo.getType());
        assertEquals("2020-04-28T09:00", foo.getStart().toString());
        assertEquals("2020-04-28T10:00", foo.getEnd().toString());
        assertEquals(5, foo.getCustomer_Id());
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

}