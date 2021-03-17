package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Appointments {
    //private ObservableList<Appointment> olAppointments = FXCollections.observableArrayList();
    private ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
    }

//    public Appointments getOL_Appointments() {
//        olAppointments.setAll(appointments.);
//        return olAppointments;
//    }

    public ArrayList<Appointment> getAllAppointments() {
        return appointments;
    }

    public void setAllAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }
}

