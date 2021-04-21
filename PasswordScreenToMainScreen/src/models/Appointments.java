package models;

import java.util.ArrayList;

public class Appointments {
    //private ObservableList<Appointment> olAppointments = FXCollections.observableArrayList();
    private ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    /** Adds an appointment to the internal array list */
    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
    }

    /** Returns the entire internal array list of appointments */
    public ArrayList<Appointment> getAllAppointments() {
        return appointments;
    }

    /** allows the array list to be loaded from an external source */
    public void setAllAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    /** takes an appointment Id, and either returns the appropriate appointment, or null if not found */
    public Appointment getAppointmentById(int idOfUpdatedAppointment) {
        for (Appointment current: appointments) {
            if(current.getId() == idOfUpdatedAppointment){
                return current;
            }
        }
        return null;
    }
}

