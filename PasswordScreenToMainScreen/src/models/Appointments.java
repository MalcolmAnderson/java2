package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Appointments {
    private ObservableList<Appointment> olAppointments = FXCollections.observableArrayList();
    private ArrayList<Appointment> alAppointments = new ArrayList<Appointment>();

    public void addAppointment(Appointment appointment){
        alAppointments.add(appointment);
    }

    public ObservableList<Appointment> getOL_Appointments() {
        olAppointments.setAll(alAppointments);
        return olAppointments;
    }

    public ArrayList<Appointment> getAppointments() {
        return alAppointments;
    }

    public void setAllAppointments(ArrayList<Appointment> alAppointments) {
        this.alAppointments = alAppointments;
    }
}

