package models;


public class ManageTestData {


    public static Appointments BuildPlaceHolderData() {

        Appointments appointments = new Appointments();

        for(int i = 1; i <= 5; i++){

            Appointment appointment = new Appointment("This is appointment number " + i);
            appointments.addAppointment(appointment);

        }

        return appointments;
    }
}