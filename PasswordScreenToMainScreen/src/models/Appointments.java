package models;

import java.util.ArrayList;

public class Appointments {
    //private ObservableList<Appointment> olAppointments = FXCollections.observableArrayList();
    private ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
    }

    public ArrayList<Appointment> getAllAppointments() {
        return appointments;
    }

    public void setAllAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Appointment getAppointmentById(int idOfUpdatedAppointment) {
        for (Appointment current: appointments) {
            if(current.getId() == idOfUpdatedAppointment){
                return current;
            }
        }
        return null;
    }
}

