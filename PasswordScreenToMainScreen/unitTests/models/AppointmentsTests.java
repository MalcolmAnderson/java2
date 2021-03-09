package models;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentsTests {

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

        Appointment foo = new Appointment(1, "a", "b", "c", "d", "e", start, end, 5);
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
        Appointments foo = ManageTestData.BuildPlaceHolderData();
        assertEquals(5, foo.getAppointments().size());
    }

    @Test void Appointments_getOL_ShouldWork(){
        Appointments foo = ManageTestData.BuildPlaceHolderData();
        ObservableList<Appointment> bar = foo.getOL_Appointments();
//        assertEquals(5, foo.getOL_Appointments().size());
        assertEquals("ObservableListWrapper",foo.getOL_Appointments().getClass().getSimpleName());
    }

    @Test void GetTableViewColumnNames(){
//        Appointments foo = ManageTestData.BuildPlaceHolderData();
//        ObservableList<Appointment> ol_appointments = foo.getOL_Appointments();
//        List<Appointment> list = ol_appointments.get(0).
//        assertEquals(5, foo.getAppointments().size());
//        System.out.println(list.toString());

    }

}