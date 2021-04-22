package models;

import java.util.ArrayList;

public class Appointments {
    //private ObservableList<Appointment> olAppointments = FXCollections.observableArrayList();
    private ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    /** Adds an appointment to the internal array list
     * @param appointment - Object to be added to collection*/
    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
    }

    /** Returns the entire internal array list of appointments
     * @return ArrayList - collection of appointment objects */
    public ArrayList<Appointment> getAllAppointments() {
        return appointments;
    }

    /** allows the array list to be loaded from an external source
     * @param appointments  - takes an array of appointments and loads the Appointments Object with them.*/
    public void setAllAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    /** takes an appointment Id, and either returns the appropriate appointment, or null if not found
     * @param idOfUpdatedAppointment - id of appointment to find
     * @return Appointment - object returned - null if id not found */
    public Appointment getAppointmentById(int idOfUpdatedAppointment) {
        for (Appointment current: appointments) {
            if(current.getId() == idOfUpdatedAppointment){
                return current;
            }
        }
        return null;
    }
}

