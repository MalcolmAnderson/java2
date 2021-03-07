package models;


import java.time.LocalDateTime;

public class ManageTestData {


    public static Appointments BuildPlaceHolderData() {

        Appointments appointments = new Appointments();

        LocalDateTime start = LocalDateTime.of(2020, 04, 28, 9, 00);
        LocalDateTime end = LocalDateTime.of(2020, 04, 28, 10, 00);
        appointments.addAppointment(new Appointment(1, "a", "b", "c", "d", "e", start, end, 2));
        appointments.addAppointment(new Appointment(2, "a", "b", "c", "d", "e", start, end, 5));
        appointments.addAppointment(new Appointment(3, "a", "b", "c", "d", "e", start, end, 3));
        appointments.addAppointment(new Appointment(4, "a", "b", "c", "d", "e", start, end, 3));
        appointments.addAppointment(new Appointment(5, "a", "b", "c", "d", "e", start, end, 5));


        //for(int i = 1; i <= 5; i++){

            //Appointment appointment = new Appointment("This is appointment number " + i);
            //appointments.addAppointment(appointment);

        //}

        return appointments;
    }
}