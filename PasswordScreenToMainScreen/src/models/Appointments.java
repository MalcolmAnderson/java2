package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Appointments {
    private ObservableList<Appointment> olAppointments = FXCollections.observableArrayList();

    public void addAppointment(Appointment appointment){
        olAppointments.add(appointment);
    }

    public ObservableList getAllAppointments() {
        return olAppointments;
    }
}

